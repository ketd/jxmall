<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="等级名称" prop="name">
        <el-input
          v-model="queryParams.data.name"
          placeholder="请输入等级名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="等级需要的成长值" prop="growthPoint">
        <el-input
          v-model="queryParams.data.growthPoint"
          placeholder="请输入等级需要的成长值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为默认等级" prop="defaultStatus">
        <el-select v-model="queryParams.data.defaultStatus" placeholder="请选择是否为默认等级" clearable>
          <el-option
            v-for="dict in dict.type.default_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="免运费标准" prop="freeFreightPoint">
        <el-input
          v-model="queryParams.data.freeFreightPoint"
          placeholder="请输入免运费标准"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="每次评价获取的成长值" prop="commentGrowthPoint">
        <el-input
          v-model="queryParams.data.commentGrowthPoint"
          placeholder="请输入每次评价获取的成长值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否有免邮特权" prop="priviledgeFreeFreight">
        <el-select v-model="queryParams.data.priviledgeFreeFreight" placeholder="请选择是否有免邮特权" clearable>
          <el-option
            v-for="dict in dict.type.priviledge_free_freight"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否有会员价格特权" prop="priviledgeMemberPrice">
        <el-select v-model="queryParams.data.priviledgeMemberPrice" placeholder="请选择是否有会员价格特权" clearable>
          <el-option
            v-for="dict in dict.type.priviledge_member_price"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否有生日特权" prop="priviledgeBirthday">
        <el-select v-model="queryParams.data.priviledgeBirthday" placeholder="请选择是否有生日特权" clearable>
          <el-option
            v-for="dict in dict.type.priviledge_birthday"
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
          v-hasPermi="['member:memberLevel:add']"
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
          v-hasPermi="['member:memberLevel:edit']"
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
          v-hasPermi="['member:memberLevel:remove']"
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
          v-hasPermi="['member:memberLevel:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="memberLevelList" @selection-change="handleSelectionChange" :header-cell-style="{ textAlign: 'center' }">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id" align="center" prop="id"/>
      <el-table-column label="等级名称" align="center" prop="name"/>
      <el-table-column label="等级需要的成长值" align="center" prop="growthPoint"/>
      <el-table-column label="是否为默认等级" align="center" prop="defaultStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.default_status" :value="scope.row.defaultStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="免运费标准" align="center" prop="freeFreightPoint"/>
      <el-table-column label="每次评价获取的成长值" align="center" prop="commentGrowthPoint"/>
      <el-table-column label="特权" >
        <el-table-column label="是否有免邮特权" align="center" prop="priviledgeFreeFreight">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.priviledge_free_freight" :value="scope.row.priviledgeFreeFreight"/>
          </template>
        </el-table-column>
        <el-table-column label="是否有会员价格特权" align="center" prop="priviledgeMemberPrice">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.priviledge_member_price" :value="scope.row.priviledgeMemberPrice"/>
          </template>
        </el-table-column>
        <el-table-column label="是否有生日特权" align="center" prop="priviledgeBirthday">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.priviledge_birthday" :value="scope.row.priviledgeBirthday"/>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="note"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['member:memberLevel:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['member:memberLevel:remove']"
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

    <!-- 添加或修改会员等级对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="等级名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入等级名称"/>
        </el-form-item>
        <el-form-item label="等级需要的成长值" prop="growthPoint">
          <el-input v-model="form.growthPoint" placeholder="请输入等级需要的成长值"/>
        </el-form-item>
        <el-form-item label="是否为默认等级[0-&gt;不是；1-&gt;是]" prop="defaultStatus">
          <el-select v-model="form.defaultStatus" placeholder="请选择是否为默认等级[0-&gt;不是；1-&gt;是]">
            <el-option
              v-for="dict in dict.type.default_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="免运费标准" prop="freeFreightPoint">
          <el-input v-model="form.freeFreightPoint" placeholder="请输入免运费标准"/>
        </el-form-item>
        <el-form-item label="每次评价获取的成长值" prop="commentGrowthPoint">
          <el-input v-model="form.commentGrowthPoint" placeholder="请输入每次评价获取的成长值"/>
        </el-form-item>
        <el-form-item label="是否有免邮特权" prop="priviledgeFreeFreight">
          <el-select v-model="form.priviledgeFreeFreight" placeholder="请选择是否有免邮特权">
            <el-option
              v-for="dict in dict.type.priviledge_free_freight"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否有会员价格特权" prop="priviledgeMemberPrice">
          <el-select v-model="form.priviledgeMemberPrice" placeholder="请选择是否有会员价格特权">
            <el-option
              v-for="dict in dict.type.priviledge_member_price"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否有生日特权" prop="priviledgeBirthday">
          <el-select v-model="form.priviledgeBirthday" placeholder="请选择是否有生日特权">
            <el-option
              v-for="dict in dict.type.priviledge_birthday"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="form.note" placeholder="请输入备注"/>
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
  listMemberLevelPage,
  getMemberLevel,
  delMemberLevel,
  addMemberLevel,
  updateMemberLevel,
  exportMemberLevel
} from "@/api/member/memberLevel";

export default {
  name: "MemberLevel",
  dicts: ['priviledge_free_freight', 'default_status', 'priviledge_member_price', 'priviledge_birthday'],
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
      // 会员等级表格数据
      memberLevelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data: {
          name: null,
          growthPoint: null,
          defaultStatus: null,
          freeFreightPoint: null,
          commentGrowthPoint: null,
          priviledgeFreeFreight: null,
          priviledgeMemberPrice: null,
          priviledgeBirthday: null,
          note: null
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询会员等级列表 */
    getList() {
      this.loading = true;
      listMemberLevelPage(this.queryParams).then(response => {
        this.memberLevelList = response.data.rows;
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
        growthPoint: null,
        defaultStatus: null,
        freeFreightPoint: null,
        commentGrowthPoint: null,
        priviledgeFreeFreight: null,
        priviledgeMemberPrice: null,
        priviledgeBirthday: null,
        note: null
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
      this.title = "添加会员等级";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMemberLevel(id).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改会员等级";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMemberLevel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMemberLevel(this.form).then(response => {
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
        return delMemberLevel(ids);
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
        return exportMemberLevel(ids);
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


