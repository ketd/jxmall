import axios from 'axios';
const baseURL = process.env.VUE_APP_PRODUCT_BASE_API;

// 分页查询品牌分类关联列表
export function listCategoryBrandRelationPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/CategoryBrandRelation/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询品牌分类关联详细
export function getCategoryBrandRelation(id) {
  return axios({
    baseURL: baseURL,
    url: `/product/CategoryBrandRelation/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增品牌分类关联
export function addCategoryBrandRelation(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/CategoryBrandRelation/save',
    method: 'post',
    data: data
  })
}

// 修改品牌分类关联
export function updateCategoryBrandRelation(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/CategoryBrandRelation/update',
    method: 'put',
    data: data
  })
}

// 删除品牌分类关联
export function delCategoryBrandRelation(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/CategoryBrandRelation/delete',
    method: 'delete',
    data: ids
  })
}


// 导出品牌分类关联属性
export function exportCategoryBrandRelation(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/CategoryBrandRelation/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出categoryBrandRelation数据.xlsx';
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

