<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="spuId" prop="spuId">
        <el-input
          v-model="queryParams.data.spuId"
          placeholder="请输入spuId"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="sku名称" prop="skuName">
        <el-input
          v-model="queryParams.data.skuName"
          placeholder="请输入sku名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="sku销售属性" prop="skuDesc">
        <el-input
          v-model="queryParams.data.skuAttrValues"
          placeholder="请输入sku销售属性"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属分类id" prop="catalogId">
        <el-input
          v-model="queryParams.data.catalogId"
          placeholder="请输入所属分类id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌id" prop="brandId">
        <el-input
          v-model="queryParams.data.brandId"
          placeholder="请输入品牌id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题" prop="skuTitle">
        <el-input
          v-model="queryParams.data.skuTitle"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input
          v-model="queryParams.data.price"
          placeholder="请输入价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="销量" prop="saleCount">
        <el-input
          v-model="queryParams.data.saleCount"
          placeholder="请输入销量"
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
          v-hasPermi="['product:skuInfo:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['product:skuInfo:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['product:skuInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['product:skuInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="skuInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="skuId" align="center" prop="skuId"/>
      <el-table-column label="spuId" align="center" prop="spuId"/>
      <el-table-column label="sku名称" align="center" prop="skuName"/>
      <el-table-column label="sku介绍描述" align="center" prop="skuDesc"/>
      <el-table-column label="sku销售属性" align="center" prop="skuAttrValues"/>
      <el-table-column label="所属分类id" align="center" prop="catalogId"/>
      <el-table-column label="品牌id" align="center" prop="brandId"/>
      <el-table-column label="默认图片" align="center" prop="skuDefaultImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.skuDefaultImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="skuTitle"/>
      <el-table-column label="副标题" align="center" prop="skuSubtitle"/>
      <el-table-column label="价格" align="center" prop="price"/>
      <el-table-column label="销量" align="center" prop="saleCount"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['product:skuInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['product:skuInfo:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改sku信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="spuId" prop="spuId">
          <el-input v-model="form.spuId" placeholder="请输入spuId"/>
        </el-form-item>
        <el-form-item label="sku名称" prop="skuName">
          <el-input v-model="form.skuName" placeholder="请输入sku名称"/>
        </el-form-item>
        <el-form-item label="sku介绍描述" prop="skuDesc">
          <el-input v-model="form.skuDesc" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="sku销售属性" prop="skuAttrValues">
          <el-input v-model="form.skuAttrValues" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="所属分类id" prop="catalogId">
          <el-input v-model="form.catalogId" placeholder="请输入所属分类id"/>
        </el-form-item>
        <el-form-item label="品牌id" prop="brandId">
          <el-input v-model="form.brandId" placeholder="请输入品牌id"/>
        </el-form-item>
        <el-form-item label="默认图片" prop="skuDefaultImg">
          <image-upload v-model="form.skuDefaultImg" @upload-success="handleUploadSuccess"/>
        </el-form-item>
        <el-form-item label="标题" prop="skuTitle">
          <el-input v-model="form.skuTitle" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item label="副标题" prop="skuSubtitle">
          <el-input v-model="form.skuSubtitle" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格"/>
        </el-form-item>
        <el-form-item label="销量" prop="saleCount">
          <el-input v-model="form.saleCount" placeholder="请输入销量"/>
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
  listSkuInfoPage,
  getSkuInfo,
  delSkuInfo,
  addSkuInfo,
  updateSkuInfo,
  exportSkuInfo
} from "@/api/product/skuInfo";

export default {
  name: "SkuInfo",
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
      // sku信息表格数据
      skuInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data: {
          spuId: null,
          skuName: null,
          skuDesc: null,
          catalogId: null,
          brandId: null,
          skuDefaultImg: null,
          skuTitle: null,
          skuSubtitle: null,
          price: null,
          saleCount: null,
          skuAttrValues: null
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询sku信息列表 */
    getList() {
      this.loading = true;
      listSkuInfoPage(this.queryParams).then(response => {
        this.skuInfoList = response.data.rows;
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
        skuId: null,
        spuId: null,
        skuName: null,
        skuDesc: null,
        catalogId: null,
        brandId: null,
        skuDefaultImg: null,
        skuTitle: null,
        skuSubtitle: null,
        price: null,
        saleCount: null,
        skuAttrValues: null
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
      this.ids = selection.map(item => item.skuId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加sku信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const skuId = row.skuId || this.ids
      getSkuInfo(skuId).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改sku信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {

        if (valid) {
          if (this.form.skuId != null) {
            updateSkuInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSkuInfo(this.form).then(response => {
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
      const skuIds = [].concat(row.skuId !== undefined ? row.skuId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认删除商品属性编号为"' + skuIds.join(', ') + '"的数据项？').then(() => {
        return delSkuInfo(skuIds);
      }).then(() => {
        // 删除成功后刷新列表并显示成功消息
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
        // 可以在这里处理删除失败的情况，比如显示错误消息
      });

    },
    /** 导出按钮操作 */
    handleExport(row) {
      const skuIds = [].concat(row.skuId !== undefined ? row.skuId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认导出商品属性编号为"' + skuIds.join(', ') + '"的数据项？').then(() => {
        return exportSkuInfo(skuIds);
      }).then(() => {
        this.$modal.msgSuccess("导出成功");
        this.getList();
      }).catch(() => {
        console.log("导出失败");
      });

    },
    handleUploadSuccess(fileName) {
      this.form.saleCount = fileName;
    },

  }
};
</script>


