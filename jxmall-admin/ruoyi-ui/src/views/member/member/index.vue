<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                  <el-form-item label="会员等级id" prop="levelId">
                    <el-input
                        v-model="queryParams.data.levelId"
                        placeholder="请输入会员等级id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="用户名" prop="username">
                    <el-input
                        v-model="queryParams.data.username"
                        placeholder="请输入用户名"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="密码" prop="password">
                    <el-input
                        v-model="queryParams.data.password"
                        placeholder="请输入密码"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="昵称" prop="nickname">
                    <el-input
                        v-model="queryParams.data.nickname"
                        placeholder="请输入昵称"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="手机号码" prop="mobile">
                    <el-input
                        v-model="queryParams.data.mobile"
                        placeholder="请输入手机号码"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="邮箱" prop="email">
                    <el-input
                        v-model="queryParams.data.email"
                        placeholder="请输入邮箱"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="性别" prop="gender">
                    <el-input
                        v-model="queryParams.data.gender"
                        placeholder="请输入性别"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="生日" prop="birth">
                    <el-date-picker clearable
                                    v-model="queryParams.data.birth"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="请选择生日">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="职业" prop="job">
                    <el-input
                        v-model="queryParams.data.job"
                        placeholder="请输入职业"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="个性签名" prop="sign">
                    <el-input
                        v-model="queryParams.data.sign"
                        placeholder="请输入个性签名"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="积分" prop="integration">
                    <el-input
                        v-model="queryParams.data.integration"
                        placeholder="请输入积分"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="成长值" prop="growth">
                    <el-input
                        v-model="queryParams.data.growth"
                        placeholder="请输入成长值"
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
            v-hasPermi="['member:member:add']"
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
            v-hasPermi="['member:member:edit']"
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
            v-hasPermi="['member:member:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['member:member:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="memberList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="会员等级id" align="center" prop="levelId" />
              <el-table-column label="用户名" align="center" prop="username" />
              <el-table-column label="密码" align="center" prop="password" />
              <el-table-column label="昵称" align="center" prop="nickname" />
              <el-table-column label="手机号码" align="center" prop="mobile" />
              <el-table-column label="邮箱" align="center" prop="email" />
              <el-table-column label="头像" align="center" prop="header" />
              <el-table-column label="性别" align="center" prop="gender" />
              <el-table-column label="生日" align="center" prop="birth" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.birth, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="所在城市" align="center" prop="city" />
              <el-table-column label="职业" align="center" prop="job" />
              <el-table-column label="个性签名" align="center" prop="sign" />
              <el-table-column label="用户来源" align="center" prop="sourceType" />
              <el-table-column label="积分" align="center" prop="integration" />
              <el-table-column label="成长值" align="center" prop="growth" />
              <el-table-column label="启用状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['member:member:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['member:member:remove']"
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

    <!-- 添加或修改会员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="会员等级id" prop="levelId">
                          <el-input v-model="form.levelId" placeholder="请输入会员等级id" />
                        </el-form-item>
                        <el-form-item label="用户名" prop="username">
                          <el-input v-model="form.username" placeholder="请输入用户名" />
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                          <el-input v-model="form.password" placeholder="请输入密码" />
                        </el-form-item>
                        <el-form-item label="昵称" prop="nickname">
                          <el-input v-model="form.nickname" placeholder="请输入昵称" />
                        </el-form-item>
                        <el-form-item label="手机号码" prop="mobile">
                          <el-input v-model="form.mobile" placeholder="请输入手机号码" />
                        </el-form-item>
                        <el-form-item label="邮箱" prop="email">
                          <el-input v-model="form.email" placeholder="请输入邮箱" />
                        </el-form-item>
                        <el-form-item label="头像" prop="header">
                          <el-input v-model="form.header" type="textarea" placeholder="请输入内容" />
                        </el-form-item>
                        <el-form-item label="性别" prop="gender">
                          <el-input v-model="form.gender" placeholder="请输入性别" />
                        </el-form-item>
                        <el-form-item label="生日" prop="birth">
                          <el-date-picker clearable
                                          v-model="form.birth"
                                          type="date"
                                          value-format="yyyy-MM-dd"
                                          placeholder="请选择生日">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="所在城市" prop="city">
                          <el-input v-model="form.city" type="textarea" placeholder="请输入内容" />
                        </el-form-item>
                        <el-form-item label="职业" prop="job">
                          <el-input v-model="form.job" placeholder="请输入职业" />
                        </el-form-item>
                        <el-form-item label="个性签名" prop="sign">
                          <el-input v-model="form.sign" placeholder="请输入个性签名" />
                        </el-form-item>
                        <el-form-item label="积分" prop="integration">
                          <el-input v-model="form.integration" placeholder="请输入积分" />
                        </el-form-item>
                        <el-form-item label="成长值" prop="growth">
                          <el-input v-model="form.growth" placeholder="请输入成长值" />
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
      listMemberPage,
    getMember,
    delMember,
    addMember,
    updateMember ,
    exportMember
  } from "@/api/member/member";

  export default {
    name: "Member",
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
        // 会员表格数据
              memberList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
            data:{
                        levelId: null,
                        username: null,
                        password: null,
                        nickname: null,
                        mobile: null,
                        email: null,
                        header: null,
                        gender: null,
                        birth: null,
                        city: null,
                        job: null,
                        sign: null,
                        sourceType: null,
                        integration: null,
                        growth: null,
                        status: null,
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
      /** 查询会员列表 */
      getList() {
        this.loading = true;
        listMemberPage(this.queryParams).then(response => {
          this.memberList = response.data.rows;
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
                        levelId: null,
                        username: null,
                        password: null,
                        nickname: null,
                        mobile: null,
                        email: null,
                        header: null,
                        gender: null,
                        birth: null,
                        city: null,
                        job: null,
                        sign: null,
                        sourceType: null,
                        integration: null,
                        growth: null,
                        status: null,
                        createTime: null
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
        this.title = "添加会员";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getMember(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改会员";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateMember(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addMember(this.form).then(response => {
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
          return delMember(ids);
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
    return exportMember(ids);
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


