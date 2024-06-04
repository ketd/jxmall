<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="品牌名" prop="name">
        <el-input
          v-model="queryParams.data.name"
          placeholder="请输入品牌名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="显示状态" prop="showStatus">
        <el-select v-model="queryParams.data.showStatus" placeholder="请选择显示状态" clearable>
          <el-option
            v-for="dict in dict.type.show_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检索首字母" prop="firstLetter">
        <el-input
          v-model="queryParams.data.firstLetter"
          placeholder="请输入检索首字母"
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
          v-hasPermi="['product:brand:add']"
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
          v-hasPermi="['product:brand:edit']"
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
          v-hasPermi="['product:brand:remove']"
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
          v-hasPermi="['product:brand:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="brandList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="品牌id" align="center" prop="brandId"/>
      <el-table-column label="品牌名" align="center" prop="name"/>
      <el-table-column label="品牌logo地址" align="center" prop="logo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.logo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="介绍" align="center" prop="descript"/>
      <el-table-column label="显示状态" align="center" prop="showStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.show_status" :value="scope.row.showStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="检索首字母" align="center" prop="firstLetter"/>
      <el-table-column label="排序" align="center" prop="sort"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['product:brand:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['product:brand:remove']"
          >删除
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-order"
            @click="relevancy(scope.row.brandId)"
          >关联分类
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

    <!-- 添加或修改品牌对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="品牌名" prop="name">
          <el-input v-model="form.name" placeholder="请输入品牌名"/>
        </el-form-item>
        <el-form-item label="品牌logo地址" prop="logo">
          <image-upload v-model="form.logo"/>
        </el-form-item>
        <el-form-item label="介绍" prop="descript">
          <el-input v-model="form.descript" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="显示状态" prop="showStatus">
          <el-radio-group v-model="form.showStatus">
            <el-radio
              v-for="dict in dict.type.show_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="检索首字母" prop="firstLetter">
          <el-input v-model="form.firstLetter" placeholder="请输入检索首字母"/>
        </el-form-item>
        <el-form-item label="检索排序" prop="firstLetter">
          <el-input v-model="form.sort" placeholder="请输入检索首字母"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 关联分类对话框 -->
    <el-dialog :title="title" :visible.sync="openRelevancy" width="500px" append-to-body>
      <el-button :disabled="catelogIds.length===0" style="margin-bottom: 10px" type="primary" icon="el-icon-edit"
                 @click="addRelevancy">新增关联
      </el-button>
      <el-cascader
        v-model="catelogIds"
        :options="options"
        :props="props"
        clearable
      >
      </el-cascader>
      <!-- TODO 获取品牌分类 -->
      <el-table v-loading="loading" :data="categoryBrandRelationList">
        <el-table-column label="#" align="center" prop="id"/>
        <el-table-column label="品牌名" align="center" prop="brandName"/>
        <el-table-column label="分类名" align="center" prop="catelogName"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDeleteCategoryBrandRelation(scope.row)"
              v-hasPermi="['product:brand:remove']"
            >移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>

      <pagination
        v-show="categoryBrandRelationListTotal>0"
        :total="categoryBrandRelationListTotal"
        :page.sync="listCategoryBrandRelationPageParams.pageNum"
        :limit.sync="listCategoryBrandRelationPageParams.pageSize"
        @pagination="getAttrAttrgroupRelation"
      />
    </el-dialog>
  </div>
</template>

<script>
import {
  listBrandPage,
  getBrand,
  delBrand,
  addBrand,
  updateBrand,
  exportBrand
} from "@/api/product/brand";
import {
  addCategoryBrandRelation,
  delCategoryBrandRelation,
  listCategoryBrandRelationPage
} from "@/api/product/categoryBrandRelation";
import {getMenus} from "@/api/commoin/commoin";
import {addGroup} from "@/api/product/group";

export default {
  name: "Brand",
  dicts: ['show_status'],
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
      categoryBrandRelationListTotal: 0,
      // 品牌表格数据
      brandList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data: {
          name: null,
          logo: null,
          descript: null,
          showStatus: null,
          firstLetter: null,
          sort: null
        }
      },
      // 表单参数
      form: {},
      RelevancyForm: {},
      // 表单校验
      rules: {},
      openRelevancy: false,
      listCategoryBrandRelationPageParams: {
        pageNum: 1,
        pageSize: 10,
        data: {
          brandId: null,
          catelogId: null,
          brandName: null,
          catelogName: null
        }
      },
      // 品牌分类关联表格数据
      categoryBrandRelationList: [],
      catelogIds: [],
      options: [],
      props: {
        value: 'catId',
        label: 'name',
        children: 'children'
      },
      getAttrAttrgroupRelationBrandId: null

    };
  },
  created() {
    this.getList();
    this.getMenus();
  },
  methods: {
    /** 查询品牌列表 */
    getList() {
      this.loading = true;
      listBrandPage(this.queryParams).then(response => {
        this.brandList = response.data.rows;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.openRelevancy = false;
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        brandId: null,
        name: null,
        logo: null,
        descript: null,
        showStatus: null,
        firstLetter: null,
        sort: null
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
      this.ids = selection.map(item => item.brandId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加品牌";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const brandId = row.brandId || this.ids
      getBrand(brandId).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改品牌";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.brandId != null) {
            updateBrand(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBrand(this.form).then(response => {
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
      const brandIds = [].concat(row.brandId !== undefined ? row.brandId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认删除商品属性编号为"' + brandIds.join(', ') + '"的数据项？').then(() => {
        return delBrand(brandIds);
      }).then(() => {
        // 删除成功后刷新列表并显示成功消息
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
        // 可以在这里处理删除失败的情况，比如显示错误消息
      });

    },
    relevancy(brandId) {
      this.title = "关联分类";
      this.openRelevancy = true;
      this.categoryBrandRelationList = [];
      this.catelogIds = []
      this.RelevancyForm = {};
      this.getAttrAttrgroupRelationBrandId = brandId;
      this.getAttrAttrgroupRelation()

    },
    /** 导出按钮操作 */
    handleExport(row) {
      const brandIds = [].concat(row.brandId !== undefined ? row.brandId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认导出商品属性编号为"' + brandIds.join(', ') + '"的数据项？').then(() => {
        return exportBrand(brandIds);
      }).then(() => {
        this.$modal.msgSuccess("导出成功");
        this.getList();
      }).catch(() => {
        console.log("导出失败");
      });

    },
    getAttrAttrgroupRelation() {
      this.listCategoryBrandRelationPageParams.data.brandId = this.getAttrAttrgroupRelationBrandId;
      listCategoryBrandRelationPage(this.listCategoryBrandRelationPageParams).then(response => {
        this.categoryBrandRelationList = response.data.rows;
        this.categoryBrandRelationListTotal = response.data.total;

      });
    },
    handleDeleteCategoryBrandRelation(row) {
      const ids = [].concat(row.id !== undefined ? row.id : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认删除关联分类编号为"' + ids.join(', ') + '"的数据项？').then(() => {
        return delCategoryBrandRelation(ids);
      }).then(() => {
        // 删除成功后刷新列表并显示成功消息
        this.getAttrAttrgroupRelation();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
        // 可以在这里处理删除失败的情况，比如显示错误消息
      });

    },
    getMenus() {
      getMenus().then(response => {
        this.options = response.data.data;
      }).catch(error => {
      }).finally(() => {
      });
    },
    addRelevancy() {
      this.RelevancyForm.catelogId = this.catelogIds[2];
      this.RelevancyForm.brandId = this.getAttrAttrgroupRelationBrandId;
      addCategoryBrandRelation(this.RelevancyForm).then(response => {
        this.$modal.msgSuccess("新增成功");
        this.catelogIds = []
        this.RelevancyForm = {};
        this.open = false;
        this.getAttrAttrgroupRelation();
      });
    }
  }
};
</script>


