<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社交账号uid" prop="socialUid">
        <el-input
          v-model="queryParams.data.socialUid"
          placeholder="请输入社交账号uid"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="token" prop="accessToken">
        <el-input
          v-model="queryParams.data.accessToken"
          placeholder="请输入token"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="过期时间" prop="exporesIn">
        <el-input
          v-model="queryParams.data.exporesIn"
          placeholder="请输入过期时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="头像地址" prop="avatarUrl">
        <el-input
          v-model="queryParams.data.avatarUrl"
          placeholder="请输入头像地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="昵称" prop="name">
        <el-input
          v-model="queryParams.data.name"
          placeholder="请输入昵称"
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
          v-hasPermi="['member:social:add']"
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
          v-hasPermi="['member:social:edit']"
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
          v-hasPermi="['member:social:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['member:social:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="socialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户id" align="center" prop="memberId" />
      <el-table-column label="社交账号uid" align="center" prop="socialUid" />
      <el-table-column label="token" align="center" prop="accessToken" />
      <el-table-column label="过期时间" align="center" prop="exporesIn" />
      <el-table-column label="头像地址" align="center" prop="avatarUrl" />
      <el-table-column label="昵称" align="center" prop="name" />
      <el-table-column label="登录平台网站类型" align="center" prop="type" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['member:social:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['member:social:remove']"
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

    <!-- 添加或修改单点登录用户信息关联对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="社交账号uid" prop="socialUid">
          <el-input v-model="form.socialUid" placeholder="请输入社交账号uid" />
        </el-form-item>
        <el-form-item label="token" prop="accessToken">
          <el-input v-model="form.accessToken" placeholder="请输入token" />
        </el-form-item>
        <el-form-item label="过期时间" prop="exporesIn">
          <el-input v-model="form.exporesIn" placeholder="请输入过期时间" />
        </el-form-item>
        <el-form-item label="头像地址" prop="avatarUrl">
          <el-input v-model="form.avatarUrl" placeholder="请输入头像地址" />
        </el-form-item>
        <el-form-item label="昵称" prop="name">
          <el-input v-model="form.name" placeholder="请输入昵称" />
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
  listSocialPage,
  getSocial,
  delSocial,
  addSocial,
  updateSocial ,
  exportSocial
} from "@/api/member/social";

export default {
  name: "Social",
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
      // 单点登录用户信息关联表格数据
      socialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data:{
          socialUid: null,
          accessToken: null,
          exporesIn: null,
          avatarUrl: null,
          name: null,
          type: null
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        socialUid: [
          { required: true, message: "社交账号uid不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询单点登录用户信息关联列表 */
    getList() {
      this.loading = true;
      listSocialPage(this.queryParams).then(response => {
        this.socialList = response.data.rows;
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
        memberId: null,
        socialUid: null,
        accessToken: null,
        exporesIn: null,
        avatarUrl: null,
        name: null,
        type: null
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
      this.ids = selection.map(item => item.memberId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加单点登录用户信息关联";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const memberId = row.memberId || this.ids
      getSocial(memberId).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改单点登录用户信息关联";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.memberId != null) {
            updateSocial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSocial(this.form).then(response => {
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
      const memberIds = [].concat(row.memberId !== undefined ? row.memberId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认删除商品属性编号为"' + memberIds.join(', ') + '"的数据项？').then(() => {
        return delSocial(memberIds);
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
      const memberIds = [].concat(row.memberId !== undefined ? row.memberId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认导出商品属性编号为"' + memberIds.join(', ') + '"的数据项？').then(() => {
        return exportSocial(memberIds);
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


