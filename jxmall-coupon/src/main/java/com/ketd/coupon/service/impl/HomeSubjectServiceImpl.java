package com.ketd.coupon.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.coupon.mapper.HomeSubjectMapper;
import com.ketd.coupon.domain.HomeSubject;
import com.ketd.coupon.service.IHomeSubjectService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectMapper, HomeSubject> implements IHomeSubjectService {

    @Autowired
    private HomeSubjectMapper homeSubjectMapper;



    /**
     * 查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     *
     * @param id 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键
     * @return 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Override
    public HomeSubject selectHomeSubjectById(Long id)
    {
        return homeSubjectMapper.selectById(id);
    }



    /**
     * 查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     *
     * @param homeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Override
    public List<HomeSubject> selectHomeSubjectList(HomeSubject homeSubject)
    {
        QueryWrapper<HomeSubject> queryWrapper = new QueryWrapper<>(homeSubject);
        return homeSubjectMapper.selectList(queryWrapper);
    }

    /**
     * 新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     *
     * @param homeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 结果
     */

    @Override
    public int insertHomeSubject(HomeSubject homeSubject) {
        return homeSubjectMapper.insert(homeSubject);
    }





    /**
     * 修改首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     *
     * @param homeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 结果
     */

    @Override
    public int updateHomeSubject(HomeSubject homeSubject) {
        return homeSubjectMapper.updateById(homeSubject);
    }

    /**
     * 批量删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     *
     * @param ids 需要删除的首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键集合
     * @return 结果
     */
    @Override
    public int deleteHomeSubjectByIds(Long[] ids) {
        return homeSubjectMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】信息
     *
     * @param id 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键
     * @return 结果
     */
    @Override
    public int deleteHomeSubjectById(Long id) {
        return homeSubjectMapper.deleteById(id);
    }


    /**
     * 导出首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     */
    @Override
    public void export(List<HomeSubject> list, HttpServletResponse response) {

        extracted(list, response,HomeSubject.class);

    }
}