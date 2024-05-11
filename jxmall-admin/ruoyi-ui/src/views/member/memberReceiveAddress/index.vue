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
                  <el-form-item label="收货人姓名" prop="name">
                    <el-input
                        v-model="queryParams.data.name"
                        placeholder="请输入收货人姓名"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="电话" prop="phone">
                    <el-input
                        v-model="queryParams.data.phone"
                        placeholder="请输入电话"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="邮政编码" prop="postCode">
                    <el-input
                        v-model="queryParams.data.postCode"
                        placeholder="请输入邮政编码"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="省份/直辖市" prop="province">
                    <el-input
                        v-model="queryParams.data.province"
                        placeholder="请输入省份/直辖市"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="城市" prop="city">
                    <el-input
                        v-model="queryParams.data.city"
                        placeholder="请输入城市"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="区" prop="region">
                    <el-input
                        v-model="queryParams.data.region"
                        placeholder="请输入区"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="详细地址(街道)" prop="detailAddress">
                    <el-input
                        v-model="queryParams.data.detailAddress"
                        placeholder="请输入详细地址(街道)"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="省市区代码" prop="areacode">
                    <el-input
                        v-model="queryParams.data.areacode"
                        placeholder="请输入省市区代码"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="是否默认" prop="defaultStatus">
                    <el-select v-model="queryParams.data.defaultStatus" placeholder="请选择是否默认" clearable>
                      <el-option
                          v-for="dict in dict.type.default_status"
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
            v-hasPermi="['member:memberReceiveAddress:add']"
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
            v-hasPermi="['member:memberReceiveAddress:edit']"
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
            v-hasPermi="['member:memberReceiveAddress:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['member:memberReceiveAddress:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="memberReceiveAddressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="member_id" align="center" prop="memberId" />
              <el-table-column label="收货人姓名" align="center" prop="name" />
              <el-table-column label="电话" align="center" prop="phone" />
              <el-table-column label="邮政编码" align="center" prop="postCode" />
              <el-table-column label="省份/直辖市" align="center" prop="province" />
              <el-table-column label="城市" align="center" prop="city" />
              <el-table-column label="区" align="center" prop="region" />
              <el-table-column label="详细地址(街道)" align="center" prop="detailAddress" />
              <el-table-column label="省市区代码" align="center" prop="areacode" />
              <el-table-column label="是否默认" align="center" prop="defaultStatus">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.default_status" :value="scope.row.defaultStatus"/>
                </template>
              </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['member:memberReceiveAddress:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['member:memberReceiveAddress:remove']"
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

    <!-- 添加或修改会员收货地址对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="member_id" prop="memberId">
                          <el-input v-model="form.memberId" placeholder="请输入member_id" />
                        </el-form-item>
                        <el-form-item label="收货人姓名" prop="name">
                          <el-input v-model="form.name" placeholder="请输入收货人姓名" />
                        </el-form-item>
                        <el-form-item label="电话" prop="phone">
                          <el-input v-model="form.phone" placeholder="请输入电话" />
                        </el-form-item>
                        <el-form-item label="邮政编码" prop="postCode">
                          <el-input v-model="form.postCode" placeholder="请输入邮政编码" />
                        </el-form-item>
                        <el-form-item label="省份/直辖市" prop="province">
                          <el-input v-model="form.province" placeholder="请输入省份/直辖市" />
                        </el-form-item>
                        <el-form-item label="城市" prop="city">
                          <el-input v-model="form.city" placeholder="请输入城市" />
                        </el-form-item>
                        <el-form-item label="区" prop="region">
                          <el-input v-model="form.region" placeholder="请输入区" />
                        </el-form-item>
                        <el-form-item label="详细地址(街道)" prop="detailAddress">
                          <el-input v-model="form.detailAddress" placeholder="请输入详细地址(街道)" />
                        </el-form-item>
                        <el-form-item label="省市区代码" prop="areacode">
                          <el-input v-model="form.areacode" placeholder="请输入省市区代码" />
                        </el-form-item>
                        <el-form-item label="是否默认" prop="defaultStatus">
                          <el-select v-model="form.defaultStatus" placeholder="请选择是否默认">
                            <el-option
                                v-for="dict in dict.type.default_status"
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
      listMemberReceiveAddressPage,
    getMemberReceiveAddress,
    delMemberReceiveAddress,
    addMemberReceiveAddress,
    updateMemberReceiveAddress ,
    exportMemberReceiveAddress
  } from "@/api/member/memberReceiveAddress";

  export default {
    name: "MemberReceiveAddress",
        dicts: ['default_status'],
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
        // 会员收货地址表格数据
              memberReceiveAddressList: [],
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
                        name: null,
                        phone: null,
                        postCode: null,
                        province: null,
                        city: null,
                        region: null,
                        detailAddress: null,
                        areacode: null,
                        defaultStatus: null
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
      /** 查询会员收货地址列表 */
      getList() {
        this.loading = true;
        listMemberReceiveAddressPage(this.queryParams).then(response => {
          this.memberReceiveAddressList = response.data.rows;
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
                        name: null,
                        phone: null,
                        postCode: null,
                        province: null,
                        city: null,
                        region: null,
                        detailAddress: null,
                        areacode: null,
                        defaultStatus: null
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
        this.title = "添加会员收货地址";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getMemberReceiveAddress(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改会员收货地址";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateMemberReceiveAddress(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addMemberReceiveAddress(this.form).then(response => {
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
          return delMemberReceiveAddress(ids);
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
    return exportMemberReceiveAddress(ids);
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


