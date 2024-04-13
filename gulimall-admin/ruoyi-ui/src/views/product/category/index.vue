<template >



  <div class="app-container">

    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                  <el-form-item label="分类名称" prop="name">
                    <el-input
                        v-model="queryParams.data.name"
                        placeholder="请输入分类名称"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="父分类id" prop="parentCid">
                    <el-input
                        v-model="queryParams.data.parentCid"
                        placeholder="请输入父分类id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="层级" prop="catLevel">
                    <el-input
                        v-model="queryParams.data.catLevel"
                        placeholder="请输入层级"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="排序" prop="sort">
                    <el-input
                        v-model="queryParams.data.sort"
                        placeholder="请输入排序"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="图标地址" prop="icon">
                    <el-input
                        v-model="queryParams.data.icon"
                        placeholder="请输入图标地址"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="计量单位" prop="productUnit">
                    <el-input
                        v-model="queryParams.data.productUnit"
                        placeholder="请输入计量单位"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="商品数量" prop="productCount">
                    <el-input
                        v-model="queryParams.data.productCount"
                        placeholder="请输入商品数量"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
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
            v-hasPermi="['product:category:add']"
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
            v-hasPermi="['product:category:edit']"
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
            v-hasPermi="['product:category:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['product:category:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="categoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="分类id" align="center" prop="catId" />
              <el-table-column label="分类名称" align="center" prop="name" />
              <el-table-column label="父分类id" align="center" prop="parentCid" />
              <el-table-column label="层级" align="center" prop="catLevel" />
              <el-table-column label="是否显示[0-不显示，1显示]" align="center" prop="showStatus" />
              <el-table-column label="排序" align="center" prop="sort" />
              <el-table-column label="图标地址" align="center" prop="icon" />
              <el-table-column label="计量单位" align="center" prop="productUnit" />
              <el-table-column label="商品数量" align="center" prop="productCount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['product:category:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['product:category:remove']"
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

    <!-- 添加或修改商品三级分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="分类名称" prop="name">
                          <el-input v-model="form.name" placeholder="请输入分类名称" />
                        </el-form-item>
                        <el-form-item label="父分类id" prop="parentCid">
                          <el-input v-model="form.parentCid" placeholder="请输入父分类id" />
                        </el-form-item>
                        <el-form-item label="层级" prop="catLevel">
                          <el-input v-model="form.catLevel" placeholder="请输入层级" />
                        </el-form-item>
                        <el-form-item label="排序" prop="sort">
                          <el-input v-model="form.sort" placeholder="请输入排序" />
                        </el-form-item>
                        <el-form-item label="图标地址" prop="icon">
                          <el-input v-model="form.icon" placeholder="请输入图标地址" />
                        </el-form-item>
                        <el-form-item label="计量单位" prop="productUnit">
                          <el-input v-model="form.productUnit" placeholder="请输入计量单位" />
                        </el-form-item>
                        <el-form-item label="商品数量" prop="productCount">
                          <el-input v-model="form.productCount" placeholder="请输入商品数量" />
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
    listCategoryPage,
    getCategory,
    delCategory,
    addCategory,
    updateCategory ,
    exportCategory
  } from "@/api/product/category";

  export default {
    name: "Category",
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
        // 商品三级分类表格数据
              categoryList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
            data:{
                        name: null,
                        parentCid: null,
                        catLevel: null,
                        showStatus: null,
                        sort: null,
                        icon: null,
                        productUnit: null,
                        productCount: null
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
      /** 查询商品三级分类列表 */
      getList() {
        this.loading = true;
        listCategoryPage(this.queryParams).then(response => {
          this.categoryList = response.data.rows;
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
                        catId: null,
                        name: null,
                        parentCid: null,
                        catLevel: null,
                        showStatus: null,
                        sort: null,
                        icon: null,
                        productUnit: null,
                        productCount: null
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
        this.ids = selection.map(item => item.catId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加商品三级分类";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const catId = row.catId || this.ids
        getCategory(catId).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改商品三级分类";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.catId != null) {
              updateCategory(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addCategory(this.form).then(response => {
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
        const catIds = [].concat(row.catId !== undefined ? row.catId : [], this.ids); // 将属性ID合并为一个数组
        this.$modal.confirm('是否确认删除商品属性编号为"' + catIds.join(', ') + '"的数据项？').then(() => {
          return delCategory(catIds);
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
    const catIds = [].concat(row.catId !== undefined ? row.catId : [], this.ids); // 将属性ID合并为一个数组
    this.$modal.confirm('是否确认导出商品属性编号为"' + catIds.join(', ') + '"的数据项？').then(() => {
    return exportCategory(catIds);
    }).then(() => {
    this.$modal.msgSuccess("导出成功");
    this.getList();
    }).catch(() => {
    console.log("导出失败");
    });

  }
  }
  };
</script>


