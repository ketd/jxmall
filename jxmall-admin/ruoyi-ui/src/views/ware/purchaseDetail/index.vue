<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="采购单id" prop="purchaseId">
        <el-input
          v-model="queryParams.data.purchaseId"
          placeholder="请输入采购单id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购商品id" prop="skuId">
        <el-input
          v-model="queryParams.data.skuId"
          placeholder="请输入采购商品id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购数量" prop="skuNum">
        <el-input
          v-model="queryParams.data.skuNum"
          placeholder="请输入采购数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购金额" prop="skuPrice">
        <el-input
          v-model="queryParams.data.skuPrice"
          placeholder="请输入采购金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库id" prop="wareId">
        <el-input
          v-model="queryParams.data.wareId"
          placeholder="请输入仓库id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.data.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.purchase_status"
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
          v-hasPermi="['ware:purchaseDetail:add']"
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
          v-hasPermi="['ware:purchaseDetail:edit']"
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
          v-hasPermi="['ware:purchaseDetail:remove']"
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
          v-hasPermi="['ware:purchaseDetail:export']"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          :disabled="multiple"
          @click="handleMerge"
        >合并到整单
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="purchaseDetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="购买详情id" align="center" prop="id"/>
      <el-table-column label="采购单id" align="center" prop="purchaseId"/>
      <el-table-column label="采购商品id" align="center" prop="skuId"/>
      <el-table-column label="采购数量" align="center" prop="skuNum"/>
      <el-table-column label="采购金额" align="center" prop="skuPrice"/>
      <el-table-column label="仓库id" align="center" prop="wareId"/>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.purchase_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ware:purchaseDetail:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ware:purchaseDetail:remove']"
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

    <!-- 添加或修改采购详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="采购单id" prop="purchaseId">
          <el-input v-model="form.purchaseId" placeholder="请输入采购单id"/>
        </el-form-item>
        <el-form-item label="采购商品id" prop="skuId">
          <el-input v-model="form.skuId" placeholder="请输入采购商品id"/>
        </el-form-item>
        <el-form-item label="采购数量" prop="skuNum">
          <el-input v-model="form.skuNum" placeholder="请输入采购数量"/>
        </el-form-item>
        <el-form-item label="采购金额" prop="skuPrice">
          <el-input v-model="form.skuPrice" placeholder="请输入采购金额"/>
        </el-form-item>
        <el-form-item label="仓库id" prop="wareId">
          <el-input v-model="form.wareId" placeholder="请输入仓库id"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.purchase_status"
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

    <el-dialog :title="title" :visible.sync="openMerge" width="500px" append-to-body>
      <el-select v-model="purchaseListId" placeholder="请选择状态" clearable>
        <el-option
          v-for="dict in PurchaseList"
          :key="dict.id"
          :label="dict.id"
          :value="dict.id"
        ></el-option>
      </el-select>

      <!-- 展示所选项对应的详情 -->
      <el-descriptions title="详细信息" v-if="selectedPurchaseDetails">
        <el-descriptions-item label="ID">{{ selectedPurchaseDetails.id }}</el-descriptions-item>
        <el-descriptions-item label="采购人ID">{{ selectedPurchaseDetails.assigneeId }}</el-descriptions-item>
        <el-descriptions-item label="采购人姓名">{{ selectedPurchaseDetails.assigneeName }}</el-descriptions-item>
        <el-descriptions-item label="电话">{{ selectedPurchaseDetails.phone }}</el-descriptions-item>
        <el-descriptions-item label="优先级">{{ selectedPurchaseDetails.priority }}</el-descriptions-item>
        <el-descriptions-item label="状态">{{ selectedPurchaseDetails.status }}</el-descriptions-item>
        <el-descriptions-item label="仓库ID">{{ selectedPurchaseDetails.wareId }}</el-descriptions-item>
        <el-descriptions-item label="金额">{{ selectedPurchaseDetails.amount }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ selectedPurchaseDetails.createTime }}</el-descriptions-item>
        <el-descriptions-item label="更新时间">{{ selectedPurchaseDetails.updateTime }}</el-descriptions-item>
      </el-descriptions>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitMerge">确 定</el-button>
        <el-button @click="openMerge = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listPurchaseDetailPage,
  getPurchaseDetail,
  delPurchaseDetail,
  addPurchaseDetail,
  updatePurchaseDetail,
  exportPurchaseDetail, addMerge
} from "@/api/ware/purchaseDetail";
import {listPurchasePage} from "@/api/ware/purchase";

export default {
  name: "PurchaseDetail",
  dicts: ['purchase_status'],
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
      // 采购详情表格数据
      purchaseDetailList: [],
      PurchaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示合并弹出层
      openMerge: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data: {
          purchaseId: null,
          skuId: null,
          skuNum: null,
          skuPrice: null,
          wareId: null,
          status: null
        }
      },
      mergeQueryParams: {
        pageNum: 1,
        pageSize: 10,
        data:{
          status: null,
        }
      },
      purchaseListId: null,
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
    this.getPurchaseList();
  },
  computed: {
    selectedPurchaseDetails() {
      // 根据 purchaseListId 获取对应的详情
      return this.PurchaseList.find(item => item.id === this.purchaseListId);
    },
  },
  methods: {
    /** 查询采购详情列表 */
    getList() {
      this.loading = true;
      listPurchaseDetailPage(this.queryParams).then(response => {
        this.purchaseDetailList = response.data.rows;
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
        purchaseId: null,
        skuId: null,
        skuNum: null,
        skuPrice: null,
        wareId: null,
        status: null
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加采购详情";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPurchaseDetail(id).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改采购详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePurchaseDetail(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchaseDetail(this.form).then(response => {
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
        return delPurchaseDetail(ids);
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
      const ids = [].concat(row.id !== undefined ? row.id : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认导出商品属性编号为"' + ids.join(', ') + '"的数据项？').then(() => {
        return exportPurchaseDetail(ids);
      }).then(() => {
        this.$modal.msgSuccess("导出成功");
        this.getList();
      }).catch(() => {
        console.log("导出失败");
      });
    },
    getPurchaseList(){
      this.mergeQueryParams.data.status=0;
      listPurchasePage(this.mergeQueryParams).then(response => {
        this.PurchaseList = response.data.rows;
      });
      this.mergeQueryParams.data.status=1;
      listPurchasePage(this.mergeQueryParams).then(response => {
        this.PurchaseList = this.PurchaseList.concat(response.data.rows);
      });
    },
    handleMerge(row){
      this.openMerge = true;
      this.title = "合并采购详情";

    },
    submitMerge(){
      const ids = [].concat(this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否合并' + ids.join(', ') + '"的采购单？').then(() => {
        addMerge(this.purchaseListId,ids).then(response => {
          this.openMerge = false;
          this.purchaseListId=null;
        })
      })
    }
  }
};
</script>


