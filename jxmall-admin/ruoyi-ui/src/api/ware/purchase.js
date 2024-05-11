import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询采购信息列表
export function listPurchasePage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/ware/Purchase/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询采购信息详细
export function getPurchase(id) {
  return axios({
    baseURL: baseURL,
    url: `/ware/Purchase/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增采购信息
export function addPurchase(data) {
  return axios({
    baseURL: baseURL,
    url: '/ware/Purchase/save',
    method: 'post',
    data: data
  })
}

// 修改采购信息
export function updatePurchase(data) {
  return axios({
    baseURL: baseURL,
    url: '/ware/Purchase/update',
    method: 'put',
    data: data
  })
}

// 删除采购信息
export function delPurchase(ids) {
  return axios({
    baseURL: baseURL,
    url: '/ware/Purchase/delete',
    method: 'delete',
    data: ids
  })
}


// 导出采购信息属性
export function exportPurchase(ids) {
  return axios({
    baseURL: baseURL,
    url: '/ware/Purchase/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出purchase数据.xlsx';
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

