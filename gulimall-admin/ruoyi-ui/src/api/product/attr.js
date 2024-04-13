import axios from 'axios';
const baseURL = process.env.VUE_APP_PRODUCT_BASE_API;

// 分页查询商品属性列表
export function listAttrPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/Attr/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询商品属性详细
export function getAttr(attrId) {
  return axios({
    baseURL: baseURL,
    url: `/product/Attr/info`,
    method: 'get',
    params: {
        attrId: attrId
    }
  })
}

// 新增商品属性
export function addAttr(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/Attr/save',
    method: 'post',
    data: data
  })
}

// 修改商品属性
export function updateAttr(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/Attr/update',
    method: 'put',
    data: data
  })
}

// 删除商品属性
export function delAttr(attrIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/Attr/delete',
    method: 'delete',
    data: attrIds
  })
}


// 导出商品属性属性
export function exportAttr(attrIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/Attr/export',
    method: 'post',
    data: attrIds,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出attr数据.xlsx';
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

