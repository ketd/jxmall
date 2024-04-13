import axios from 'axios';
const baseURL = process.env.VUE_APP_PRODUCT_BASE_API;

// 分页查询属性&属性分组关联列表
export function listRelationPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrAttrgroupRelation/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询属性&属性分组关联详细
export function getRelation(id) {
  return axios({
    baseURL: baseURL,
    url: `/product/AttrAttrgroupRelation/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增属性&属性分组关联
export function addRelation(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrAttrgroupRelation/save',
    method: 'post',
    data: data
  })
}

// 修改属性&属性分组关联
export function updateRelation(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrAttrgroupRelation/update',
    method: 'put',
    data: data
  })
}

// 删除属性&属性分组关联
export function delRelation(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrAttrgroupRelation/delete',
    method: 'delete',
    data: ids
  })
}


// 导出属性&属性分组关联属性
export function exportRelation(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrAttrgroupRelation/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出relation数据.xlsx';
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

