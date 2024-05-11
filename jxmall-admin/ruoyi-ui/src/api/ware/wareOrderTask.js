import axios from 'axios';
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;

// 分页查询库存工作单列表
export function listWareOrderTaskPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareOrderTask/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询库存工作单详细
export function getWareOrderTask(id) {
  return axios({
    baseURL: baseURL,
    url: `/ware/WareOrderTask/info`,
    method: 'get',
    params: {
        id: id
    }
  })
}

// 新增库存工作单
export function addWareOrderTask(data) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareOrderTask/save',
    method: 'post',
    data: data
  })
}

// 修改库存工作单
export function updateWareOrderTask(data) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareOrderTask/update',
    method: 'put',
    data: data
  })
}

// 删除库存工作单
export function delWareOrderTask(ids) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareOrderTask/delete',
    method: 'delete',
    data: ids
  })
}


// 导出库存工作单属性
export function exportWareOrderTask(ids) {
  return axios({
    baseURL: baseURL,
    url: '/ware/WareOrderTask/export',
    method: 'post',
    data: ids,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出wareOrderTask数据.xlsx';
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

