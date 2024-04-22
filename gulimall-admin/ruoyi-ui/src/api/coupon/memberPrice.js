import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询商品会员价格列表
export function listMemberPricePage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/MemberPrice/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询商品会员价格详细
export function getMemberPrice(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/MemberPrice/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增商品会员价格
export function addMemberPrice(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/MemberPrice/save',
    method: 'post',
    data: data
  })
}

// 修改商品会员价格
export function updateMemberPrice(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/MemberPrice/update',
    method: 'put',
    data: data
  })
}

// 删除商品会员价格
export function delMemberPrice(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/MemberPrice/delete',
    method: 'delete',
    data: ids
  })
}


// 导出商品会员价格属性
export function exportMemberPrice(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/MemberPrice/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出memberPrice数据.xlsx';
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

