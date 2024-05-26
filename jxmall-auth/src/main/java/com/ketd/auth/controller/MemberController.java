package com.ketd.auth.controller;

import com.ketd.auth.server.MemberService;
import com.ketd.auth.vo.MemberReceiveAddressVo;
import com.ketd.auth.vo.UpdateMemberInfoVo;
import com.ketd.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.controller
 * @Author: ketd
 * @CreateTime: 2024-05-21  17:32
 */
@Tag(name = "用户个人信息操作")
@RestController
@RequestMapping("/auth/member")
public class MemberController {

    @Autowired
    private MemberService  memberService;

    @GetMapping
    public Result<?> getMemberInfo(){
       return memberService.getMemberInfo();
    }

    @PutMapping
    public Result<?> updateMemberInfo(@RequestBody UpdateMemberInfoVo updateMemberInfoVo){
        return memberService.updateMemberInfo(updateMemberInfoVo);
    }

    @GetMapping("/address")
    public Result<?> getMemberAddress(){
        return memberService.getMemberAddress();
    }

    @PostMapping("/address")
    public Result<?> addMemberAddress(@RequestBody MemberReceiveAddressVo memberReceiveAddressVo){
        return memberService.addMemberAddress(memberReceiveAddressVo);
    }

    @DeleteMapping("address")
    public Result<?> deleteMemberAddress(@RequestBody Long[] ids){
        return memberService.deleteMemberAddress(ids);
    }

    @PutMapping("/address")
    public Result<?> updateMemberAddress(@RequestBody MemberReceiveAddressVo memberReceiveAddressVo){
        return memberService.updateMemberAddress(memberReceiveAddressVo);
    }


}
