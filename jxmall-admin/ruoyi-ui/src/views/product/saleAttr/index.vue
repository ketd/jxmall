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
          <el-form-item label="属性名" prop="attrName">
            <el-input
              v-model="queryParams.data.attrName"
              placeholder="请输入属性名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="是否需要检索" prop="searchType">
            <el-select v-model="queryParams.data.searchType" placeholder="请选择是否需要检索" clearable>
              <el-option
                v-for="dict in dict.type.search_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="值类型" prop="valueType">
            <el-select v-model="queryParams.data.valueType" placeholder="请选择值类型" clearable>
              <el-option
                v-for="dict in dict.type.value_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="属性图标" prop="icon">
            <el-input
              v-model="queryParams.data.icon"
              placeholder="请输入属性图标"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="可选值列表[用逗号分隔]" prop="valueSelect">
            <el-input
              v-model="queryParams.data.valueSelect"
              placeholder="请输入可选值列表[用逗号分隔]"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="属性类型" prop="attrType">
            <el-select v-model="queryParams.data.attrType" placeholder="请选择属性类型" clearable>
              <el-option
                v-for="dict in dict.type.attr_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="启用状态" prop="enable">
            <el-select v-model="queryParams.data.enable" placeholder="请选择启用状态" clearable>
              <el-option
                v-for="dict in dict.type.enable"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="所属分类" prop="catelogId">
            <el-input
              v-model="queryParams.data.catelogId"
              placeholder="请输入所属分类"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="快速展示" prop="showDesc">
            <el-select v-model="queryParams.data.showDesc" placeholder="请选择快速展示" clearable>
              <el-option
                v-for="dict in dict.type.show_status"
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
              v-hasPermi="['product:baseAttr:add']"
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
              v-hasPermi="['product:baseAttr:edit']"
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
              v-hasPermi="['product:baseAttr:remove']"
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
              v-hasPermi="['product:baseAttr:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="attrList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="属性id" align="center" prop="attrId"/>
          <el-table-column label="属性名" align="center" prop="attrName"/>
          <el-table-column label="是否需要检索" align="center" prop="searchType">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.search_type" :value="scope.row.searchType"/>
            </template>
          </el-table-column>
          <el-table-column label="值类型" align="center" prop="valueType">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.value_type" :value="scope.row.valueType"/>
            </template>
          </el-table-column>
          <!--      <el-table-column label="属性图标" align="center" prop="icon"/>-->
          <el-table-column prop="icon" label="图标" align="center" width="100">
            <template slot-scope="scope">
              <svg-icon :icon-class="scope.row.icon"/>
            </template>
          </el-table-column>
          <el-table-column label="可选值列表" align="center" prop="valueSelect"/>
          <el-table-column label="属性类型" align="center" prop="attrType">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.attr_type" :value="scope.row.attrType"/>
            </template>
          </el-table-column>
          <el-table-column label="启用状态" align="center" prop="enable">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.enable" :value="scope.row.enable"/>
            </template>
          </el-table-column>
          <el-table-column label="所属分类" align="center" prop="catelogName"/>
          <el-table-column label="所属分组" align="center" prop="attrGroupName"/>
          <el-table-column label="快速展示" align="center" prop="showDesc">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.show_status" :value="scope.row.showDesc"/>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['product:baseAttr:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['product:baseAttr:remove']"
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
      </el-col>
    </el-row>
    <!-- 添加或修改商品属性对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="属性名" prop="attrName" :rules="[{ required: true, message: '请输入属性名' } ]">
          <el-input v-model="form.attrName" placeholder="请输入属性名"/>
        </el-form-item>
        <el-form-item label="是否需要检索" prop="searchType"
                      :rules="[{ required: true, message: '请输入是否需要检索' } ]">
          <el-select v-model="form.searchType" placeholder="请选择是否需要检索">
            <el-option
              v-for="dict in dict.type.search_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="值类型" prop="valueType" :rules="[{ required: true, message: '请填写值类型' } ]">
          <el-select v-model="form.valueType" placeholder="请选择值类型">
            <el-option
              v-for="dict in dict.type.value_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
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
        <el-form-item label="可选值列表" :rules="[{ required: true, message: '请输入可选值列表' } ]">
          <!--          <el-input v-model="form.valueSelect" placeholder="请输入可选值列表[用逗号分隔]"/>-->
          <el-select
            v-model="form.valueSelect"
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
        <el-form-item label="属性类型" prop="attrType" :rules="[{ required: true, message: '请填写属性类型' } ]">
          <el-select v-model="form.attrType" placeholder="请选择属性类型">
            <el-option
              v-for="dict in dict.type.attr_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="启用状态" prop="enable" :rules="[{ required: true, message: '请填写启用状态' } ]">
          <el-select v-model="form.enable" placeholder="请选择启用状态">
            <el-option
              v-for="dict in dict.type.enable"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
              placeholder
            ></el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="所属分组" prop="attrGroupId" :rules="[{ required: true, message: '请填写所属分组' } ]">
          &lt;!&ndash;          <el-input v-model="form.attrGroupId" placeholder="请输入所属分组"/>&ndash;&gt;
          <el-select v-model="form.attrGroupId" placeholder="请选择">
            <el-option
              v-for="item in attrGroupIdOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>-->
        <el-form-item label="所属分类" :rules="[{ required: true, message: '请填写所属分类' } ]">
          <!--          <el-input v-model="form.catelogId" placeholder="请输入所属分类"/>-->
          <el-cascader
            v-model="catelogIds"
            :options="options"
            :props="props"
          >
          </el-cascader>
        </el-form-item>
        <el-form-item label="快速展示" prop="showDesc" :rules="[{ required: true, message: '请填写快速展示' } ]">
          <el-select v-model="form.showDesc" placeholder="请选择快速展示">
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
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listSaleAttrPage,
  getAttr,
  delAttr,
  addAttr,
  updateAttr,
  exportAttr
} from "@/api/product/saleAttr";
import {getMenus} from "@/api/commoin/commoin";
import {listGroupPage} from "@/api/product/group";
import IconSelect from "@/components/IconSelect/index.vue";
import tree from "@/views/commoin/tree.vue";

