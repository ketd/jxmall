import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询优惠券与产品关联列表
export function listCouponSpuRelationPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponSpuRelation/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询优惠券与产品关联详细
export function getCouponSpuRelation(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/CouponSpuRelation/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增优惠券与产品关联
export function addCouponSpuRelation(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponSpuRelation/save',
    method: 'post',
    data: data
  })
}

// 修改优惠券与产品关联
export function updateCouponSpuRelation(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponSpuRelation/update',
    method: 'put',
    data: data
  })
}

// 删除优惠券与产品关联
export function delCouponSpuRelation(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponSpuRelation/delete',
    method: 'delete',
    data: ids
  })
}


// 导出优惠券与产品关联属性
export function exportCouponSpuRelation(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/CouponSpuRelation/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出couponSpuRelation数据.xlsx';
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

