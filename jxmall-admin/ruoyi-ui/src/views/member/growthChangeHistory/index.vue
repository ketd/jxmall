<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                  <el-form-item label="member_id" prop="memberId">
                    <el-input
                        v-model="queryParams.data.memberId"
                        placeholder="请输入member_id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="改变的值" prop="changeCount">
                    <el-input
                        v-model="queryParams.data.changeCount"
                        placeholder="请输入改变的值"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="备注" prop="note">
                    <el-input
                        v-model="queryParams.data.note"
                        placeholder="请输入备注"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="积分来源[0-购物，1-管理员修改]" prop="sourceType">
                    <el-select v-model="queryParams.data.sourceType" placeholder="请选择积分来源[0-购物，1-管理员修改]" clearable>
                      <el-option
                          v-for="dict in dict.type.source_type"
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
            v-hasPermi="['member:growthChangeHistory:add']"
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
            v-hasPermi="['member:growthChangeHistory:edit']"
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
            v-hasPermi="['member:growthChangeHistory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['member:growthChangeHistory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="growthChangeHistoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="member_id" align="center" prop="memberId" />
              <el-table-column label="改变的值" align="center" prop="changeCount" />
              <el-table-column label="备注" align="center" prop="note" />
              <el-table-column label="积分来源[0-购物，1-管理员修改]" align="center" prop="sourceType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.source_type" :value="scope.row.sourceType"/>
                </template>
              </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['member:growthChangeHistory:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['member:growthChangeHistory:remove']"
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

    <!-- 添加或修改成长值变化历史记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="member_id" prop="memberId">
                          <el-input v-model="form.memberId" placeholder="请输入member_id" />
                        </el-form-item>
                        <el-form-item label="改变的值" prop="changeCount">
                          <el-input v-model="form.changeCount" placeholder="请输入改变的值" />
                        </el-form-item>
                        <el-form-item label="备注" prop="note">
                          <el-input v-model="form.note" placeholder="请输入备注" />
                        </el-form-item>
                        <el-form-item label="积分来源[0-购物，1-管理员修改]" prop="sourceType">
                          <el-select v-model="form.sourceType" placeholder="请选择积分来源[0-购物，1-管理员修改]">
                            <el-option
                                v-for="dict in dict.type.source_type"
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
      listGrowthChangeHistoryPage,
    getGrowthChangeHistory,
    delGrowthChangeHistory,
    addGrowthChangeHistory,
    updateGrowthChangeHistory ,
    exportGrowthChangeHistory
  } from "@/api/member/growthChangeHistory";

  export default {
    name: "GrowthChangeHistory",
        dicts: ['source_type'],
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
        // 成长值变化历史记录表格数据
              growthChangeHistoryList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
            data:{
                        memberId: null,
                        changeCount: null,
                        note: null,
                        sourceType: null
          }
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
                        memberId: [
                    { required: true, message: "member_id不能为空", trigger: "blur" }
                  ],
                        createTime: [
                    { required: true, message: "create_time不能为空", trigger: "blur" }
                  ],
                        changeCount: [
                    { required: true, message: "改变的值不能为空", trigger: "blur" }
                  ],
                        note: [
                    { required: true, message: "备注不能为空", trigger: "blur" }
                  ],
                        sourceType: [
                    { required: true, message: "积分来源[0-购物，1-管理员修改]不能为空", trigger: "change" }
                  ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询成长值变化历史记录列表 */
      getList() {
        this.loading = true;
        listGrowthChangeHistoryPage(this.queryParams).then(response => {
          this.growthChangeHistoryList = response.data.rows;
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
                        memberId: null,
                        createTime: null,
                        changeCount: null,
                        note: null,
                        sourceType: null
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
        this.title = "添加成长值变化历史记录";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getGrowthChangeHistory(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改成长值变化历史记录";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateGrowthChangeHistory(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addGrowthChangeHistory(this.form).then(response => {
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
          return delGrowthChangeHistory(ids);
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
    return exportGrowthChangeHistory(ids);
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


