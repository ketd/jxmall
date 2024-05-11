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
<!--          <el-table-column label="组图标" align="center" prop="icon" width="100">
            <template slot-scope="scope">
              <image-preview :src="scope.row.icon" :width="50" :height="50"/>
            </template>
          </el-table-column>-->
          <el-table-column prop="icon" label="图标" align="center" width="100">
            <template slot-scope="scope">
              <svg-icon :icon-class="scope.row.icon"/>
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
<!--        <el-form-item label="组图标" prop="icon">
          <image-upload v-model="form.icon" @upload-success="handleUploadSuccess"/>
        </el-form-item>-->

        <el-form-item label="属性图标" prop="icon" :rules="[{ required: true, message: '请填写属性图标' } ]">
          <!--          <el-input v-model="form.icon" placeholder="请输入属性图标"/>-->
          <el-popover
            placement="bottom-start"
            width="460"
            trigger="click"
            @show="$refs['iconSelect'].reset()"
          >
            <IconSelect ref="iconSelect" @selected="selected" :active-icon="form.icon"/>
            <el-input slot="reference" v-model="form.icon" placeholder="点击选择图标" readonly>
              <svg-icon
                v-if="form.icon"
                slot="prefix"
                :icon-class="form.icon"
                style="width: 25px;"
              />
              <i v-else slot="prefix" class="el-icon-search el-input__icon"/>
            </el-input>
          </el-popover>

        </el-form-item>

        <el-form-item label="所属分类" prop="catelogId">
<!--          <el-input v-model="form.catelogId" placeholder="请输入所属分类id"/>-->
<!--          <el-cascader
            v-model="catelogIds"
            :options="options"
            :props="props"
           >
          </el-cascader>-->
          <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
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
        style="margin-bottom: 10px"
        type="primary" icon="el-icon-edit"
        @click="handleAddAttr"
      >新建规格
      </el-button>
      <el-button
        style="margin-bottom: 10px"
        type="primary" icon="el-icon-link"
        @click="handleAddLinkAttr"
      >添加关联
      </el-button>
      <el-button
        type="danger"
        plain
        icon="el-icon-delete"
        size="mini"
        :disabled="multiple"
        @click="handleDeleteAttr "
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
              @click="handleUpdateAttr(scope.row)"
            >修改
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDeleteAttr(scope.row)"
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


    <!-- 添加或修改商品属性对话框 -->
    <el-dialog :title="attrFromTitle" :visible.sync="isOpenAddOrUpdateAttr" width="500px" append-to-body>
      <el-form ref="form" :model="attrForm" :rules="rules" label-width="80px">
        <el-form-item label="属性名" prop="attrName" :rules="[{ required: true, message: '必填项' } ]">
          <el-input v-model="attrForm.attrName" placeholder="请输入属性名" required="true" />
        </el-form-item>
        <el-form-item label="是否需要检索" prop="searchType" :rules="[{ required: true, message: '必填项' } ]">
          <el-select v-model="attrForm.searchType" placeholder="请选择是否需要检索">
            <el-option
              v-for="dict in dict.type.search_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="值类型" prop="valueType" :rules="[{ required: true, message: '必填项' } ]">
          <el-select v-model="attrForm.valueType" placeholder="请选择值类型">
            <el-option
              v-for="dict in dict.type.value_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="属性图标" prop="icon" :rules="[{ required: true, message: '必填项' } ]">
          <el-popover
            placement="bottom-start"
            width="460"
            trigger="click"
            @show="$refs['iconSelect'].reset()"
          >
            <IconSelect ref="iconSelect" @selected="selected" :active-icon="attrForm.icon"/>
            <el-input slot="reference" v-model="attrForm.icon" placeholder="点击选择图标" readonly>
              <svg-icon
                v-if="attrForm.icon"
                slot="prefix"
                :icon-class="attrForm.icon"
                style="width: 25px;"
              />
              <i v-else slot="prefix" class="el-icon-search el-input__icon"/>
            </el-input>
          </el-popover>

        </el-form-item>
        <el-form-item label="可选值列表" prop="valueSelect" :rules="[{ required: true, message: '必填项' } ]">
          <!--          <el-input v-model="form.valueSelect" placeholder="请输入可选值列表[用逗号分隔]"/>-->
          <el-select
            v-model="attrForm.valueSelect"
            multiple
            filterable
            allow-create
            default-first-option
            placeholder="可选值列表">
            <el-option
              v-for="item in valueSelectOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="属性类型" prop="attrType" :rules="[{ required: true, message: '必填项' } ]">
          <el-select v-model="attrForm.attrType" placeholder="请选择属性类型">
            <el-option
              v-for="dict in dict.type.attr_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="启用状态" prop="enable" :rules="[{ required: true, message: '必填项' } ]">
          <el-select v-model="attrForm.enable" placeholder="请选择启用状态">
            <el-option
              v-for="dict in dict.type.enable"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
              placeholder
            ></el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="所属分组" prop="attrGroupId">
          <el-select v-model="attrForm.attrGroupId" placeholder="请选择">
            <el-option
              v-for="item in attrGroupIdOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>-->
        <el-form-item label="所属分类" prop="catelogId" :rules="[{ required: true, message: '必填项' } ]">
