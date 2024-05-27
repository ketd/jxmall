import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询订单项信息列表
export function listOrderItemPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderItem/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询订单项信息详细
export function getOrderItem(id) {
  return axios({
    baseURL: baseURL,
    url: `/order/OrderItem/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增订单项信息
export function addOrderItem(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderItem/save',
    method: 'post',
    data: data
  })
}

// 修改订单项信息
export function updateOrderItem(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderItem/update',
    method: 'put',
    data: data
  })
}

// 删除订单项信息
export function delOrderItem(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderItem/delete',
    method: 'delete',
    data: ids
  })
}


// 导出订单项信息属性
export function exportOrderItem(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderItem/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出orderItem数据.xlsx';
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

