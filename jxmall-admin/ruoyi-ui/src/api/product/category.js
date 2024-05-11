import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询商品三级分类列表
export function listCategoryPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/Category/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询商品三级分类详细
export function getCategory(catId) {
  return axios({
    baseURL: baseURL,
    url: `/product/Category/info`,
    method: 'get',
    params: {
        catId: catId
    }
  })
}

// 新增商品三级分类
export function addCategory(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/Category/save',
    method: 'post',
    data: data
  })
}

// 修改商品三级分类
export function updateCategory(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/Category/update',
    method: 'put',
    data: data
  })
}

// 删除商品三级分类
export function delCategory(catIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/Category/delete',
    method: 'delete',
    data: catIds
  })
}


// 导出商品三级分类属性
export function exportCategory(catIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/Category/export',
    method: 'post',
    data: catIds,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出category数据.xlsx';
    const blob = new Blob([response.data], { type: 'application/octet-stream' });

    // 使用 FileSaver.js 来触发下载
    if (typeof window.navigator.msSaveBlob !== 'undefined') {
      window.navigator.msSaveBlob(blob, filename);
    } else {
      const link = document.createElement('a');
      if (link.download !== undefined) {
        // 创建一个链接，并将 Blob 对象设置为其 href 属性，以触发下载
        const url = URL.createObjectURL(blob);
        link.setAttribute('href', url);
        link.setAttribute('download', filename);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      }
    }
  });
}

