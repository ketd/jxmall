import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询首页轮播广告列表
export function listHomeAdvPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeAdv/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询首页轮播广告详细
export function getHomeAdv(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/HomeAdv/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增首页轮播广告
export function addHomeAdv(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeAdv/save',
    method: 'post',
    data: data
  })
}

// 修改首页轮播广告
export function updateHomeAdv(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeAdv/update',
    method: 'put',
    data: data
  })
}

// 删除首页轮播广告
export function delHomeAdv(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeAdv/delete',
    method: 'delete',
    data: ids
  })
}


// 导出首页轮播广告属性
export function exportHomeAdv(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeAdv/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出homeAdv数据.xlsx';
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

