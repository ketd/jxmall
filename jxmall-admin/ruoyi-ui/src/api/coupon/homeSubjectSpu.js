import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询专题商品列表
export function listHomeSubjectSpuPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubjectSpu/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询专题商品详细
export function getHomeSubjectSpu(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/HomeSubjectSpu/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增专题商品
export function addHomeSubjectSpu(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubjectSpu/save',
    method: 'post',
    data: data
  })
}

// 修改专题商品
export function updateHomeSubjectSpu(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubjectSpu/update',
    method: 'put',
    data: data
  })
}

// 删除专题商品
export function delHomeSubjectSpu(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubjectSpu/delete',
    method: 'delete',
    data: ids
  })
}


// 导出专题商品属性
export function exportHomeSubjectSpu(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubjectSpu/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出homeSubjectSpu数据.xlsx';
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

