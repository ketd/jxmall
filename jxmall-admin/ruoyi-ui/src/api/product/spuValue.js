import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询spu属性值列表
export function listSpuValuePage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/ProductAttrValue/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询spu属性值详细
export function getSpuValue(id) {
  return axios({
    baseURL: baseURL,
    url: `/product/ProductAttrValue/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增spu属性值
export function addSpuValue(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/ProductAttrValue/save',
    method: 'post',
    data: data
  })
}

// 修改spu属性值
export function updateSpuValue(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/ProductAttrValue/update',
    method: 'put',
    data: data
  })
}

// 删除spu属性值
export function delSpuValue(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/ProductAttrValue/delete',
    method: 'delete',
    data: ids
  })
}


// 导出spu属性值属性
export function exportSpuValue(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/ProductAttrValue/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出spuValue数据.xlsx';
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

