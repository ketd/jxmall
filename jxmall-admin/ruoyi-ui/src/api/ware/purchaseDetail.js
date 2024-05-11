import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询采购详情列表
export function listPurchaseDetailPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/ware/PurchaseDetail/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询采购详情详细
export function getPurchaseDetail(id) {
  return axios({
    baseURL: baseURL,
    url: `/ware/PurchaseDetail/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增采购详情
export function addPurchaseDetail(data) {
  return axios({
    baseURL: baseURL,
    url: '/ware/PurchaseDetail/save',
    method: 'post',
    data: data
  })
}

// 合并采购单
export function addMerge(purchaseId,items) {
  const MergeVo={
    purchaseId:purchaseId,
    items:items
  }
  return axios({
    baseURL: baseURL,
    url: '/ware/Purchase/merge',
    method: 'post',
    data: MergeVo
  })
}

// 修改采购详情
export function updatePurchaseDetail(data) {
  return axios({
    baseURL: baseURL,
    url: '/ware/PurchaseDetail/update',
    method: 'put',
    data: data
  })
}

// 删除采购详情
export function delPurchaseDetail(ids) {
  return axios({
    baseURL: baseURL,
    url: '/ware/PurchaseDetail/delete',
    method: 'delete',
    data: ids
  })
}


// 导出采购详情属性
export function exportPurchaseDetail(ids) {
  return axios({
    baseURL: baseURL,
    url: '/ware/PurchaseDetail/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出purchaseDetail数据.xlsx';
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

