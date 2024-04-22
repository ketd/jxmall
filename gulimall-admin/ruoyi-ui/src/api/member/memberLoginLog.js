import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询会员登录记录列表
export function listMemberLoginLogPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLoginLog/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询会员登录记录详细
export function getMemberLoginLog(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/MemberLoginLog/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增会员登录记录
export function addMemberLoginLog(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLoginLog/save',
    method: 'post',
    data: data
  })
}

// 修改会员登录记录
export function updateMemberLoginLog(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLoginLog/update',
    method: 'put',
    data: data
  })
}

// 删除会员登录记录
export function delMemberLoginLog(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLoginLog/delete',
    method: 'delete',
    data: ids
  })
}


// 导出会员登录记录属性
export function exportMemberLoginLog(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLoginLog/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出memberLoginLog数据.xlsx';
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