export default {
  name: "Attr",
  components: {tree, IconSelect},
  dicts: ['enable', 'search_type', 'attr_type', 'show_status', 'value_type'],
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
      // 商品属性表格数据
      attrList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data: {
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
        }
      },
      getAttrGroupListQueryParams: {
        pageNum: 1,
        pageSize: 100,
        data: {}
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      catelogIds: [],
      options: [],
      props: {
        value: 'catId',
        label: 'name',
        children: 'children'
      },
      valueSelectOptions: [],
      attrGroupIdOptions: [],
      valueAttrGroupId: null,
    };
  },
  created() {
    this.getList();
    this.getMenus();
    this.getAttrGroupList();
  },
  methods: {
    // 选择图标
    selected(name) {
      this.form.icon = name;
    },
    /** 查询商品属性列表 */
    getList() {
      this.loading = true;
      listSaleAttrPage(this.queryParams).then(response => {
        this.attrList = response.data.data.rows;
        this.total = response.data.data.total;
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
      this.catelogIds = []
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
      this.ids = selection.map(item => item.attrId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品属性";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.valueSelectOptions = []
      const attrId = row.attrId || this.ids
      getAttr(attrId).then(response => {
        this.form = response.data.data;
        this.catelogIds = response.data.data.catelogPath;
        try {
          this.form.valueSelect.split(',').forEach(item => {
            this.valueSelectOptions.push({
              value: item,
              label: item
            })
          })
          this.form.valueSelect = this.form.valueSelect.split(",");
        } catch (e) {
          this.valueSelectOptions = [];
          this.form.valueSelect = [];

        }

        this.open = true;
        this.title = "修改商品属性";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.attrId != null) {
            this.form.catelogId = this.catelogIds[2];
            this.form.valueSelect = this.form.valueSelect.toString();
            updateAttr(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.valueSelect = this.form.valueSelect.toString();
            this.form.catelogId = this.catelogIds[2];
            addAttr(this.form).then(response => {
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
      const attrIds = [].concat(row.attrId !== undefined ? row.attrId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认删除商品属性编号为"' + attrIds.join(', ') + '"的数据项？').then(() => {
        return delAttr(attrIds);
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
      const attrIds = [].concat(row.attrId !== undefined ? row.attrId : [], this.ids); // 将属性ID合并为一个数组
      this.$modal.confirm('是否确认导出商品属性编号为"' + attrIds.join(', ') + '"的数据项？').then(() => {
        return exportAttr(attrIds);
      }).then(() => {
        this.$modal.msgSuccess("导出成功");
        this.getList();
      }).catch(() => {
        console.log("导出失败");
      });
    },
    getMenus() {
      getMenus().then(response => {
        this.options = response.data.data;
      }).catch(error => {

      }).finally(() => {
      });
    },
    getAttrGroupList() {
      listGroupPage(this.getAttrGroupListQueryParams).then(response => {
        response.data.rows.forEach(item => {
          this.attrGroupIdOptions.push({
            value: item.attrGroupId,
            label: item.attrGroupName
          })
        })
      });
    },
    handleNodeClick(data, node, component) {
      if (node.level === 3) {
        this.queryParams.data.catelogId = data.catId;
        this.handleQuery();
      }
    },
  }
};
</script>


