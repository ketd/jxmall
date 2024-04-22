import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询会员收藏的商品列表
export function listMemberCollectSpuPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSpu/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询会员收藏的商品详细
export function getMemberCollectSpu(id) {
  return axios({
    baseURL: baseURL,
    url: `/member/MemberCollectSpu/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增会员收藏的商品
export function addMemberCollectSpu(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSpu/save',
    method: 'post',
    data: data
  })
}

// 修改会员收藏的商品
export function updateMemberCollectSpu(data) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSpu/update',
    method: 'put',
    data: data
  })
}

// 删除会员收藏的商品
export function delMemberCollectSpu(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSpu/delete',
    method: 'delete',
    data: ids
  })
}


// 导出会员收藏的商品属性
export function exportMemberCollectSpu(ids) {
  return axios({
    baseURL: baseURL,
    url: '/member/MemberCollectSpu/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出memberCollectSpu数据.xlsx';
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

