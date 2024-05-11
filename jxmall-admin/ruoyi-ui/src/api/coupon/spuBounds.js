import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询商品spu积分设置列表
export function listSpuBoundsPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SpuBounds/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询商品spu积分设置详细
export function getSpuBounds(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/SpuBounds/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增商品spu积分设置
export function addSpuBounds(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SpuBounds/save',
    method: 'post',
    data: data
  })
}

// 修改商品spu积分设置
export function updateSpuBounds(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SpuBounds/update',
    method: 'put',
    data: data
  })
}

// 删除商品spu积分设置
export function delSpuBounds(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SpuBounds/delete',
    method: 'delete',
    data: ids
  })
}


// 导出商品spu积分设置属性
export function exportSpuBounds(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SpuBounds/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出spuBounds数据.xlsx';
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

