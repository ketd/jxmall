import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询spu信息介绍列表
export function listSpuInfodescPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfoDesc/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询spu信息介绍详细
export function getSpuInfodesc(spuId) {
  return axios({
    baseURL: baseURL,
    url: `/product/SpuInfoDesc/info`,
    method: 'get',
    params: {
        spuId: spuId
    }
  })
}

// 新增spu信息介绍
export function addSpuInfodesc(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfoDesc/save',
    method: 'post',
    data: data
  })
}

// 修改spu信息介绍
export function updateSpuInfodesc(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfoDesc/update',
    method: 'put',
    data: data
  })
}

// 删除spu信息介绍
export function delSpuInfodesc(spuIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfoDesc/delete',
    method: 'delete',
    data: spuIds
  })
}


// 导出spu信息介绍属性
export function exportSpuInfodesc(spuIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfoDesc/export',
    method: 'post',
    data: spuIds,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出spuInfodesc数据.xlsx';
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

