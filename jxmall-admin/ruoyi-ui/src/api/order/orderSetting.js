import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询订单配置信息列表
export function listOrderSettingPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderSetting/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询订单配置信息详细
export function getOrderSetting(id) {
  return axios({
    baseURL: baseURL,
    url: `/order/OrderSetting/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增订单配置信息
export function addOrderSetting(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderSetting/save',
    method: 'post',
    data: data
  })
}

// 修改订单配置信息
export function updateOrderSetting(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderSetting/update',
    method: 'put',
    data: data
  })
}

// 删除订单配置信息
export function delOrderSetting(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderSetting/delete',
    method: 'delete',
    data: ids
  })
}


// 导出订单配置信息属性
export function exportOrderSetting(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/OrderSetting/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出orderSetting数据.xlsx';
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

