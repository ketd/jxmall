package com.ketd.product.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.SpuInfoDesc;


/**
 * spu信息介绍Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface ISpuInfoDescService  extends IService<SpuInfoDesc> {
    /**
     * 查询spu信息介绍
     * 
     * @param spuId spu信息介绍主键
     * @return spu信息介绍
     */
    public SpuInfoDesc selectSpuInfoDescBySpuId(Long spuId);

    /**
     * 查询spu信息介绍列表
     * 
     * @param spuInfoDesc spu信息介绍
     * @return spu信息介绍集合
     */
    public List<SpuInfoDesc> selectSpuInfoDescList(SpuInfoDesc spuInfoDesc);

    /**
     * 新增spu信息介绍
     * 
     * @param spuInfoDesc spu信息介绍
     * @return 结果
     */
    public int insertSpuInfoDesc(SpuInfoDesc spuInfoDesc);

    /**
     * 修改spu信息介绍
     * 
     * @param spuInfoDesc spu信息介绍
     * @return 结果
     */
    public int updateSpuInfoDesc(SpuInfoDesc spuInfoDesc);

    /**
     * 批量删除spu信息介绍
     * 
     * @param spuIds 需要删除的spu信息介绍主键集合
     * @return 结果
     */
    public int deleteSpuInfoDescBySpuIds(Long[] spuIds);

    /**
     * 删除spu信息介绍信息
     * 
     * @param spuId spu信息介绍主键
     * @return 结果
     */
    public int deleteSpuInfoDescBySpuId(Long spuId);

    /**
     * 导出spu信息介绍列表
     */
    void export(List<SpuInfoDesc> list, HttpServletResponse response);
}
