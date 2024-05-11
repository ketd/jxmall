import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询优惠券领取历史记录列表
export function listCouponHistoryPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponHistory/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询优惠券领取历史记录详细
export function getCouponHistory(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/CouponHistory/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增优惠券领取历史记录
export function addCouponHistory(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponHistory/save',
    method: 'post',
    data: data
  })
}

// 修改优惠券领取历史记录
export function updateCouponHistory(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponHistory/update',
    method: 'put',
    data: data
  })
}

// 删除优惠券领取历史记录
export function delCouponHistory(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponHistory/delete',
    method: 'delete',
    data: ids
  })
}


// 导出优惠券领取历史记录属性
export function exportCouponHistory(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponHistory/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出couponHistory数据.xlsx';
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

