<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                  <el-form-item label="sku_id" prop="skuId">
                    <el-input
                        v-model="queryParams.data.skuId"
                        placeholder="请输入sku_id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="会员等级id" prop="memberLevelId">
                    <el-input
                        v-model="queryParams.data.memberLevelId"
                        placeholder="请输入会员等级id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="会员等级名" prop="memberLevelName">
                    <el-input
                        v-model="queryParams.data.memberLevelName"
                        placeholder="请输入会员等级名"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="会员对应价格" prop="memberPrice">
                    <el-input
                        v-model="queryParams.data.memberPrice"
                        placeholder="请输入会员对应价格"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="可否叠加其他优惠[0-不可叠加优惠，1-可叠加]" prop="addOther">
                    <el-select v-model="queryParams.data.addOther" placeholder="请选择可否叠加其他优惠[0-不可叠加优惠，1-可叠加]" clearable>
                      <el-option
                          v-for="dict in dict.type.add_other"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['coupon:memberPrice:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['coupon:memberPrice:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['coupon:memberPrice:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['coupon:memberPrice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="memberPriceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="sku_id" align="center" prop="skuId" />
              <el-table-column label="会员等级id" align="center" prop="memberLevelId" />
              <el-table-column label="会员等级名" align="center" prop="memberLevelName" />
              <el-table-column label="会员对应价格" align="center" prop="memberPrice" />
              <el-table-column label="可否叠加其他优惠[0-不可叠加优惠，1-可叠加]" align="center" prop="addOther">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.add_other" :value="scope.row.addOther"/>
                </template>
              </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['coupon:memberPrice:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['coupon:memberPrice:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改商品会员价格对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="sku_id" prop="skuId">
                          <el-input v-model="form.skuId" placeholder="请输入sku_id" />
                        </el-form-item>
                        <el-form-item label="会员等级id" prop="memberLevelId">
                          <el-input v-model="form.memberLevelId" placeholder="请输入会员等级id" />
                        </el-form-item>
                        <el-form-item label="会员等级名" prop="memberLevelName">
                          <el-input v-model="form.memberLevelName" placeholder="请输入会员等级名" />
                        </el-form-item>
                        <el-form-item label="会员对应价格" prop="memberPrice">
                          <el-input v-model="form.memberPrice" placeholder="请输入会员对应价格" />
                        </el-form-item>
                        <el-form-item label="可否叠加其他优惠[0-不可叠加优惠，1-可叠加]" prop="addOther">
                          <el-select v-model="form.addOther" placeholder="请选择可否叠加其他优惠[0-不可叠加优惠，1-可叠加]">
                            <el-option
                                v-for="dict in dict.type.add_other"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
      listMemberPricePage,
    getMemberPrice,
    delMemberPrice,
    addMemberPrice,
    updateMemberPrice ,
    exportMemberPrice
  } from "@/api/coupon/memberPrice";

  export default {
    name: "MemberPrice",
        dicts: ['add_other'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 商品会员价格表格数据
              memberPriceList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
            data:{
                        skuId: null,
                        memberLevelId: null,
                        memberLevelName: null,
                        memberPrice: null,
                        addOther: null
          }
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询商品会员价格列表 */
      getList() {
        this.loading = true;
        listMemberPricePage(this.queryParams).then(response => {
          this.memberPriceList = response.data.rows;
          this.total = response.data.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
                        id: null,
                        skuId: null,
                        memberLevelId: null,
                        memberLevelName: null,
                        memberPrice: null,
                        addOther: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加商品会员价格";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getMemberPrice(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改商品会员价格";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateMemberPrice(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addMemberPrice(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = [].concat(row.id !== undefined ? row.id : [], this.ids); // 将属性ID合并为一个数组
        this.$modal.confirm('是否确认删除商品属性编号为"' + ids.join(', ') + '"的数据项？').then(() => {
          return delMemberPrice(ids);
        }).then(() => {
          // 删除成功后刷新列表并显示成功消息
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
          // 可以在这里处理删除失败的情况，比如显示错误消息
        });

      },
  /** 导出按钮操作 */
  handleExport(row)
  {
    const ids = [].concat(row.id !== undefined ? row.id : [], this.ids); // 将属性ID合并为一个数组
    this.$modal.confirm('是否确认导出商品属性编号为"' + ids.join(', ') + '"的数据项？').then(() => {
    return exportMemberPrice(ids);
    }).then(() => {
    this.$modal.msgSuccess("导出成功");
    this.getList();
    }).catch(() => {
    console.log("导出失败");
    });

  },
  }
  };
</script>


