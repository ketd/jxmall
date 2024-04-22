import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询会员等级列表
export function listMemberLevelPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLevel/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询会员等级详细
export function getMemberLevel(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/MemberLevel/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增会员等级
export function addMemberLevel(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLevel/save',
    method: 'post',
    data: data
  })
}

// 修改会员等级
export function updateMemberLevel(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLevel/update',
    method: 'put',
    data: data
  })
}

// 删除会员等级
export function delMemberLevel(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLevel/delete',
    method: 'delete',
    data: ids
  })
}


// 导出会员等级属性
export function exportMemberLevel(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberLevel/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出memberLevel数据.xlsx';
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

