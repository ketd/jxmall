package com.ketd.member.service.impl;


import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.result.Result;
import com.ketd.common.result.ResultCodeEnum;
import com.ketd.member.dto.EmailDto;
import com.ketd.member.service.EmailService;
import com.ketd.member.util.RedisUtil;
import com.ketd.member.vo.MemberVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.member.mapper.MemberMapper;
import com.ketd.member.domain.Member;
import com.ketd.member.service.IMemberService;


/**
 * 会员Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisUtil  redisUtil;

    @Autowired
    private EmailService  emailService;



    // 验证码放入redis缓存过期时间
    @Value("${code.expiration}")
    private Long expiration;



    /**
     * 查询会员
     *
     * @param id 会员主键
     * @return 会员
     */
    @Override
    public Member selectMemberById(Long id)
    {
        return memberMapper.selectById(id);
    }



    /**
     * 查询会员列表
     *
     * @param member 会员
     * @return 会员
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>(member);
        return memberMapper.selectList(queryWrapper);
    }

    /**
     * 新增会员
     *
     * @param member 会员
     * @return 结果
     */

    @Override
    public int insertMember(Member member) {
        return memberMapper.insert(member);
    }





    /**
     * 修改会员
     *
     * @param member 会员
     * @return 结果
     */

    @Override
    public int updateMember(Member member) {
        return memberMapper.updateById(member);
    }

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的会员主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(Long[] ids) {
        return memberMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员信息
     *
     * @param id 会员主键
     * @return 结果
     */
    @Override
    public int deleteMemberById(Long id) {
        return memberMapper.deleteById(id);
    }


    /**
     * 导出会员列表
     */
    @Override
    public void export(List<Member> list, HttpServletResponse response) {

        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表"+ ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
            EasyExcel.write(response.getOutputStream(), Member.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result<?> regist(MemberVo memberVo) {

        if (memberMapper.findOneByMobile(memberVo.getMobile()) !=null) {
            return Result.build(null, ResultCodeEnum.USER_MOBILE_EXISTENCE);
        }
        if(memberMapper.findOneByEmail(memberVo.getEmail()) !=null){
            return Result.build(null, ResultCodeEnum.EMAIL_EXISTENCE);
        }

        // 通过email获取redis中的code
        Object value = redisUtil.get("regist-Code:"+"Email-"+memberVo.getEmail());
        if (value == null || !value.toString().equals(memberVo.getCode())) {

            return Result.build(null, ResultCodeEnum.INVALID_VERIFICATION_CODE);
        } else {
            redisUtil.del("regist-Code:"+"Email-"+memberVo.getEmail());
        }

        Member member = new Member();
        member.setLevelId(1L);
        member.setUsername(memberVo.getUsername());
        member.setNickname(memberVo.getNickname());
        member.setMobile(memberVo.getMobile());
        member.setEmail(memberVo.getEmail());
        member.setHeader(null);
        member.setCreateTime(new Date());




        //加密
        String encodedPassword = BCrypt.hashpw( memberVo.getPassword(), BCrypt.gensalt());
        member.setPassword(encodedPassword);
        memberMapper.insert(member);
        return Result.build(null, ResultCodeEnum.REGISTER_SUCCESS);

    }

    @Override
    public CompletableFuture<Result<?>> sendMailCode(String email) {


        // 查看注册邮箱是否存在
        if (memberMapper.findOneByEmail(email)!=null) {
            return CompletableFuture.completedFuture(Result.build(null,ResultCodeEnum.EMAIL_EXISTENCE));
        }

        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-code.ftl");

        // 从redis缓存中尝试获取验证码
        String code = redisUtil.get("regist-Code:"+"Email-"+email);
        if (code == null) {
            // 如果在缓存中未获取到验证码，则产生6位随机数，放入缓存中
            code = RandomUtil.randomNumbers(6);
            redisUtil.set("regist-Code:"+"Email-"+email, code, expiration);


        }
        emailService.send(new EmailDto(Collections.singletonList(email),
                "京西商城||邮箱验证码", template.render(Dict.create().set("code", code))));

        return CompletableFuture.completedFuture(Result.build(null,ResultCodeEnum.SUCCESS));
    }
}