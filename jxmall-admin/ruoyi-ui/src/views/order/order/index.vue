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
                  <el-form-item label="订单号" prop="orderSn">
                    <el-input
                        v-model="queryParams.data.orderSn"
                        placeholder="请输入订单号"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="使用的优惠券" prop="couponId">
                    <el-input
                        v-model="queryParams.data.couponId"
                        placeholder="请输入使用的优惠券"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="用户名" prop="memberUsername">
                    <el-input
                        v-model="queryParams.data.memberUsername"
                        placeholder="请输入用户名"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="订单总额" prop="totalAmount">
                    <el-input
                        v-model="queryParams.data.totalAmount"
                        placeholder="请输入订单总额"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="应付总额" prop="payAmount">
                    <el-input
                        v-model="queryParams.data.payAmount"
                        placeholder="请输入应付总额"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="运费金额" prop="freightAmount">
                    <el-input
                        v-model="queryParams.data.freightAmount"
                        placeholder="请输入运费金额"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="促销优化金额" prop="promotionAmount">
                    <el-input
                        v-model="queryParams.data.promotionAmount"
                        placeholder="请输入促销优化金额"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="积分抵扣金额" prop="integrationAmount">
                    <el-input
                        v-model="queryParams.data.integrationAmount"
                        placeholder="请输入积分抵扣金额"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="优惠券抵扣金额" prop="couponAmount">
                    <el-input
                        v-model="queryParams.data.couponAmount"
                        placeholder="请输入优惠券抵扣金额"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="后台调整订单使用的折扣金额" prop="discountAmount">
                    <el-input
                        v-model="queryParams.data.discountAmount"
                        placeholder="请输入后台调整订单使用的折扣金额"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="支付方式【1-&gt;支付宝；2-&gt;微信；3-&gt;银联； 4-&gt;货到付款；】" prop="payType">
                    <el-select v-model="queryParams.data.payType" placeholder="请选择支付方式【1-&gt;支付宝；2-&gt;微信；3-&gt;银联； 4-&gt;货到付款；】" clearable>
                      <el-option
                          v-for="dict in dict.type.pay_type"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="订单来源[0-&gt;PC订单；1-&gt;app订单]" prop="sourceType">
                    <el-select v-model="queryParams.data.sourceType" placeholder="请选择订单来源[0-&gt;PC订单；1-&gt;app订单]" clearable>
                      <el-option
                          v-for="dict in dict.type.order_source_type"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】" prop="status">
                    <el-select v-model="queryParams.data.status" placeholder="请选择订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】" clearable>
                      <el-option
                          v-for="dict in dict.type.status"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="物流公司(配送方式)" prop="deliveryCompany">
                    <el-input
                        v-model="queryParams.data.deliveryCompany"
                        placeholder="请输入物流公司(配送方式)"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="物流单号" prop="deliverySn">
                    <el-input
                        v-model="queryParams.data.deliverySn"
                        placeholder="请输入物流单号"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="自动确认时间" prop="autoConfirmDay">
                    <el-input
                        v-model="queryParams.data.autoConfirmDay"
                        placeholder="请输入自动确认时间"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="可以获得的积分" prop="integration">
                    <el-input
                        v-model="queryParams.data.integration"
                        placeholder="请输入可以获得的积分"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="可以获得的成长值" prop="growth">
                    <el-input
                        v-model="queryParams.data.growth"
                        placeholder="请输入可以获得的成长值"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="发票类型[0-&gt;不开发票；1-&gt;电子发票；2-&gt;纸质发票]" prop="billType">
                    <el-select v-model="queryParams.data.billType" placeholder="请选择发票类型[0-&gt;不开发票；1-&gt;电子发票；2-&gt;纸质发票]" clearable>
                      <el-option
                          v-for="dict in dict.type.bill_type"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="发票抬头" prop="billHeader">
                    <el-input
                        v-model="queryParams.data.billHeader"
                        placeholder="请输入发票抬头"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="收票人电话" prop="billReceiverPhone">
                    <el-input
                        v-model="queryParams.data.billReceiverPhone"
                        placeholder="请输入收票人电话"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="收票人邮箱" prop="billReceiverEmail">
                    <el-input
                        v-model="queryParams.data.billReceiverEmail"
                        placeholder="请输入收票人邮箱"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="收货人姓名" prop="receiverName">
                    <el-input
                        v-model="queryParams.data.receiverName"
                        placeholder="请输入收货人姓名"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="收货人电话" prop="receiverPhone">
                    <el-input
                        v-model="queryParams.data.receiverPhone"
                        placeholder="请输入收货人电话"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="收货人邮编" prop="receiverPostCode">
                    <el-input
                        v-model="queryParams.data.receiverPostCode"
                        placeholder="请输入收货人邮编"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="省份/直辖市" prop="receiverProvince">
                    <el-input
                        v-model="queryParams.data.receiverProvince"
                        placeholder="请输入省份/直辖市"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="城市" prop="receiverCity">
                    <el-input
                        v-model="queryParams.data.receiverCity"
                        placeholder="请输入城市"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="区" prop="receiverRegion">
                    <el-input
                        v-model="queryParams.data.receiverRegion"
                        placeholder="请输入区"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="详细地址" prop="receiverDetailAddress">
                    <el-input
                        v-model="queryParams.data.receiverDetailAddress"
                        placeholder="请输入详细地址"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="确认收货状态[0-&gt;未确认；1-&gt;已确认]" prop="confirmStatus">
                    <el-select v-model="queryParams.data.confirmStatus" placeholder="请选择确认收货状态[0-&gt;未确认；1-&gt;已确认]" clearable>
                      <el-option
                          v-for="dict in dict.type.confirm_status"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="删除状态【0-&gt;未删除；1-&gt;已删除】" prop="deleteStatus">
                    <el-select v-model="queryParams.data.deleteStatus" placeholder="请选择删除状态【0-&gt;未删除；1-&gt;已删除】" clearable>
                      <el-option
                          v-for="dict in dict.type.delete_status"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="下单时使用的积分" prop="useIntegration">
                    <el-input
                        v-model="queryParams.data.useIntegration"
                        placeholder="请输入下单时使用的积分"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="支付时间" prop="paymentTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.paymentTime"
                                    type="date"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="请选择支付时间">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="发货时间" prop="deliveryTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.deliveryTime"
                                    type="date"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="请选择发货时间">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="确认收货时间" prop="receiveTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.receiveTime"
                                    type="date"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="请选择确认收货时间">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="评价时间" prop="commentTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.commentTime"
                                    type="date"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="请选择评价时间">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="修改时间" prop="modifyTime">
                    <el-date-picker clearable
                                    v-model="queryParams.data.modifyTime"
                                    type="date"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="请选择修改时间">
                    </el-date-picker>
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
            v-hasPermi="['order:order:add']"
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
            v-hasPermi="['order:order:edit']"
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
            v-hasPermi="['order:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['order:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="member_id" align="center" prop="memberId" />
              <el-table-column label="订单号" align="center" prop="orderSn" />
              <el-table-column label="使用的优惠券" align="center" prop="couponId" />
              <el-table-column label="用户名" align="center" prop="memberUsername" />
              <el-table-column label="订单总额" align="center" prop="totalAmount" />
              <el-table-column label="应付总额" align="center" prop="payAmount" />
              <el-table-column label="运费金额" align="center" prop="freightAmount" />
              <el-table-column label="促销优化金额" align="center" prop="promotionAmount" />
              <el-table-column label="积分抵扣金额" align="center" prop="integrationAmount" />
              <el-table-column label="优惠券抵扣金额" align="center" prop="couponAmount" />
              <el-table-column label="后台调整订单使用的折扣金额" align="center" prop="discountAmount" />
              <el-table-column label="支付方式【1-&gt;支付宝；2-&gt;微信；3-&gt;银联； 4-&gt;货到付款；】" align="center" prop="payType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.pay_type" :value="scope.row.payType"/>
                </template>
              </el-table-column>
              <el-table-column label="订单来源[0-&gt;PC订单；1-&gt;app订单]" align="center" prop="sourceType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.order_source_type" :value="scope.row.sourceType"/>
                </template>
              </el-table-column>
              <el-table-column label="订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】" align="center" prop="status">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.status" :value="scope.row.status"/>
                </template>
              </el-table-column>
              <el-table-column label="物流公司(配送方式)" align="center" prop="deliveryCompany" />
              <el-table-column label="物流单号" align="center" prop="deliverySn" />
              <el-table-column label="自动确认时间" align="center" prop="autoConfirmDay" />
              <el-table-column label="可以获得的积分" align="center" prop="integration" />
              <el-table-column label="可以获得的成长值" align="center" prop="growth" />
              <el-table-column label="发票类型[0-&gt;不开发票；1-&gt;电子发票；2-&gt;纸质发票]" align="center" prop="billType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.bill_type" :value="scope.row.billType"/>
                </template>
              </el-table-column>
              <el-table-column label="发票抬头" align="center" prop="billHeader" />
              <el-table-column label="发票内容" align="center" prop="billContent" />
              <el-table-column label="收票人电话" align="center" prop="billReceiverPhone" />
              <el-table-column label="收票人邮箱" align="center" prop="billReceiverEmail" />
              <el-table-column label="收货人姓名" align="center" prop="receiverName" />
              <el-table-column label="收货人电话" align="center" prop="receiverPhone" />
              <el-table-column label="收货人邮编" align="center" prop="receiverPostCode" />
              <el-table-column label="省份/直辖市" align="center" prop="receiverProvince" />
              <el-table-column label="城市" align="center" prop="receiverCity" />
              <el-table-column label="区" align="center" prop="receiverRegion" />
              <el-table-column label="详细地址" align="center" prop="receiverDetailAddress" />
              <el-table-column label="订单备注" align="center" prop="note" />
              <el-table-column label="确认收货状态[0-&gt;未确认；1-&gt;已确认]" align="center" prop="confirmStatus">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.confirm_status" :value="scope.row.confirmStatus"/>
                </template>
              </el-table-column>
              <el-table-column label="删除状态【0-&gt;未删除；1-&gt;已删除】" align="center" prop="deleteStatus">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.delete_status" :value="scope.row.deleteStatus"/>
                </template>
              </el-table-column>
              <el-table-column label="下单时使用的积分" align="center" prop="useIntegration" />
              <el-table-column label="支付时间" align="center" prop="paymentTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.paymentTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="发货时间" align="center" prop="deliveryTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.deliveryTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="确认收货时间" align="center" prop="receiveTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.receiveTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="评价时间" align="center" prop="commentTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.commentTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="修改时间" align="center" prop="modifyTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.modifyTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['order:order:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['order:order:remove']"
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

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="member_id" prop="memberId">
                          <el-input v-model="form.memberId" placeholder="请输入member_id" />
                        </el-form-item>
                        <el-form-item label="订单号" prop="orderSn">
                          <el-input v-model="form.orderSn" placeholder="请输入订单号" />
                        </el-form-item>
                        <el-form-item label="使用的优惠券" prop="couponId">
                          <el-input v-model="form.couponId" placeholder="请输入使用的优惠券" />
                        </el-form-item>
                        <el-form-item label="用户名" prop="memberUsername">
                          <el-input v-model="form.memberUsername" placeholder="请输入用户名" />
                        </el-form-item>
                        <el-form-item label="订单总额" prop="totalAmount">
                          <el-input v-model="form.totalAmount" placeholder="请输入订单总额" />
                        </el-form-item>
                        <el-form-item label="应付总额" prop="payAmount">
                          <el-input v-model="form.payAmount" placeholder="请输入应付总额" />
                        </el-form-item>
                        <el-form-item label="运费金额" prop="freightAmount">
                          <el-input v-model="form.freightAmount" placeholder="请输入运费金额" />
                        </el-form-item>
                        <el-form-item label="促销优化金额" prop="promotionAmount">
                          <el-input v-model="form.promotionAmount" placeholder="请输入促销优化金额" />
                        </el-form-item>
                        <el-form-item label="积分抵扣金额" prop="integrationAmount">
                          <el-input v-model="form.integrationAmount" placeholder="请输入积分抵扣金额" />
                        </el-form-item>
                        <el-form-item label="优惠券抵扣金额" prop="couponAmount">
                          <el-input v-model="form.couponAmount" placeholder="请输入优惠券抵扣金额" />
                        </el-form-item>
                        <el-form-item label="后台调整订单使用的折扣金额" prop="discountAmount">
                          <el-input v-model="form.discountAmount" placeholder="请输入后台调整订单使用的折扣金额" />
                        </el-form-item>
                        <el-form-item label="支付方式【1-&gt;支付宝；2-&gt;微信；3-&gt;银联； 4-&gt;货到付款；】" prop="payType">
                          <el-select v-model="form.payType" placeholder="请选择支付方式【1-&gt;支付宝；2-&gt;微信；3-&gt;银联； 4-&gt;货到付款；】">
                            <el-option
                                v-for="dict in dict.type.pay_type"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="订单来源[0-&gt;PC订单；1-&gt;app订单]" prop="sourceType">
                          <el-select v-model="form.sourceType" placeholder="请选择订单来源[0-&gt;PC订单；1-&gt;app订单]">
                            <el-option
                                v-for="dict in dict.type.order_source_type"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】" prop="status">
                          <el-select v-model="form.status" placeholder="请选择订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】">
                            <el-option
                                v-for="dict in dict.type.status"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="物流公司(配送方式)" prop="deliveryCompany">
                          <el-input v-model="form.deliveryCompany" placeholder="请输入物流公司(配送方式)" />
                        </el-form-item>
                        <el-form-item label="物流单号" prop="deliverySn">
                          <el-input v-model="form.deliverySn" placeholder="请输入物流单号" />
                        </el-form-item>
                        <el-form-item label="自动确认时间" prop="autoConfirmDay">
                          <el-input v-model="form.autoConfirmDay" placeholder="请输入自动确认时间" />
                        </el-form-item>
                        <el-form-item label="可以获得的积分" prop="integration">
                          <el-input v-model="form.integration" placeholder="请输入可以获得的积分" />
                        </el-form-item>
                        <el-form-item label="可以获得的成长值" prop="growth">
                          <el-input v-model="form.growth" placeholder="请输入可以获得的成长值" />
                        </el-form-item>
                        <el-form-item label="发票类型[0-&gt;不开发票；1-&gt;电子发票；2-&gt;纸质发票]" prop="billType">
                          <el-select v-model="form.billType" placeholder="请选择发票类型[0-&gt;不开发票；1-&gt;电子发票；2-&gt;纸质发票]">
                            <el-option
                                v-for="dict in dict.type.bill_type"
                                :key="dict.value"
                                :label="dict.label"
                                :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="发票抬头" prop="billHeader">
                          <el-input v-model="form.billHeader" placeholder="请输入发票抬头" />
                        </el-form-item>
                        <el-form-item label="发票内容">
                          <editor v-model="form.billContent" :min-height="192"/>
                        </el-form-item>
                        <el-form-item label="收票人电话" prop="billReceiverPhone">
                          <el-input v-model="form.billReceiverPhone" placeholder="请输入收票人电话" />
                        </el-form-item>
                        <el-form-item label="收票人邮箱" prop="billReceiverEmail">
                          <el-input v-model="form.billReceiverEmail" placeholder="请输入收票人邮箱" />
                        </el-form-item>
                        <el-form-item label="收货人姓名" prop="receiverName">
                          <el-input v-model="form.receiverName" placeholder="请输入收货人姓名" />
                        </el-form-item>
                        <el-form-item label="收货人电话" prop="receiverPhone">
                          <el-input v-model="form.receiverPhone" placeholder="请输入收货人电话" />
                        </el-form-item>
                        <el-form-item label="收货人邮编" prop="receiverPostCode">
                          <el-input v-model="form.receiverPostCode" placeholder="请输入收货人邮编" />
                        </el-form-item>
                        <el-form-item label="省份/直辖市" prop="receiverProvince">
                          <el-input v-model="form.receiverProvince" placeholder="请输入省份/直辖市" />
                        </el-form-item>
                        <el-form-item label="城市" prop="receiverCity">
                          <el-input v-model="form.receiverCity" placeholder="请输入城市" />
                        </el-form-item>
                        <el-form-item label="区" prop="receiverRegion">
                          <el-input v-model="form.receiverRegion" placeholder="请输入区" />
                        </el-form-item>
                        <el-form-item label="详细地址" prop="receiverDetailAddress">
                          <el-input v-model="form.receiverDetailAddress" placeholder="请输入详细地址" />
                        </el-form-item>
                        <el-form-item label="订单备注" prop="note">
                          <el-input v-model="form.note" type="textarea" placeholder="请输入内容" />
                        </el-form-item>
                        <el-form-item label="确认收货状态[0-&gt;未确认；1-&gt;已确认]" prop="confirmStatus">
                          <el-radio-group v-model="form.confirmStatus">
                            <el-radio
                                v-for="dict in dict.type.confirm_status"
                                :key="dict.value"
                                :label="parseInt(dict.value)"
                            >{{dict.label}}</el-radio>
                          </el-radio-group>
                        </el-form-item>
                        <el-form-item label="删除状态【0-&gt;未删除；1-&gt;已删除】" prop="deleteStatus">
                          <el-radio-group v-model="form.deleteStatus">
                            <el-radio
                                v-for="dict in dict.type.delete_status"
                                :key="dict.value"
                                :label="parseInt(dict.value)"
                            >{{dict.label}}</el-radio>
                          </el-radio-group>
                        </el-form-item>
                        <el-form-item label="下单时使用的积分" prop="useIntegration">
                          <el-input v-model="form.useIntegration" placeholder="请输入下单时使用的积分" />
                        </el-form-item>
                        <el-form-item label="支付时间" prop="paymentTime">
                          <el-date-picker clearable
                                          v-model="form.paymentTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择支付时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="发货时间" prop="deliveryTime">
                          <el-date-picker clearable
                                          v-model="form.deliveryTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择发货时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="确认收货时间" prop="receiveTime">
                          <el-date-picker clearable
                                          v-model="form.receiveTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择确认收货时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="评价时间" prop="commentTime">
                          <el-date-picker clearable
                                          v-model="form.commentTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择评价时间">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="修改时间" prop="modifyTime">
                          <el-date-picker clearable
                                          v-model="form.modifyTime"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择修改时间">
                          </el-date-picker>
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
      listOrderPage,
    getOrder,
    delOrder,
    addOrder,
    updateOrder ,
    exportOrder
  } from "@/api/order/order";

  export default {
    name: "Order",
        dicts: ['bill_type', 'pay_type', 'status', 'delete_status', 'order_source_type', 'confirm_status'],
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
        // 订单表格数据
              orderList: [],
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
                        orderSn: null,
                        couponId: null,
                        memberUsername: null,
                        totalAmount: null,
                        payAmount: null,
                        freightAmount: null,
                        promotionAmount: null,
                        integrationAmount: null,
                        couponAmount: null,
                        discountAmount: null,
                        payType: null,
                        sourceType: null,
                        status: null,
                        deliveryCompany: null,
                        deliverySn: null,
                        autoConfirmDay: null,
                        integration: null,
                        growth: null,
                        billType: null,
                        billHeader: null,
                        billContent: null,
                        billReceiverPhone: null,
                        billReceiverEmail: null,
                        receiverName: null,
                        receiverPhone: null,
                        receiverPostCode: null,
                        receiverProvince: null,
                        receiverCity: null,
                        receiverRegion: null,
                        receiverDetailAddress: null,
                        note: null,
                        confirmStatus: null,
                        deleteStatus: null,
                        useIntegration: null,
                        paymentTime: null,
                        deliveryTime: null,
                        receiveTime: null,
                        commentTime: null,
                        modifyTime: null
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
      /** 查询订单列表 */
      getList() {
        this.loading = true;
        listOrderPage(this.queryParams).then(response => {
          this.orderList = response.data.rows;
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
                        orderSn: null,
                        couponId: null,
                        createTime: null,
                        memberUsername: null,
                        totalAmount: null,
                        payAmount: null,
                        freightAmount: null,
                        promotionAmount: null,
                        integrationAmount: null,
                        couponAmount: null,
                        discountAmount: null,
                        payType: null,
                        sourceType: null,
                        status: null,
                        deliveryCompany: null,
                        deliverySn: null,
                        autoConfirmDay: null,
                        integration: null,
                        growth: null,
                        billType: null,
                        billHeader: null,
                        billContent: null,
                        billReceiverPhone: null,
                        billReceiverEmail: null,
                        receiverName: null,
                        receiverPhone: null,
                        receiverPostCode: null,
                        receiverProvince: null,
                        receiverCity: null,
                        receiverRegion: null,
                        receiverDetailAddress: null,
                        note: null,
                        confirmStatus: null,
                        deleteStatus: null,
                        useIntegration: null,
                        paymentTime: null,
                        deliveryTime: null,
                        receiveTime: null,
                        commentTime: null,
                        modifyTime: null
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
        this.title = "添加订单";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getOrder(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改订单";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateOrder(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addOrder(this.form).then(response => {
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
          return delOrder(ids);
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
    return exportOrder(ids);
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