<!--          <el-cascader
            v-model="catelogIds"
            :options="options"
            :props="props"
          >
          </el-cascader>-->
          <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
        </el-form-item>
        <el-form-item label="快速展示" prop="showDesc" :rules="[{ required: true, message: '必填项' } ]">
          <el-select v-model="attrForm.showDesc" placeholder="请选择快速展示">
            <el-option
              v-for="dict in dict.type.show_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAttrForm()">确 定</el-button>
        <el-button @click="cancelAttr">取 消</el-button>
      </div>
    </el-dialog>


    <el-dialog :title="addLinkAttrFromTitle" :visible.sync="isOpenAddLinkAttr" width="500px" append-to-body>
        <el-table v-loading="loading" :data="allNoLinkAttrList" @selection-change="handleAddLinkAttrSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="属性id" align="center" prop="attrId"/>
        <el-table-column label="属性名" align="center" prop="attrName"/>
        <!--      <el-table-column label="属性图标" align="center" prop="icon"/>-->
        <el-table-column prop="icon" label="图标" align="center" width="100">
          <template slot-scope="scope">
            <svg-icon :icon-class="scope.row.icon"/>
          </template>
        </el-table-column>
        <el-table-column label="可选值列表" align="center" prop="valueSelect"/>

      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitLinkAttr">确 定</el-button>
        <el-button @click="cancelLinkAttr">取 消</el-button>
      </div>

      <pagination
        v-show="allAttrTotals>0"
        :total="allAttrTotals"
        :page.sync="attrQueryParams.pageNum"
        :limit.sync="attrQueryParams.pageSize"
        @pagination="getAllNoLinkAttrList"
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
import {
  addAttr,
  delAttr,
  findAttrGroupRelation,
  getAttr,
  linkAttr,
  listBaseAttrPage,
  noLinkAttrList,
  updateAttr
} from "@/api/product/baseAttr";
import IconSelect from "@/components/IconSelect/index.vue";
import CategoryCascader from "@/views/commoin/category-cascader.vue";

