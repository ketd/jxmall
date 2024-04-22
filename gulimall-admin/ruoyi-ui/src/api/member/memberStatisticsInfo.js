import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询会员统计信息列表
export function listMemberStatisticsInfoPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberStatisticsInfo/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询会员统计信息详细
export function getMemberStatisticsInfo(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/MemberStatisticsInfo/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增会员统计信息
export function addMemberStatisticsInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberStatisticsInfo/save',
    method: 'post',
    data: data
  })
}

// 修改会员统计信息
export function updateMemberStatisticsInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberStatisticsInfo/update',
    method: 'put',
    data: data
  })
}

// 删除会员统计信息
export function delMemberStatisticsInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberStatisticsInfo/delete',
    method: 'delete',
    data: ids
  })
}


// 导出会员统计信息属性
export function exportMemberStatisticsInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberStatisticsInfo/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出memberStatisticsInfo数据.xlsx';
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

