import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询仓库信息列表
export function listWareInfoPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareInfo/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询仓库信息详细
export function getWareInfo(id) {
  return axios({
    baseURL: baseURL,
    url: `/ware/WareInfo/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增仓库信息
export function addWareInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareInfo/save',
    method: 'post',
    data: data
  })
}

// 修改仓库信息
export function updateWareInfo(data) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareInfo/update',
    method: 'put',
    data: data
  })
}

// 删除仓库信息
export function delWareInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareInfo/delete',
    method: 'delete',
    data: ids
  })
}


// 导出仓库信息属性
export function exportWareInfo(ids) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareInfo/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出wareInfo数据.xlsx';
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

