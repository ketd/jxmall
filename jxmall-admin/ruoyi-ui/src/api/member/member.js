import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询会员列表
export function listMemberPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/Member/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询会员详细
export function getMember(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/Member/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增会员
export function addMember(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/Member/save',
    method: 'post',
    data: data
  })
}

// 修改会员
export function updateMember(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/Member/update',
    method: 'put',
    data: data
  })
}

// 删除会员
export function delMember(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/Member/delete',
    method: 'delete',
    data: ids
  })
}


// 导出会员属性
export function exportMember(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/Member/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出member数据.xlsx';
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

