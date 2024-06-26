<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                  <el-form-item label="名字" prop="name">
                    <el-input
                        v-model="queryParams.data.name"
                        placeholder="请输入名字"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="开始时间" prop="startTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.startTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="请选择开始时间">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="结束时间" prop="endTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.endTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="请选择结束时间">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="点击数" prop="clickCount">
                    <el-input
                        v-model="queryParams.data.clickCount"
                        placeholder="请输入点击数"
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
                  <el-form-item label="发布者" prop="publisherId">
                    <el-input
                        v-model="queryParams.data.publisherId"
                        placeholder="请输入发布者"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="审核者" prop="authId">
                    <el-input
                        v-model="queryParams.data.authId"
                        placeholder="请输入审核者"
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
            v-hasPermi="['coupon:homeAdv:add']"
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
            v-hasPermi="['coupon:homeAdv:edit']"
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
            v-hasPermi="['coupon:homeAdv:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['coupon:homeAdv:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="homeAdvList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="名字" align="center" prop="name" />
              <el-table-column label="图片地址" align="center" prop="pic" width="100">
                <template slot-scope="scope">
                  <image-preview :src="scope.row.pic" :width="50" :height="50"/>
                </template>
              </el-table-column>
              <el-table-column label="开始时间" align="center" prop="startTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="结束时间" align="center" prop="endTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="状态" align="center" prop="status" />
              <el-table-column label="点击数" align="center" prop="clickCount" />
              <el-table-column label="广告详情连接地址" align="center" prop="url" />
              <el-table-column label="备注" align="center" prop="note" />
              <el-table-column label="排序" align="center" prop="sort" />
              <el-table-column label="发布者" align="center" prop="publisherId" />
              <el-table-column label="审核者" align="center" prop="authId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['coupon:homeAdv:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['coupon:homeAdv:remove']"
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

    <!-- 添加或修改首页轮播广告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="名字" prop="name">
                          <el-input v-model="form.name" placeholder="请输入名字" />
                        </el-form-item>
                        <el-form-item label="图片地址" prop="pic">
                          <image-upload v-model="form.pic" />
                        </el-form-item>
                        <el-form-item label="开始时间" prop="startTime">
                          <el-date-picker clearable
                                          v-model="form.startTime"
                                          type="date"
                                          value-format="yyyy-MM-dd HH:mm:ss"
                                          placeholder="请选择开始时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="结束时间" prop="endTime">
                          <el-date-picker clearable
                                          v-model="form.endTime"
                                          type="date"
                                          value-format="yyyy-MM-dd HH:mm:ss"
                                          placeholder="请选择结束时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="点击数" prop="clickCount">
                          <el-input v-model="form.clickCount" placeholder="请输入点击数" />
                        </el-form-item>
                        <el-form-item label="广告详情连接地址" prop="url">
                          <el-input v-model="form.url" type="textarea" placeholder="请输入内容" />
                        </el-form-item>
                        <el-form-item label="备注" prop="note">
                          <el-input v-model="form.note" type="textarea" placeholder="请输入内容" />
                        </el-form-item>
                        <el-form-item label="排序" prop="sort">
                          <el-input v-model="form.sort" placeholder="请输入排序" />
                        </el-form-item>
                        <el-form-item label="发布者" prop="publisherId">
                          <el-input v-model="form.publisherId" placeholder="请输入发布者" />
                        </el-form-item>
                        <el-form-item label="审核者" prop="authId">
                          <el-input v-model="form.authId" placeholder="请输入审核者" />
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
      listHomeAdvPage,
    getHomeAdv,
    delHomeAdv,
    addHomeAdv,
    updateHomeAdv ,
    exportHomeAdv
  } from "@/api/coupon/homeAdv";

  export default {
    name: "HomeAdv",
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
        // 首页轮播广告表格数据
              homeAdvList: [],
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
                        pic: null,
                        startTime: null,
                        endTime: null,
                        status: null,
                        clickCount: null,
                        url: null,
                        note: null,
                        sort: null,
                        publisherId: null,
                        authId: null
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
      /** 查询首页轮播广告列表 */
      getList() {
        this.loading = true;
        listHomeAdvPage(this.queryParams).then(response => {
          this.homeAdvList = response.data.rows;
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
                        name: null,
                        pic: null,
                        startTime: null,
                        endTime: null,
                        status: null,
                        clickCount: null,
                        url: null,
                        note: null,
                        sort: null,
                        publisherId: null,
                        authId: null
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
        this.title = "添加首页轮播广告";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getHomeAdv(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改首页轮播广告";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              if (Array.isArray(this.form.pic)) { // 确保 this.form.pic 是数组
                this.form.pic = this.form.pic.map(filename => filename).join(", ");
              }
              updateHomeAdv(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              if (Array.isArray(this.form.pic)) { // 确保 this.form.pic 是数组
                this.form.pic = this.form.pic.map(filename => filename).join(", ");
              }
              addHomeAdv(this.form).then(response => {
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
          return delHomeAdv(ids);
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
    return exportHomeAdv(ids);
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


