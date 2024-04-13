import axios from 'axios';
const baseURL = process.env.VUE_APP_PRODUCT_BASE_API;

// 分页查询品牌列表
export function listBrandPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/Brand/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询品牌详细
export function getBrand(brandId) {
  return axios({
    baseURL: baseURL,
    url: `/product/Brand/info`,
    method: 'get',
    params: {
        brandId: brandId
    }
  })
}

// 新增品牌
export function addBrand(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/Brand/save',
    method: 'post',
    data: data
  })
}

// 修改品牌
export function updateBrand(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/Brand/update',
    method: 'put',
    data: data
  })
}

// 删除品牌
export function delBrand(brandIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/Brand/delete',
    method: 'delete',
    data: brandIds
  })
}


// 导出品牌属性
export function exportBrand(brandIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/Brand/export',
    method: 'post',
    data: brandIds,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出brand数据.xlsx';
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

