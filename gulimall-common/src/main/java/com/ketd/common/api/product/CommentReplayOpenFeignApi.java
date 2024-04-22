package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.CommentReplayTO;

@FeignClient(value = "cloud-gateway-service")
public interface CommentReplayOpenFeignApi {

    @PostMapping("/product/CommentReplay/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CommentReplayTO> pageRequest);

    /**
     * 获取商品评价回复关系详细信息
     */
    @GetMapping(value = "/product/CommentReplay/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增商品评价回复关系
     */
    @PostMapping("/product/CommentReplay/save")
    public Result<?> add(@RequestBody CommentReplayTO commentReplayTO);

    /**
     * 修改商品评价回复关系
     */
    @PutMapping("/product/CommentReplay/update")
    public Result<?> edit(@RequestBody CommentReplayTO commentReplayTO);

    /**
     * 删除商品评价回复关系
     */
    @DeleteMapping("/product/CommentReplay/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}