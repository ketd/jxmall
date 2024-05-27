import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询订单操作历史记录列表
export function listOrderOperateHistoryPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderOperateHistory/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询订单操作历史记录详细
export function getOrderOperateHistory(id) {
  return axios({
    baseURL: baseURL,
    url: `/order/OrderOperateHistory/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增订单操作历史记录
export function addOrderOperateHistory(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderOperateHistory/save',
    method: 'post',
    data: data
  })
}

// 修改订单操作历史记录
export function updateOrderOperateHistory(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderOperateHistory/update',
    method: 'put',
    data: data
  })
}

// 删除订单操作历史记录
export function delOrderOperateHistory(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderOperateHistory/delete',
    method: 'delete',
    data: ids
  })
}


// 导出订单操作历史记录属性
export function exportOrderOperateHistory(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderOperateHistory/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出orderOperateHistory数据.xlsx';
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

