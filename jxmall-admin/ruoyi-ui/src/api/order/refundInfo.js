import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询退款信息列表
export function listRefundInfoPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/order/RefundInfo/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询退款信息详细
export function getRefundInfo(id) {
  return axios({
    baseURL: baseURL,
    url: `/order/RefundInfo/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增退款信息
export function addRefundInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/RefundInfo/save',
    method: 'post',
    data: data
  })
}

// 修改退款信息
export function updateRefundInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/order/RefundInfo/update',
    method: 'put',
    data: data
  })
}

// 删除退款信息
export function delRefundInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/RefundInfo/delete',
    method: 'delete',
    data: ids
  })
}


// 导出退款信息属性
export function exportRefundInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/order/RefundInfo/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出refundInfo数据.xlsx';
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

