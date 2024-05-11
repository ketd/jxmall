<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                  <el-form-item label="优惠券id" prop="couponId">
                    <el-input
                        v-model="queryParams.data.couponId"
                        placeholder="请输入优惠券id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="会员id" prop="memberId">
                    <el-input
                        v-model="queryParams.data.memberId"
                        placeholder="请输入会员id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="会员名字" prop="memberNickName">
                    <el-input
                        v-model="queryParams.data.memberNickName"
                        placeholder="请输入会员名字"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="获取方式[0-&gt;后台赠送；1-&gt;主动领取]" prop="getType">
                    <el-select v-model="queryParams.data.getType" placeholder="请选择获取方式[0-&gt;后台赠送；1-&gt;主动领取]" clearable>
                      <el-option
                          v-for="dict in dict.type.get_type"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="使用状态[0-&gt;未使用；1-&gt;已使用；2-&gt;已过期]" prop="useStateType">
                    <el-select v-model="queryParams.data.useStateType" placeholder="请选择使用状态[0-&gt;未使用；1-&gt;已使用；2-&gt;已过期]" clearable>
                      <el-option
                          v-for="dict in dict.type.use_state_type"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="使用时间" prop="useTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.useTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="请选择使用时间">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="订单id" prop="orderId">
                    <el-input
                        v-model="queryParams.data.orderId"
                        placeholder="请输入订单id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="订单号" prop="orderSn">
                    <el-input
                        v-model="queryParams.data.orderSn"
                        placeholder="请输入订单号"
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
            v-hasPermi="['coupon:couponHistory:add']"
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
            v-hasPermi="['coupon:couponHistory:edit']"
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
            v-hasPermi="['coupon:couponHistory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['coupon:couponHistory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="couponHistoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="优惠券id" align="center" prop="couponId" />
              <el-table-column label="会员id" align="center" prop="memberId" />
              <el-table-column label="会员名字" align="center" prop="memberNickName" />
              <el-table-column label="获取方式[0-&gt;后台赠送；1-&gt;主动领取]" align="center" prop="getType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.get_type" :value="scope.row.getType"/>
                </template>
              </el-table-column>
              <el-table-column label="使用状态[0-&gt;未使用；1-&gt;已使用；2-&gt;已过期]" align="center" prop="useStateType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.use_state_type" :value="scope.row.useStateType"/>
                </template>
              </el-table-column>
              <el-table-column label="使用时间" align="center" prop="useTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.useTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="订单id" align="center" prop="orderId" />
              <el-table-column label="订单号" align="center" prop="orderSn" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['coupon:couponHistory:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['coupon:couponHistory:remove']"
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

    <!-- 添加或修改优惠券领取历史记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="优惠券id" prop="couponId">
                          <el-input v-model="form.couponId" placeholder="请输入优惠券id" />
                        </el-form-item>
                        <el-form-item label="会员id" prop="memberId">
                          <el-input v-model="form.memberId" placeholder="请输入会员id" />
                        </el-form-item>
                        <el-form-item label="会员名字" prop="memberNickName">
                          <el-input v-model="form.memberNickName" placeholder="请输入会员名字" />
                        </el-form-item>
                        <el-form-item label="获取方式[0-&gt;后台赠送；1-&gt;主动领取]" prop="getType">
                          <el-select v-model="form.getType" placeholder="请选择获取方式[0-&gt;后台赠送；1-&gt;主动领取]">
                            <el-option
                                v-for="dict in dict.type.get_type"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="使用状态[0-&gt;未使用；1-&gt;已使用；2-&gt;已过期]" prop="useStateType">
                          <el-select v-model="form.useStateType" placeholder="请选择使用状态[0-&gt;未使用；1-&gt;已使用；2-&gt;已过期]">
                            <el-option
                                v-for="dict in dict.type.use_state_type"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="使用时间" prop="useTime">
                          <el-date-picker clearable
                                          v-model="form.useTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择使用时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="订单id" prop="orderId">
                          <el-input v-model="form.orderId" placeholder="请输入订单id" />
                        </el-form-item>
                        <el-form-item label="订单号" prop="orderSn">
                          <el-input v-model="form.orderSn" placeholder="请输入订单号" />
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
      listCouponHistoryPage,
    getCouponHistory,
    delCouponHistory,
    addCouponHistory,
    updateCouponHistory ,
    exportCouponHistory
  } from "@/api/coupon/couponHistory";

  export default {
    name: "CouponHistory",
        dicts: ['use_state_type', 'get_type'],
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
        // 优惠券领取历史记录表格数据
              couponHistoryList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
            data:{
                        couponId: null,
                        memberId: null,
                        memberNickName: null,
                        getType: null,
                        useStateType: null,
                        useTime: null,
                        orderId: null,
                        orderSn: null
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
      /** 查询优惠券领取历史记录列表 */
      getList() {
        this.loading = true;
        listCouponHistoryPage(this.queryParams).then(response => {
          this.couponHistoryList = response.data.rows;
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
                        couponId: null,
                        memberId: null,
                        memberNickName: null,
                        getType: null,
                        createTime: null,
                        useStateType: null,
                        useTime: null,
                        orderId: null,
                        orderSn: null
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
        this.title = "添加优惠券领取历史记录";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getCouponHistory(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改优惠券领取历史记录";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateCouponHistory(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addCouponHistory(this.form).then(response => {
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
          return delCouponHistory(ids);
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
    return exportCouponHistory(ids);
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


