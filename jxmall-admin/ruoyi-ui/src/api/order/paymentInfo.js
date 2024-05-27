import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询支付信息列表
export function listPaymentInfoPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/order/PaymentInfo/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询支付信息详细
export function getPaymentInfo(id) {
  return axios({
    baseURL: baseURL,
    url: `/order/PaymentInfo/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增支付信息
export function addPaymentInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/PaymentInfo/save',
    method: 'post',
    data: data
  })
}

// 修改支付信息
export function updatePaymentInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/PaymentInfo/update',
    method: 'put',
    data: data
  })
}

// 删除支付信息
export function delPaymentInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/PaymentInfo/delete',
    method: 'delete',
    data: ids
  })
}


// 导出支付信息属性
export function exportPaymentInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/PaymentInfo/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出paymentInfo数据.xlsx';
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

