<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                  <el-form-item label="优惠卷类型[0-&gt;全场赠券；1-&gt;会员赠券；2-&gt;购物赠券；3-&gt;注册赠券]" prop="couponType">
                    <el-select v-model="queryParams.data.couponType" placeholder="请选择优惠卷类型[0-&gt;全场赠券；1-&gt;会员赠券；2-&gt;购物赠券；3-&gt;注册赠券]" clearable>
                      <el-option
                          v-for="dict in dict.type.coupon_type"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="优惠卷名字" prop="couponName">
                    <el-input
                        v-model="queryParams.data.couponName"
                        placeholder="请输入优惠卷名字"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="数量" prop="num">
                    <el-input
                        v-model="queryParams.data.num"
                        placeholder="请输入数量"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="金额" prop="amount">
                    <el-input
                        v-model="queryParams.data.amount"
                        placeholder="请输入金额"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="每人限领张数" prop="perLimit">
                    <el-input
                        v-model="queryParams.data.perLimit"
                        placeholder="请输入每人限领张数"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="使用门槛" prop="minPoint">
                    <el-input
                        v-model="queryParams.data.minPoint"
                        placeholder="请输入使用门槛"
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
                  <el-form-item label="使用类型[0-&gt;全场通用；1-&gt;指定分类；2-&gt;指定商品]" prop="useType">
                    <el-select v-model="queryParams.data.useType" placeholder="请选择使用类型[0-&gt;全场通用；1-&gt;指定分类；2-&gt;指定商品]" clearable>
                      <el-option
                          v-for="dict in dict.type.use_type"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="备注" prop="note">
                    <el-input
                        v-model="queryParams.data.note"
                        placeholder="请输入备注"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="发行数量" prop="publishCount">
                    <el-input
                        v-model="queryParams.data.publishCount"
                        placeholder="请输入发行数量"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="已使用数量" prop="useCount">
                    <el-input
                        v-model="queryParams.data.useCount"
                        placeholder="请输入已使用数量"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="领取数量" prop="receiveCount">
                    <el-input
                        v-model="queryParams.data.receiveCount"
                        placeholder="请输入领取数量"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="可以领取的开始日期" prop="enableStartTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.enableStartTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="请选择可以领取的开始日期">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="可以领取的结束日期" prop="enableEndTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.enableEndTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="请选择可以领取的结束日期">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="优惠码" prop="code">
                    <el-input
                        v-model="queryParams.data.code"
                        placeholder="请输入优惠码"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="可以领取的会员等级[0-&gt;不限等级，其他-对应等级]" prop="memberLevel">
                    <el-input
                        v-model="queryParams.data.memberLevel"
                        placeholder="请输入可以领取的会员等级[0-&gt;不限等级，其他-对应等级]"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="发布状态[0-未发布，1-已发布]" prop="publish">
                    <el-select v-model="queryParams.data.publish" placeholder="请选择发布状态[0-未发布，1-已发布]" clearable>
                      <el-option
                          v-for="dict in dict.type.publish"
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
            v-hasPermi="['coupon:coupon:add']"
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
            v-hasPermi="['coupon:coupon:edit']"
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
            v-hasPermi="['coupon:coupon:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['coupon:coupon:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="couponList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="优惠卷类型[0-&gt;全场赠券；1-&gt;会员赠券；2-&gt;购物赠券；3-&gt;注册赠券]" align="center" prop="couponType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.coupon_type" :value="scope.row.couponType"/>
                </template>
              </el-table-column>
              <el-table-column label="优惠券图片" align="center" prop="couponImg" width="100">
                <template slot-scope="scope">
                  <image-preview :src="scope.row.couponImg" :width="50" :height="50"/>
                </template>
              </el-table-column>
              <el-table-column label="优惠卷名字" align="center" prop="couponName" />
              <el-table-column label="数量" align="center" prop="num" />
              <el-table-column label="金额" align="center" prop="amount" />
              <el-table-column label="每人限领张数" align="center" prop="perLimit" />
              <el-table-column label="使用门槛" align="center" prop="minPoint" />
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
              <el-table-column label="使用类型[0-&gt;全场通用；1-&gt;指定分类；2-&gt;指定商品]" align="center" prop="useType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.use_type" :value="scope.row.useType"/>
                </template>
              </el-table-column>
              <el-table-column label="备注" align="center" prop="note" />
              <el-table-column label="发行数量" align="center" prop="publishCount" />
              <el-table-column label="已使用数量" align="center" prop="useCount" />
              <el-table-column label="领取数量" align="center" prop="receiveCount" />
              <el-table-column label="可以领取的开始日期" align="center" prop="enableStartTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.enableStartTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="可以领取的结束日期" align="center" prop="enableEndTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.enableEndTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="优惠码" align="center" prop="code" />
              <el-table-column label="可以领取的会员等级[0-&gt;不限等级，其他-对应等级]" align="center" prop="memberLevel" />
              <el-table-column label="发布状态[0-未发布，1-已发布]" align="center" prop="publish">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.publish" :value="scope.row.publish"/>
                </template>
              </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['coupon:coupon:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['coupon:coupon:remove']"
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

    <!-- 添加或修改优惠券信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="优惠卷类型[0-&gt;全场赠券；1-&gt;会员赠券；2-&gt;购物赠券；3-&gt;注册赠券]" prop="couponType">
                          <el-select v-model="form.couponType" placeholder="请选择优惠卷类型[0-&gt;全场赠券；1-&gt;会员赠券；2-&gt;购物赠券；3-&gt;注册赠券]">
                            <el-option
                                v-for="dict in dict.type.coupon_type"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="优惠券图片" prop="couponImg">
                          <image-upload v-model="form.couponImg" @upload-success="handleUploadSuccess"/>
                        </el-form-item>
                        <el-form-item label="优惠卷名字" prop="couponName">
                          <el-input v-model="form.couponName" placeholder="请输入优惠卷名字" />
                        </el-form-item>
                        <el-form-item label="数量" prop="num">
                          <el-input v-model="form.num" placeholder="请输入数量" />
                        </el-form-item>
                        <el-form-item label="金额" prop="amount">
                          <el-input v-model="form.amount" placeholder="请输入金额" />
                        </el-form-item>
                        <el-form-item label="每人限领张数" prop="perLimit">
                          <el-input v-model="form.perLimit" placeholder="请输入每人限领张数" />
                        </el-form-item>
                        <el-form-item label="使用门槛" prop="minPoint">
                          <el-input v-model="form.minPoint" placeholder="请输入使用门槛" />
                        </el-form-item>
                        <el-form-item label="开始时间" prop="startTime">
                          <el-date-picker clearable
                                          v-model="form.startTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择开始时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="结束时间" prop="endTime">
                          <el-date-picker clearable
                                          v-model="form.endTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择结束时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="使用类型[0-&gt;全场通用；1-&gt;指定分类；2-&gt;指定商品]" prop="useType">
                          <el-select v-model="form.useType" placeholder="请选择使用类型[0-&gt;全场通用；1-&gt;指定分类；2-&gt;指定商品]">
                            <el-option
                                v-for="dict in dict.type.use_type"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="备注" prop="note">
                          <el-input v-model="form.note" placeholder="请输入备注" />
                        </el-form-item>
                        <el-form-item label="发行数量" prop="publishCount">
                          <el-input v-model="form.publishCount" placeholder="请输入发行数量" />
                        </el-form-item>
                        <el-form-item label="已使用数量" prop="useCount">
                          <el-input v-model="form.useCount" placeholder="请输入已使用数量" />
                        </el-form-item>
                        <el-form-item label="领取数量" prop="receiveCount">
                          <el-input v-model="form.receiveCount" placeholder="请输入领取数量" />
                        </el-form-item>
                        <el-form-item label="可以领取的开始日期" prop="enableStartTime">
                          <el-date-picker clearable
                                          v-model="form.enableStartTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择可以领取的开始日期">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="可以领取的结束日期" prop="enableEndTime">
                          <el-date-picker clearable
                                          v-model="form.enableEndTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择可以领取的结束日期">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="优惠码" prop="code">
                          <el-input v-model="form.code" placeholder="请输入优惠码" />
                        </el-form-item>
                        <el-form-item label="可以领取的会员等级[0-&gt;不限等级，其他-对应等级]" prop="memberLevel">
                          <el-input v-model="form.memberLevel" placeholder="请输入可以领取的会员等级[0-&gt;不限等级，其他-对应等级]" />
                        </el-form-item>
                        <el-form-item label="发布状态[0-未发布，1-已发布]" prop="publish">
                          <el-select v-model="form.publish" placeholder="请选择发布状态[0-未发布，1-已发布]">
                            <el-option
                                v-for="dict in dict.type.publish"
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
      listCouponPage,
    getCoupon,
    delCoupon,
    addCoupon,
    updateCoupon ,
    exportCoupon
  } from "@/api/coupon/coupon";

  export default {
    name: "Coupon",
        dicts: ['coupon_type', 'use_type', 'publish'],
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
        // 优惠券信息表格数据
              couponList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
            data:{
                        couponType: null,
                        couponImg: null,
                        couponName: null,
                        num: null,
                        amount: null,
                        perLimit: null,
                        minPoint: null,
                        startTime: null,
                        endTime: null,
                        useType: null,
                        note: null,
                        publishCount: null,
                        useCount: null,
                        receiveCount: null,
                        enableStartTime: null,
                        enableEndTime: null,
                        code: null,
                        memberLevel: null,
                        publish: null
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
      /** 查询优惠券信息列表 */
      getList() {
        this.loading = true;
        listCouponPage(this.queryParams).then(response => {
          this.couponList = response.data.rows;
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
                        couponType: null,
                        couponImg: null,
                        couponName: null,
                        num: null,
                        amount: null,
                        perLimit: null,
                        minPoint: null,
                        startTime: null,
                        endTime: null,
                        useType: null,
                        note: null,
                        publishCount: null,
                        useCount: null,
                        receiveCount: null,
                        enableStartTime: null,
                        enableEndTime: null,
                        code: null,
                        memberLevel: null,
                        publish: null
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
        this.title = "添加优惠券信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getCoupon(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改优惠券信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateCoupon(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addCoupon(this.form).then(response => {
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
          return delCoupon(ids);
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
    return exportCoupon(ids);
    }).then(() => {
    this.$modal.msgSuccess("导出成功");
    this.getList();
    }).catch(() => {
    console.log("导出失败");
    });

  },
  handleUploadSuccess(fileName) {
    this.form.publish = fileName;
  },
  }
  };
</script>


