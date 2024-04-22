import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询优惠券信息列表
export function listCouponPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/Coupon/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询优惠券信息详细
export function getCoupon(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/Coupon/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增优惠券信息
export function addCoupon(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/Coupon/save',
    method: 'post',
    data: data
  })
}

// 修改优惠券信息
export function updateCoupon(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/Coupon/update',
    method: 'put',
    data: data
  })
}

// 删除优惠券信息
export function delCoupon(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/Coupon/delete',
    method: 'delete',
    data: ids
  })
}


// 导出优惠券信息属性
export function exportCoupon(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/Coupon/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出coupon数据.xlsx';
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

