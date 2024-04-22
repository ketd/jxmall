import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询商品评价列表
export function listSpuCommentPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuComment/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询商品评价详细
export function getSpuComment(id) {
  return axios({
    baseURL: baseURL,
    url: `/product/SpuComment/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增商品评价
export function addSpuComment(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuComment/save',
    method: 'post',
    data: data
  })
}

// 修改商品评价
export function updateSpuComment(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuComment/update',
    method: 'put',
    data: data
  })
}

// 删除商品评价
export function delSpuComment(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuComment/delete',
    method: 'delete',
    data: ids
  })
}


// 导出商品评价属性
export function exportSpuComment(ids) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuComment/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出spuComment数据.xlsx';
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

