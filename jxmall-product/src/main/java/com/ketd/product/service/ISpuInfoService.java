package com.ketd.product.service;


import java.util.List;

import com.ketd.common.result.Result;
import com.ketd.product.domain.SpuInfoDesc;
import com.ketd.product.vo.SpuSaveVo;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.SpuInfo;


/**
 * spu信息Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface ISpuInfoService  extends IService<SpuInfo> {
    /**
     * 查询spu信息
     * 
     * @param id spu信息主键
     * @return spu信息
     */
    public SpuInfo selectSpuInfoById(Long id);

    /**
     * 查询spu信息列表
     * 
     * @param spuInfo spu信息
     * @return spu信息集合
     */
    public List<SpuInfo> selectSpuInfoList(SpuInfo spuInfo);

    /**
     * 新增spu信息
     * 
     * @param spuInfo spu信息
     * @return 结果
     */
    public int insertSpuInfo(SpuInfo spuInfo);

    /**
     * 修改spu信息
     * 
     * @param spuInfo spu信息
     * @return 结果
     */
    public int updateSpuInfo(SpuInfo spuInfo);

    /**
     * 批量删除spu信息
     * 
     * @param ids 需要删除的spu信息主键集合
     * @return 结果
     */
    public int deleteSpuInfoByIds(Long[] ids);

    /**
     * 删除spu信息信息
     * 
     * @param id spu信息主键
     * @return 结果
     */
    public int deleteSpuInfoById(Long id);

    /**
     * 导出spu信息列表
     */
    void export(List<SpuInfo> list, HttpServletResponse response);


    Result<?> saveSpuInfo(SpuSaveVo spuInfo);

    void saveBaseSpuInfo(SpuInfo spuInfo);


    Result<?> up(Long[] ids);
}
