import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询单点登录用户信息关联列表
export function listSocialPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberSocial/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询单点登录用户信息关联详细
export function getSocial(memberId) {
  return axios({
    baseURL: baseURL,
    url: `/member/MemberSocial/info`,
    method: 'get',
    params: {
        memberId: memberId
    }
  })
}

// 新增单点登录用户信息关联
export function addSocial(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberSocial/save',
    method: 'post',
    data: data
  })
}

// 修改单点登录用户信息关联
export function updateSocial(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberSocial/update',
    method: 'put',
    data: data
  })
}

// 删除单点登录用户信息关联
export function delSocial(memberIds) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberSocial/delete',
    method: 'delete',
    data: memberIds
  })
}


// 导出单点登录用户信息关联属性
export function exportSocial(memberIds) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberSocial/export',
    method: 'post',
    data: memberIds,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出social数据.xlsx';
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

