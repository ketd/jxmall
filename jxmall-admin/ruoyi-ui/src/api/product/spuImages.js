import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询spu图片列表
export function listSpuImagesPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuImages/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询spu图片详细
export function getSpuImages(id) {
  return axios({
    baseURL: baseURL,
    url: `/product/SpuImages/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增spu图片
export function addSpuImages(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuImages/save',
    method: 'post',
    data: data
  })
}

// 修改spu图片
export function updateSpuImages(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuImages/update',
    method: 'put',
    data: data
  })
}

// 删除spu图片
export function delSpuImages(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuImages/delete',
    method: 'delete',
    data: ids
  })
}


// 导出spu图片属性
export function exportSpuImages(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuImages/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出spuImages数据.xlsx';
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

