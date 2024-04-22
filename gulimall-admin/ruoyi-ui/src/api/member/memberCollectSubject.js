import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询会员收藏的专题活动列表
export function listMemberCollectSubjectPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSubject/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询会员收藏的专题活动详细
export function getMemberCollectSubject(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/MemberCollectSubject/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增会员收藏的专题活动
export function addMemberCollectSubject(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSubject/save',
    method: 'post',
    data: data
  })
}

// 修改会员收藏的专题活动
export function updateMemberCollectSubject(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSubject/update',
    method: 'put',
    data: data
  })
}

// 删除会员收藏的专题活动
export function delMemberCollectSubject(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSubject/delete',
    method: 'delete',
    data: ids
  })
}


// 导出会员收藏的专题活动属性
export function exportMemberCollectSubject(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSubject/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出memberCollectSubject数据.xlsx';
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

