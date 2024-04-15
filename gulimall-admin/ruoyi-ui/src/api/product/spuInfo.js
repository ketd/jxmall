import axios from 'axios';
const baseURL = process.env.VUE_APP_PRODUCT_BASE_API;

// 分页查询spu信息列表
export function listSpuInfoPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfo/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询spu信息详细
export function getSpuInfo(id) {
  return axios({
    baseURL: baseURL,
    url: `/product/SpuInfo/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增spu信息
export function addSpuInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfo/save',
    method: 'post',
    data: data
  })
}

// 修改spu信息
export function updateSpuInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfo/update',
    method: 'put',
    data: data
  })
}

// 删除spu信息
export function delSpuInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfo/delete',
    method: 'delete',
    data: ids
  })
}


// 导出spu信息属性
export function exportSpuInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfo/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出spuInfo数据.xlsx';
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

