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
    handleNodeClick(data, node, component) {
      this.$emit('handleNodeClick', data, node, component);
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

  }

};
</script>

<template>
  <div>
    <el-tree
      :data="menus"
      :props="defaultProps"
      node-key="catId"
      :default-expanded-keys="expandedKey"
      @node-click="handleNodeClick"
      ref="menuTree">
    </el-tree>
  </div>
</template>

<style scoped lang="scss">
</style>
