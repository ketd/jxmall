import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询商品满减信息列表
export function listSkuFullReductionPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuFullReduction/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询商品满减信息详细
export function getSkuFullReduction(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/SkuFullReduction/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增商品满减信息
export function addSkuFullReduction(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuFullReduction/save',
    method: 'post',
    data: data
  })
}

// 修改商品满减信息
export function updateSkuFullReduction(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuFullReduction/update',
    method: 'put',
    data: data
  })
}

// 删除商品满减信息
export function delSkuFullReduction(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuFullReduction/delete',
    method: 'delete',
    data: ids
  })
}


// 导出商品满减信息属性
export function exportSkuFullReduction(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuFullReduction/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出skuFullReduction数据.xlsx';
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