export default {
  components: {
    CategoryCascader,
    IconSelect,
    tree
  },
  name: "Group",
  dicts: ['enable', 'search_type', 'attr_type', 'show_status', 'value_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      attrIds: [],
      inkIds: [],
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
      allAttrTotals:0,
      // 属性分组表格数据
      groupList: [],
      // 商品属性表格数据
      attrList: [],
      allNoLinkAttrList:[],
      // 弹出层标题
      title: "",
      attributeGroupTitle: "",
      attrFromTitle: "",
      addLinkAttrFromTitle: "添加关联",
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
      attrQueryParams: {
        pageNum: 1,
        pageSize: 10,
        data: {
          attrType: null,
        }
      },
      getAttrListQueryParams :{
        pageNum: 1,
        pageSize: 10,
        data: null


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
      attrGroupId: null,
      catelogPath: [],
      isOpenAttrList: false,
      isOpenAddOrUpdateAttr: false,
      isOpenAddLinkAttr: false,
      valueSelectOptions: [],
      attrGroupIdOptions: [],
      valueAttrGroupId: null,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 选择图标
    selected(name) {
      this.form.icon = name;
    },
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
      this.isOpenAttrList = false;
      this.reset();
    },
    cancelAttr(){
      this.isOpenAddOrUpdateAttr=false
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
      this.catelogPath=[]
      this.resetForm("form");
    },
    resetAttrFrom() {
      this.attrForm = {
        attrId: null,
        attrName: null,
        searchType: null,
        valueType: null,
        icon: null,
        valueSelect: null,
        attrType: null,
        enable: null,
        attrGroupId: null,
        catelogId: null,
        showDesc: null
      };
      this.catelogPath = []
      this.resetForm("attrForm");
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
    // 多选框选中数据
    handleAttrListSelectionChange(selection) {
      this.attrIds = selection.map(item => item.attrId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleAddLinkAttrSelectionChange(selection){
      this.inkIds= selection.map(item => item.attrId)
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
        this.catelogPath = response.data.data.catelogPath;
        this.open = true;
        this.title = "修改属性分组";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.attrGroupId != null) {
            this.form.catelogId = this.catelogPath[2];
            updateGroup(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.catelogId = this.catelogPath[2];
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
    openAttrListDialog(row){
      this.getAttrListQueryParams.data = row.attrGroupId;
      this.attrGroupId= row.attrGroupId;
      this.isOpenAttrList = true;
      this.attributeGroupTitle = "规格参数";
      this.attrList= [];
      this.getAttrList();
    },
    getAttrList(){
      console.log(this.getAttrListQueryParams);
      findAttrGroupRelation(this.getAttrListQueryParams).then(response => {
        this.attrList = response.data.data.rows;
        this.attrTotals = response.data.data.total;
      });
    },
    handleAddAttr(){
      this.attrFromTitle= "添加";
      this.resetAttrFrom();
      this.valueSelectOptions = []
      this.isOpenAddOrUpdateAttr= true;
    },
    handleUpdateAttr(row) {
      this.attrFromTitle= "修改";
      this.resetAttrFrom();
      this.valueSelectOptions = []
      const attrId = row.attrId || this.ids
      getAttr(attrId).then(response => {
        this.attrForm = response.data.data;
        this.catelogPath = response.data.data.catelogPath;
        try {
          this.attrForm.valueSelect.split(',').forEach(item => {
            this.valueSelectOptions.push({
              value: item,
              label: item
            })
          })
          this.attrForm.valueSelect = this.attrForm.valueSelect.split(",");
        }catch (e) {
          this.valueSelectOptions=[]
          this.attrForm.valueSelect = []
        }

        this.isOpenAddOrUpdateAttr= true;
        this.title = "修改商品属性";
      });
    },
    /** 提交按钮 */
    submitAttrForm() {
      this.$refs["form"].validate(valid => {
        this.attrForm.attrGroupId= this.attrGroupId;
        if (valid) {
          if (this.attrForm.attrId != null) {
            this.attrForm.catelogId = this.catelogPath[2];
            this.attrForm.valueSelect = this.attrForm.valueSelect.toString();
            updateAttr(this.attrForm).then(response => {
              if(response.data.code === 200){
                this.$modal.msgSuccess("修改成功");
              }
              this.isOpenAddOrUpdateAttr = false;
              this.getAttrList();
            });
          } else {
            this.attrForm.valueSelect = this.attrForm.valueSelect.toString();
            this.attrForm.catelogId = this.catelogPath[2];
            addAttr(this.attrForm).then(response => {
              if(response.data.code === 200){
                this.$modal.msgSuccess("新增成功");
              }
              this.isOpenAddOrUpdateAttr = false;
              this.getAttrList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDeleteAttr(row) {
      const attrIds = [].concat(row.attrId !== undefined ? row.attrId : [], this.attrIds); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认删除商品属性编号为"' + attrIds.join(', ') + '"的数据项？').then(() => {
        return delAttr(attrIds);
      }).then(() => {
        // 删除成功后刷新列表并显示成功消息
        this.getAttrList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
        // 可以在这里处理删除失败的情况，比如显示错误消息
      });
    },
    handleAddLinkAttr(){
      this.getAllNoLinkAttrList()
      this.isOpenAddLinkAttr=true;
    },
    getAllNoLinkAttrList() {
      this.loading = true;
      this.allNoLinkAttrList=[]
      this.allAttrTotals=0
      noLinkAttrList(this.attrQueryParams).then(response => {
        response.data.data.rows.forEach(item => {
          if(item.attrGroupId==null){
            this.allNoLinkAttrList = this.allNoLinkAttrList.concat(item);
            this.allAttrTotals ++
          }
        })
        this.loading = false;
        console.log(this.allNoLinkAttrList);
      });
    },
    cancelLinkAttr(){
      this.isOpenAddLinkAttr=false
      this.inkIds=[]
    },
    submitLinkAttr(){

      this.$modal.confirm('是否确认关联属性编号为"' + this.inkIds.join(', ') + '"的数据项？').then(() => {
       return  linkAttr( this.attrGroupId,this.inkIds).then(response => {
          this.isOpenAddLinkAttr = false;
          this.getAttrList();
        })
      })
    }
  }
};
</script>


