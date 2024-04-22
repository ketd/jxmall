import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询秒杀活动场次列表
export function listSeckillSessionPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SeckillSession/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询秒杀活动场次详细
export function getSeckillSession(id) {
  return axios({
    baseURL: baseURL,
    url: `/coupon/SeckillSession/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增秒杀活动场次
export function addSeckillSession(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SeckillSession/save',
    method: 'post',
    data: data
  })
}

// 修改秒杀活动场次
export function updateSeckillSession(data) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SeckillSession/update',
    method: 'put',
    data: data
  })
}

// 删除秒杀活动场次
export function delSeckillSession(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SeckillSession/delete',
    method: 'delete',
    data: ids
  })
}


// 导出秒杀活动场次属性
export function exportSeckillSession(ids) {
  return axios({
    baseURL: baseURL,
    url: '/coupon/SeckillSession/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出seckillSession数据.xlsx';
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

