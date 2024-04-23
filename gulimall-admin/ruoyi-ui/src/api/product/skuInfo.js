import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询sku信息列表
export function listSkuInfoPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuInfo/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询sku信息详细
export function getSkuInfo(skuId) {
  return axios({
    baseURL: baseURL,
    url: `/product/SkuInfo/info`,
    method: 'get',
    params: {
        skuId: skuId
    }
  })
}

// 新增sku信息
export function addSkuInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuInfo/save',
    method: 'post',
    data: data
  })
}

// 修改sku信息
export function updateSkuInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuInfo/update',
    method: 'put',
    data: data
  })
}

// 删除sku信息
export function delSkuInfo(skuIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuInfo/delete',
    method: 'delete',
    data: skuIds
  })
}




// 导出sku信息属性
export function exportSkuInfo(skuIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuInfo/export',
    method: 'post',
    data: skuIds,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出skuInfo数据.xlsx';
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

