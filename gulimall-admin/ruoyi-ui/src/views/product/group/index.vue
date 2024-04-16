<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            placeholder="请输入分组名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <tree @handleNodeClick="handleNodeClick"></tree>
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="组名" prop="attrGroupName">
            <el-input
              v-model="queryParams.data.attrGroupName"
              placeholder="请输入组名"
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
          <el-form-item label="描述" prop="descript">
            <el-input
              v-model="queryParams.data.descript"
              placeholder="请输入描述"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="所属分类id" prop="catelogId">
            <el-input
              v-model="queryParams.data.catelogId"
              placeholder="请输入所属分类"
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
              v-hasPermi="['product:group:add']"
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
              v-hasPermi="['product:group:edit']"
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
              v-hasPermi="['product:group:remove']"
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
              v-hasPermi="['product:group:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="groupList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="分组id" align="center" prop="attrGroupId"/>
          <el-table-column label="组名" align="center" prop="attrGroupName"/>
          <el-table-column label="排序" align="center" prop="sort"/>
          <el-table-column label="描述" align="center" prop="descript"/>
          <el-table-column label="组图标" align="center" prop="icon" width="100">
            <template slot-scope="scope">
              <image-preview :src="scope.row.icon" :width="50" :height="50"/>
            </template>
          </el-table-column>
          <el-table-column label="所属分类" align="center" prop="catelogId"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['product:group:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['product:group:remove']"
              >删除
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-s-order"
                @click="openAttrListDialog(scope.row)"
              >关联
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改属性分组对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="组名" prop="attrGroupName">
          <el-input v-model="form.attrGroupName" placeholder="请输入组名"/>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序"/>
        </el-form-item>
        <el-form-item label="描述" prop="descript">
          <el-input v-model="form.descript" placeholder="请输入描述"/>
        </el-form-item>
        <el-form-item label="组图标" prop="icon">
          <image-upload v-model="form.icon" @upload-success="handleUploadSuccess"/>
        </el-form-item>
        <el-form-item label="所属分类" prop="catelogId">
<!--          <el-input v-model="form.catelogId" placeholder="请输入所属分类id"/>-->
          <el-cascader
            v-model="catelogIds"
            :options="options"
            :props="props"
           >
          </el-cascader>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 属性分组对话框 -->
    <el-dialog :title="attributeGroupTitle" :visible.sync="isOpenAttrList" width="500px" append-to-body>
      <el-button
        :disabled="catelogIds.length===0"
        style="margin-bottom: 10px"
        type="primary" icon="el-icon-edit"
        @click=""
      >新建关联
      </el-button>
      <el-button
        type="danger"
        plain
        icon="el-icon-delete"
        size="mini"
        :disabled="multiple"
        @click="handleDelete"
      >批量删除
      </el-button>
      <!-- TODO 获取品牌分类 -->
      <el-table v-loading="loading" :data="attrList" @selection-change="handleAttrListSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="#" align="center" prop="attrId" />
        <el-table-column label="属性名" align="center" prop="attrName" />
        <el-table-column label="可选值" align="center" prop="valueSelect" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
            >修改
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
            >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>

      <pagination
        v-show="attrTotals>0"
        :total="attrTotals"
        :page.sync="getAttrListQueryParams.pageNum"
        :limit.sync="getAttrListQueryParams.pageSize"
        @pagination="getAttrList"
      />
    </el-dialog>


  </div>
</template>

<script>
import {
  listGroupPage,
  getGroup,
  delGroup,
  addGroup,
  updateGroup,
  exportGroup
} from "@/api/product/group";
import tree from "@/views/commoin/tree.vue";
import {getMenus} from "@/api/commoin/commoin";
import {listAttrPage} from "@/api/product/attr";

export default {
  components: {
    tree
  },
  name: "Group",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      attrIds: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 规格参数条数
      attrTotals: 0,
      // 属性分组表格数据
      groupList: [],
      // 商品属性表格数据
      attrList: [],
      // 弹出层标题
      title: "",
      attributeGroupTitle: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data: {
          attrGroupName: null,
          sort: null,
          descript: null,
          icon: null,
          catelogId: null,
        }
      },
      getAttrListQueryParams :{
        pageNum: 1,
        pageSize: 10,
        data:{
          attrGroupId : null,
        }
      },
      // 表单参数
      form: {},
      attrForm: {},
      // 表单校验
      rules: {},
      options: [],
      props: {
        value: 'catId',
        label: 'name',
        children: 'children'
      },
      catelogIds: [],
      isOpenAttrList: false,
    };
  },
  created() {
    this.getList();
    this.getMenus();
  },
  methods: {
    /** 查询属性分组列表 */
    getList() {
      this.loading = true;
      listGroupPage(this.queryParams).then(response => {
        this.groupList = response.data.rows;
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
        attrGroupId: null,
        attrGroupName: null,
        sort: null,
        descript: null,
        icon: null,
        catelogId: null,
      };
      this.catelogIds=[]
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
      this.ids = selection.map(item => item.attrGroupId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加属性分组";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const attrGroupId = row.attrGroupId || this.ids
      getGroup(attrGroupId).then(response => {
        this.form = response.data.data;
        this.catelogIds = response.data.data.catelogPath;
        this.open = true;
        this.title = "修改属性分组";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.attrGroupId != null) {
            this.form.catelogId = this.catelogIds[2];
            updateGroup(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.catelogId = this.catelogIds[2];
            addGroup(this.form).then(response => {
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
      const attrGroupIds = [].concat(row.attrGroupId !== undefined ? row.attrGroupId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认删除商品属性编号为"' + attrGroupIds.join(', ') + '"的数据项？').then(() => {
        return delGroup(attrGroupIds);
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
      const attrGroupIds = [].concat(row.attrGroupId !== undefined ? row.attrGroupId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认导出商品属性编号为"' + attrGroupIds.join(', ') + '"的数据项？').then(() => {
        return exportGroup(attrGroupIds);
      }).then(() => {
        this.$modal.msgSuccess("导出成功");
        this.getList();
      }).catch(() => {
        console.log("导出失败");
      });

    },
    handleUploadSuccess(fileName) {
      this.form.icon = fileName;
    },
    handleNodeClick(data, node, component) {
      if(node.level === 3){
        this.queryParams.data.catelogId = data.catId;
        this.handleQuery();
      }
    },
    getMenus(){
      getMenus().then(response => {
        this.options = response.data.data;
        console.log(this.options);
     }).catch(error => {

     }).finally(() => {

     });
    },
    openAttrListDialog(row){
      this.getAttrListQueryParams.data.attrGroupId = row.attrGroupId;
      this.isOpenAttrList = true;
      this.attributeGroupTitle = "规格参数";
      this.attrList= [];
      this.getAttrList();
    },
    // 多选框选中数据
    handleAttrListSelectionChange(selection) {
      this.attrIds = selection.map(item => item.attrGroupId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    getAttrList(){
      console.log(this.getAttrListQueryParams);
      listAttrPage(this.getAttrListQueryParams).then(response => {
        this.attrList = response.data.rows;
        this.attrTotals = response.data.total;
      });
    }
  }
};
</script>


