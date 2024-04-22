import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询商品阶梯价格列表
export function listSkuLadderPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuLadder/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询商品阶梯价格详细
export function getSkuLadder(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/SkuLadder/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增商品阶梯价格
export function addSkuLadder(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuLadder/save',
    method: 'post',
    data: data
  })
}

// 修改商品阶梯价格
export function updateSkuLadder(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuLadder/update',
    method: 'put',
    data: data
  })
}

// 删除商品阶梯价格
export function delSkuLadder(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuLadder/delete',
    method: 'delete',
    data: ids
  })
}


// 导出商品阶梯价格属性
export function exportSkuLadder(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SkuLadder/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出skuLadder数据.xlsx';
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

