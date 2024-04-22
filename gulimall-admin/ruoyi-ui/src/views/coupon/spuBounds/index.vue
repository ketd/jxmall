<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="spuId">
        <el-input
          v-model="queryParams.data.spuId"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成长积分" prop="growBounds">
        <el-input
          v-model="queryParams.data.growBounds"
          placeholder="请输入成长积分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购物积分" prop="buyBounds">
        <el-input
          v-model="queryParams.data.buyBounds"
          placeholder="请输入购物积分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠生效情况[1111" prop="work">
        <el-select v-model="queryParams.data.work" placeholder="请选择优惠生效情况[1111" clearable>
          <el-option
            v-for="dict in dict.type.work"
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
          v-hasPermi="['coupon:spuBounds:add']"
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
          v-hasPermi="['coupon:spuBounds:edit']"
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
          v-hasPermi="['coupon:spuBounds:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['coupon:spuBounds:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="spuBoundsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="${comment}" align="center" prop="spuId" />
      <el-table-column label="成长积分" align="center" prop="growBounds" />
      <el-table-column label="购物积分" align="center" prop="buyBounds" />
      <el-table-column label="优惠生效情况[1111" align="center" prop="work">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.work" :value="scope.row.work"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['coupon:spuBounds:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['coupon:spuBounds:remove']"
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

    <!-- 添加或修改商品spu积分设置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="spuId">
          <el-input v-model="form.spuId" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="成长积分" prop="growBounds">
          <el-input v-model="form.growBounds" placeholder="请输入成长积分" />
        </el-form-item>
        <el-form-item label="购物积分" prop="buyBounds">
          <el-input v-model="form.buyBounds" placeholder="请输入购物积分" />
        </el-form-item>
        <el-form-item label="优惠生效情况[1111" prop="work">
          <el-select v-model="form.work" placeholder="请选择优惠生效情况[1111">
            <el-option
              v-for="dict in dict.type.work"
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
  listSpuBoundsPage,
  getSpuBounds,
  delSpuBounds,
  addSpuBounds,
  updateSpuBounds ,
  exportSpuBounds
} from "@/api/coupon/spuBounds";

export default {
  name: "SpuBounds",
  dicts: ['work'],
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
      // 商品spu积分设置表格数据
      spuBoundsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data:{
          spuId: null,
          growBounds: null,
          buyBounds: null,
          work: null
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
    /** 查询商品spu积分设置列表 */
    getList() {
      this.loading = true;
      listSpuBoundsPage(this.queryParams).then(response => {
        this.spuBoundsList = response.data.rows;
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
        spuId: null,
        growBounds: null,
        buyBounds: null,
        work: null
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
      this.title = "添加商品spu积分设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSpuBounds(id).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改商品spu积分设置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSpuBounds(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSpuBounds(this.form).then(response => {
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
        return delSpuBounds(ids);
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
        return exportSpuBounds(ids);
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


