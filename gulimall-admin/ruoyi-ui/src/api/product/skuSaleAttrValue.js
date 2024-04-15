import axios from 'axios';
const baseURL = process.env.VUE_APP_PRODUCT_BASE_API;

// 分页查询sku销售属性&值列表
export function listSkuSaleAttrValuePage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuSaleAttrValue/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询sku销售属性&值详细
export function getSkuSaleAttrValue(id) {
  return axios({
    baseURL: baseURL,
    url: `/product/SkuSaleAttrValue/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增sku销售属性&值
export function addSkuSaleAttrValue(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuSaleAttrValue/save',
    method: 'post',
    data: data
  })
}

// 修改sku销售属性&值
export function updateSkuSaleAttrValue(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuSaleAttrValue/update',
    method: 'put',
    data: data
  })
}

// 删除sku销售属性&值
export function delSkuSaleAttrValue(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuSaleAttrValue/delete',
    method: 'delete',
    data: ids
  })
}


// 导出sku销售属性&值属性
export function exportSkuSaleAttrValue(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/SkuSaleAttrValue/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出skuSaleAttrValue数据.xlsx';
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

