import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询积分变化历史记录列表
export function listIntegrationChangeHistoryPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/IntegrationChangeHistory/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询积分变化历史记录详细
export function getIntegrationChangeHistory(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/IntegrationChangeHistory/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增积分变化历史记录
export function addIntegrationChangeHistory(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/IntegrationChangeHistory/save',
    method: 'post',
    data: data
  })
}

// 修改积分变化历史记录
export function updateIntegrationChangeHistory(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/IntegrationChangeHistory/update',
    method: 'put',
    data: data
  })
}

// 删除积分变化历史记录
export function delIntegrationChangeHistory(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/IntegrationChangeHistory/delete',
    method: 'delete',
    data: ids
  })
}


// 导出积分变化历史记录属性
export function exportIntegrationChangeHistory(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/IntegrationChangeHistory/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出integrationChangeHistory数据.xlsx';
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

