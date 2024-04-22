<script>
import axios from 'axios';
import {getToken} from "@/utils/auth";


const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

export default {
  components:{},
  props: {},
  data() {
    return {
      pCid: [],
      draggable: false,
      updateNodes: [],
      maxLevel: 0,
      dialogType: '',
      dialogTitle: '',
      category: {
        catId: null,
        name: '',
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        icon: '',
        productUnit: '',
        sort: 0,
        productCount: 0,
      },
      menus: [],
      expandedKey: [],
      dialogVisible: false,
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    };
  },
  created() {
    // 在组件创建时调用 getMenus 方法
    this.getMenus();
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    },
    getMenus() {
      axios({
        baseURL: baseURL,
        url: '/product/Category/list/tree',
        method: 'GET',
        headers: {
          'Authorization': getToken() // 如果需要身份验证，请添加适当的头部信息
        }
      }).then(response => {
        this.menus = response.data.data;
        console.log(response);
      }).catch(error => {
        console.error('Error fetching menus:', error);
      });
    },
    submitForm() {
      if (this.dialogType === 'add') {
        this.addCategory()
      } else {
        this.editCategory()
      }
    },
    remove(node, data) {
      this.open(node, data)
    },
    open(node, data) {
      this.$confirm('此操作将永久删除[ ' + data.name + '], 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var ids = [data.catId];
        axios({
          baseURL: baseURL,
          url: '/product/Category/delete',
          method: 'POST',
          headers: {
            'Authorization': getToken() // 如果需要身份验证，请添加适当的头部信息
          },
          data: ids  // 如果需要发送数据给服务器，可以在这里添加
        }).then(response => {
          if (response.data.code === 0) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getMenus()
            this.expandedKey = [node.parent.data.catId]
          }
          console.log(response);
        }).catch(error => {
          console.error('Error fetching menus:', error);
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    append(data) {
      this.dialogVisible = true
      this.dialogType = 'add'
      this.dialogTitle = '添加分类'
      this.category.parentCid = data.catId
      this.category.name = ''
      this.category.catLevel = data.catLevel * 1 + 1
    },
    addCategory() {
      axios({
        baseURL: baseURL,
        url: '/product/Category/save',
        method: 'POST',
        headers: {
          'Authorization': getToken() // 如果需要身份验证，请添加适当的头部信息
        },
        data: this.category  // 如果需要发送数据给服务器，可以在这里添加
      }).then(response => {
        if (response.data.code === 0) {
          this.$message({
            type: 'success',
            message: '添加成功!'
          });
          this.getMenus()
          this.dialogVisible = false
          this.expandedKey = [this.category.parentCid]
          this.category = {
            name: '',
            parentCid: 0,
            catLevel: 0,
            showStatus: 1,
            icon: '',
            productUnit: '',
            sort: 0,
            productCount: 0,
          }

        }
        console.log(response);
      }).catch(error => {
        console.error('Error fetching menus:', error);
        this.$message({
          type: 'error',
          message: '添加失败!'
        })

      });
    },
    edit(data) {
      this.dialogVisible = true
      this.dialogType = 'edit'
      this.dialogTitle = '修改分类'
      //获取当前节点最新数据/category/info/{catId}
      axios({
        baseURL: baseURL,
        url: '/product/Category/info/' + data.catId,
        method: 'GET',
        headers: {
          'Authorization': getToken() // 如果需要身份验证，请添加适当的头部信息
        }
      }).then(response => {
        console.log(response);
        this.category.name = response.data.data.name
        this.category.catId = response.data.data.catId
        this.category.parentCid = response.data.data.parentCid
        this.category.icon = response.data.data.icon
        this.category.productUnit = response.data.data.productUnit
      }).catch(error => {
        console.error('Error fetching menus:', error);
      });

    },
    editCategory() {
      //解构表达式
      var {catId, name, icon, productUnit} = this.category
      var dto = {catId, name, icon, productUnit}
      axios({
        baseURL: baseURL,
        url: '/product/Category/update',
        method: 'POST',
        headers: {
          'Authorization': getToken() // 如果需要身份验证，请添加适当的头部信息
        },
        data: dto  // 如果需要发送数据给服务器，可以在这里添加
      }).then(response => {
        if (response.data.code === 0) {
          this.$message({
            type: 'success',
            message: '修改成功!'
          });
          this.getMenus()
          this.dialogVisible = false
          this.expandedKey = [this.category.parentCid]
          this.category = {
            name: '',
            parentCid: 0,
            catLevel: 0,
            showStatus: 1,
            icon: '',
            productUnit: '',
            sort: 0,
            productCount: 0,
          }

        }
        console.log(response);
      }).catch(error => {
        console.error('Error fetching menus:', error);
        this.$message({
          type: 'error',
          message: '修改失败!'
        })

      });
    },
    handleDrop(draggingNode, dropNode, dropType, ev) {
      console.log("handleDrop: ", draggingNode, dropNode, dropType);
      //1、当前节点最新的父节点id
      let pCid = 0;
      let siblings = null;
      if (dropType === "before" || dropType === "after") {
        pCid =
          dropNode.parent.data.catId === undefined
            ? 0
            : dropNode.parent.data.catId;
        siblings = dropNode.parent.childNodes;
      } else {
        pCid = dropNode.data.catId;
        siblings = dropNode.childNodes;
      }
      this.pCid.push(pCid);

      //2、当前拖拽节点的最新顺序，
      for (let i = 0; i < siblings.length; i++) {
        if (siblings[i].data.catId === draggingNode.data.catId) {
          //如果遍历的是当前正在拖拽的节点
          let catLevel = draggingNode.level;
          if (siblings[i].level !== draggingNode.level) {
            //当前节点的层级发生变化
            catLevel = siblings[i].level;
            //修改他子节点的层级
            this.updateChildNodeLevel(siblings[i]);
          }
          this.updateNodes.push({
            catId: siblings[i].data.catId,
            sort: i,
            parentCid: pCid,
            catLevel: catLevel
          });
        } else {
          this.updateNodes.push({ catId: siblings[i].data.catId, sort: i });
        }
      }

      //3、当前拖拽节点的最新层级
      console.log("updateNodes", this.updateNodes);


    },
    updateChildNodeLevel(node) {
      if (node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          var cNode = node.childNodes[i].data;
          this.updateNodes.push({
            catId: cNode.catId,
            catLevel: node.childNodes[i].level
          });
          this.updateChildNodeLevel(node.childNodes[i]);
        }
      }
    },
    allowDrop(draggingNode, dropNode, type) {
      //1、被拖动的当前节点以及所在的父节点总层数不能大于3

      //1）、被拖动的当前节点总层数
      console.log("allowDrop:", draggingNode, dropNode, type);
      //
      this.countNodeLevel(draggingNode);
      //当前正在拖动的节点+父节点所在的深度不大于3即可
      let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
      console.log("深度：", deep);

      //   this.maxLevel
      if (type === "inner") {
        // console.log(
        //   `this.maxLevel：${this.maxLevel}；draggingNode.data.catLevel：${draggingNode.data.catLevel}；dropNode.level：${dropNode.level}`
        // );
        return deep + dropNode.level <= 3;
      } else {
        return deep + dropNode.parent.level <= 3;
      }
    },
    batchSave() {
      axios({
        baseURL: baseURL,
        url: '/product/Category/update/sort',
        method: 'POST',
        headers: {
          'Authorization': getToken() // 如果需要身份验证，请添加适当的头部信息
        },
        data: this.updateNodes  // 如果需要发送数据给服务器，可以在这里添加
      }).then(response => {
        if (response.data.code === 0) {
          this.$message({
            type: 'success',
            message: '修改成功!'
          });
          this.getMenus()
          this.expandedKey = this.pCid
          this.updateNodes=[]
          this. maxLevel= 0


        }
        console.log(response);
      }).catch(error => {
        console.error('Error fetching menus:', error);
      });
    },
    countNodeLevel(node) {
      //找到所有子节点，求出最大深度
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          if (node.childNodes[i].level > this.maxLevel) {
            this.maxLevel = node.childNodes[i].level;
          }
          this.countNodeLevel(node.childNodes[i]);
        }
      }
    },
    batchDelete() {
      let catIds = [];
      let checkedNodes = this.$refs.menuTree.getCheckedNodes();
      console.log("被选中的元素", checkedNodes);
      for (let i = 0; i < checkedNodes.length; i++) {
        catIds.push(checkedNodes[i].catId);
      }
      this.$confirm(`是否批量删除【${catIds}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          axios({
            baseURL: baseURL,
            url: '/product/Category/delete',
            method: 'POST',
            headers: {
              'Authorization': getToken() // 如果需要身份验证，请添加适当的头部信息
            },
            data: catIds  // 如果需要发送数据给服务器，可以在这里添加
          }).then(response => {
            if (response.data.code === 0) {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.getMenus()
              this.expandedKey = this.pCid
              this.updateNodes=[]
              this. maxLevel= 0


            }
            console.log(response);
          }).catch(error => {
            console.error('Error fetching menus:', error);
          });

        })
        .catch(() => {});
    },
    //关闭表单弹窗
    handleClose() {
      this.dialogVisible = false
      this.category = {
        catId: null,
        name: '',
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        icon: '',
        productUnit: '',
        sort: 0,
        productCount: 0,
      }

    }
  }

};
</script>

<template>
  <div>
    <el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>
    <el-button v-if="draggable" @click="batchSave">批量保存</el-button>
    <el-button type="danger" @click="batchDelete">批量删除</el-button>
    <el-tree
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
      :draggable="draggable"
      :allow-drop="allowDrop"
      @node-drop="handleDrop"
      ref="menuTree">
     <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)">
            添加
          </el-button>
          <el-button
            v-if="node.childNodes.length === 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            删除
          </el-button>
           <el-button
             type="text"
             size="mini"
             @click="() => edit(data)">
            修改
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">

      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submitForm">确 定</el-button>
  </span>
    </el-dialog>
  </div>

</template>

<style scoped lang="scss">
</style>
