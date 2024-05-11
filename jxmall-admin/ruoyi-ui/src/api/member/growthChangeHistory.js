import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询成长值变化历史记录列表
export function listGrowthChangeHistoryPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/GrowthChangeHistory/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询成长值变化历史记录详细
export function getGrowthChangeHistory(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/GrowthChangeHistory/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增成长值变化历史记录
export function addGrowthChangeHistory(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/GrowthChangeHistory/save',
    method: 'post',
    data: data
  })
}

// 修改成长值变化历史记录
export function updateGrowthChangeHistory(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/GrowthChangeHistory/update',
    method: 'put',
    data: data
  })
}

// 删除成长值变化历史记录
export function delGrowthChangeHistory(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/GrowthChangeHistory/delete',
    method: 'delete',
    data: ids
  })
}


// 导出成长值变化历史记录属性
export function exportGrowthChangeHistory(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/GrowthChangeHistory/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出growthChangeHistory数据.xlsx';
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

