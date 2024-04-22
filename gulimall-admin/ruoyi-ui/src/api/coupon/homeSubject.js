import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
export function listHomeSubjectPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubject/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详细
export function getHomeSubject(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/HomeSubject/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
export function addHomeSubject(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubject/save',
    method: 'post',
    data: data
  })
}

// 修改首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
export function updateHomeSubject(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubject/update',
    method: 'put',
    data: data
  })
}

// 删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
export function delHomeSubject(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubject/delete',
    method: 'delete',
    data: ids
  })
}


// 导出首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】属性
export function exportHomeSubject(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/HomeSubject/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出homeSubject数据.xlsx';
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

