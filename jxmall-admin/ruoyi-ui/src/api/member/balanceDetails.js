import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询用户余额明细列表
export function listBalanceDetailsPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/BalanceDetails/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询用户余额明细详细
export function getBalanceDetails(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/BalanceDetails/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增用户余额明细
export function addBalanceDetails(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/BalanceDetails/save',
    method: 'post',
    data: data
  })
}

// 修改用户余额明细
export function updateBalanceDetails(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/BalanceDetails/update',
    method: 'put',
    data: data
  })
}

// 删除用户余额明细
export function delBalanceDetails(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/BalanceDetails/delete',
    method: 'delete',
    data: ids
  })
}


// 导出用户余额明细属性
export function exportBalanceDetails(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/BalanceDetails/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出balanceDetails数据.xlsx';
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

