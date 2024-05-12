package com.ketd.product.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.result.Result;
import com.ketd.product.domain.AttrAttrgroupRelation;
import com.ketd.product.domain.AttrGroup;
import com.ketd.product.domain.Category;
import com.ketd.product.mapper.AttrAttrgroupRelationMapper;
import com.ketd.product.mapper.AttrGroupMapper;
import com.ketd.product.mapper.CategoryMapper;
import com.ketd.product.service.ICategoryService;
import com.ketd.product.vo.AttrVo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.AttrMapper;
import com.ketd.product.domain.Attr;
import com.ketd.product.service.IAttrService;


/**
 * 商品属性Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private AttrGroupMapper attrGroupMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;


    @Override
    public Result<?> pageAttrList(PageRequest<Attr> pageRequest) {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Attr attr = pageRequest.getData();
        attr.setAttrType(pageRequest.getData().getAttrType());
        return getResult(pageNum, pageSize, attr);
    }

    @Override
    public Result<?> noLinkAttrList(PageRequest<Attr> pageRequest) {
        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Attr attr = pageRequest.getData();
        attr.setAttrType(pageRequest.getData().getAttrType());
        attr.setCatelogId(pageRequest.getData().getCatelogId());



        return getResult(pageNum, pageSize, attr);
    }

    @Override
    public Result<?> linkAttr(Long attrGroupId, Long[] attrIds) {

        for (Long attrId : attrIds) {
            System.out.println(attrGroupId);
            try {
                // 修改分组id
                AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.findOneByAttrId(attrId);
                if (attrAttrgroupRelation == null) {
                    attrAttrgroupRelation = new AttrAttrgroupRelation();
                    attrAttrgroupRelation.setAttrId(attrId);
                    attrAttrgroupRelation.setAttrGroupId(attrGroupId);
                    attrAttrgroupRelationMapper.insert(attrAttrgroupRelation);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return Result.ok(null);
    }

    @Override
    public List<Attr> findAllAttrByAttrGroupId(PageRequest<Long> pageRequest) {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();
        Page<Attr> page = new Page<>(pageNum, pageSize);
        Attr attrQ = new Attr();
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>(attrQ);
        List<Attr> attrPage = attrMapper.selectPage(page, queryWrapper).getRecords();


        Long attrGroupId = pageRequest.getData();
        List<AttrAttrgroupRelation> attrAttrgroupRelationList = attrAttrgroupRelationMapper.findAllByAttrGroupId(attrGroupId);
        List<Attr> attrList = new ArrayList<>();

        // 遍历属性关联关系列表
        for (AttrAttrgroupRelation item : attrAttrgroupRelationList) {
            // 查找匹配的Attr对象并添加到attrList中
            for (Attr attr : attrPage) {
                if (attr.getAttrId().equals(item.getAttrId())) {
                    attrList.add(attr);
                    break; // 找到匹配的Attr后跳出内层循环
                }
            }
        }


        return attrList;
    }


    /**
     * 根据分组id找到关联的所有属性
     *
     * @param attrgroupId
     * @return
     */
    @Override
    public List<Attr> getRelationAttr(Long attrgroupId) {

        List<AttrAttrgroupRelation> entities = attrAttrgroupRelationMapper.selectList
                (new QueryWrapper<AttrAttrgroupRelation>().eq("attr_group_id", attrgroupId));

        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        //根据attrIds查找所有的属性信息
        //Collection<Attr> attrEntities = this.listByIds(attrIds);

        //如果attrIds为空就直接返回一个null值出去
        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }

        List<Attr> AttrList = this.baseMapper.selectBatchIds(attrIds);

        return AttrList;
    }

    @Override
    public List<AttrVo> pageAttrSaleList(Long catalogId) {
        Attr attrQ = new Attr();
        attrQ.setAttrType(0L);
        attrQ.setCatelogId(catalogId);
        attrQ.setEnable(1L);
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>(attrQ);
        List<Attr> attrList = attrMapper.selectList(queryWrapper);


        List<AttrVo> attrVoList = attrList.stream().map(item -> {
            AttrVo attrVo = new AttrVo();
            BeanUtils.copyProperties(item, attrVo);

            AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.findOneByAttrId(item.getAttrId());
            if (attrAttrgroupRelation != null) {
                attrVo.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
            }
            return attrVo;
        }).collect(Collectors.toList());

        return attrVoList;


    }

    @Override
    public List<Long> selectSearchAttrs(List<Long> attrIds) {
        List<Attr> attrEntities = this.listByIds(attrIds);
        return attrEntities.stream().filter(item -> {
            return item.getSearchType() == 1 && item.getEnable() == 1;
        }).map(Attr::getAttrId).collect(Collectors.toList());
    }

    @Override
    public Result<?> getInfoByCatelogId(Long catelogId) {
       /* Attr attr = new Attr();
        attr.setCatelogId(catelogId);
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>(attr);
        List<Attr> attrList = attrMapper.selectList(queryWrapper);
        List<Long>  attrIds = attrList.stream().map(Attr::getAttrId).toList();*/
        return null;

    }

    @Override
    public Result<?> findAttrByCatelogId(Long catelogId) {
        List<Attr> attrList = attrMapper.selectAllByCatelogId(catelogId);
        return Result.ok(attrList);
    }


    /**
     * 获取当前分组没有被关联的所有属性
     *
     * @param
     * @param
     * @return
     */
    /*@Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {

        //1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroup attrGroup = attrGroupMapper.selectById(attrgroupId);
        //获取当前分类的id
        Long catelogId = attrGroup.getCatelogId();

        //2、当前分组只能关联别的分组没有引用的属性
        //2.1）、当前分类下的其它分组
        List<AttrGroup> groupEntities = attrGroupMapper.selectList(new QueryWrapper<AttrGroup>()
                .eq("catelog_id", catelogId));

        //获取到所有的attrGroupId
        List<Long> collect = groupEntities.stream().map((item) -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());


        //2.2）、这些分组关联的属性
        List<AttrAttrgroupRelation> groupId = attrAttrgroupRelationMapper.selectList
                (new QueryWrapper<AttrAttrgroupRelation>().in("attr_group_id", collect));

        List<Long> attrIds = groupId.stream().map((item) -> {
            return item.getAttrId();
        }).collect(Collectors.toList());

        //2.3）、从当前分类的所有属性移除这些属性
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<Attr>()
                .eq("catelog_id", catelogId).eq("attr_type",1);

        if (attrIds != null && attrIds.size() > 0) {
            queryWrapper.notIn("attr_id", attrIds);
        }


        IPage<Attr> page = this.page(new Query<Attr>().getPage(params), queryWrapper);

        PageUtils pageUtils = new PageUtils(page);


        return pageUtils;
    }*/

    private Result<?> getResult(Integer pageNum, Integer pageSize, Attr attr) {
        Page<Attr> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>(attr);
        IPage<Attr> attrPage = attrMapper.selectPage(page, queryWrapper);

        List<AttrVo> attrList = new ArrayList<>();

        attrPage.getRecords().forEach(item -> {

            // 转换成AttrVo
            AttrVo attrVo = new AttrVo();
            BeanUtils.copyProperties(item, attrVo);


            Long attrGroupId = null;
            AttrAttrgroupRelation relation = attrAttrgroupRelationMapper.findOneByAttrId(item.getAttrId());
            if (relation != null) {
                attrGroupId = relation.getAttrGroupId();
            }

            if (attrGroupId != null) {
                AttrGroup attrGroup = attrGroupMapper.selectById(attrGroupId);
                attrVo.setAttrGroupId(attrGroup.getAttrGroupId());
                attrVo.setAttrGroupName(attrGroup.getAttrGroupName());
            }else {
                attrVo.setAttrGroupId(null);
                attrVo.setAttrGroupName("无分组");
            }


            // 查询分类名称
            if (item.getCatelogId() != null) {
                // 查询分类名称
                Category categoryAndName = categoryMapper.selectById(item.getCatelogId());
                attrVo.setCatelogName(categoryAndName.getName());
            }


            attrList.add(attrVo);


        });
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(attrPage.getTotal());
        tableDataInfo.setRows(attrList);
        return Result.ok(tableDataInfo);
    }


    /**
     * 查询商品属性
     *
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    @Override
    public Result<?> selectAttrByAttrId(Long attrId) {
        try {
            Attr attr = attrMapper.selectById(attrId);
            AttrVo attrVo = new AttrVo();
            BeanUtils.copyProperties(attr, attrVo);
            Long[] path = categoryService.findCategoryPath(attr.getCatelogId());
            attrVo.setCatelogPath(path);


            // 查询分组信息
            AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.findOneByAttrId(attrId);
            if (attrAttrgroupRelation != null) {
                attrVo.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
            }

            return Result.ok(attrVo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 查询商品属性列表
     *
     * @param attr 商品属性
     * @return 商品属性
     */
    @Override
    public List<Attr> selectAttrList(Attr attr) {
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>(attr);
        return attrMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品属性
     *
     * @param attr 商品属性
     * @return 结果
     */

    @Override
    public int insertAttr(Attr attr) {

        return attrMapper.insert(attr);
    }


    /**
     * 修改商品属性
     *
     * @param attrVo 商品属性
     * @return 结果
     */

    @Override
    public int updateAttr(AttrVo attrVo) {
        Attr attr = new Attr();
        BeanUtils.copyProperties(attrVo, attr);

        AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.findOneByAttrId(attrVo.getAttrId());

        if (attrAttrgroupRelation != null) {
            attrAttrgroupRelation.setAttrGroupId(attrVo.getAttrGroupId());
            attrAttrgroupRelationMapper.updateById(attrAttrgroupRelation);
        }


        return attrMapper.updateById(attr);
    }

    /**
     * 批量删除商品属性
     *
     * @param attrIds 需要删除的商品属性主键集合
     * @return 结果
     */
    @Override
    public int deleteAttrByAttrIds(Long[] attrIds) {
        return attrMapper.deleteBatchIds(Arrays.asList(attrIds));
    }

    /**
     * 删除商品属性信息
     *
     * @param attrId 商品属性主键
     * @return 结果
     */
    @Override
    public int deleteAttrByAttrId(Long attrId) {
        return attrMapper.deleteById(attrId);
    }


    /**
     * 导出商品属性列表
     */
    @Override
    public void export(List<Attr> list, HttpServletResponse response) {

        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表" + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            EasyExcel.write(response.getOutputStream(), Attr.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}