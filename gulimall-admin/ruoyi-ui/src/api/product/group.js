import axios from 'axios';
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

// 分页查询属性分组列表
export function listGroupPage(queryParams) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrGroup/list/page',
    method: 'post',
    data: queryParams
  })
}


// 查询属性分组详细
export function getGroup(attrGroupId) {
  return axios({
    baseURL: baseURL,
    url: `/product/AttrGroup/info`,
    method: 'get',
    params: {
        attrGroupId: attrGroupId
    }
  })
}

// 新增属性分组
export function addGroup(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrGroup/save',
    method: 'post',
    data: data
  })
}

// 修改属性分组
export function updateGroup(data) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrGroup/update',
    method: 'put',
    data: data
  })
}

// 删除属性分组
export function delGroup(attrGroupIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrGroup/delete',
    method: 'delete',
    data: attrGroupIds
  })
}


// 导出属性分组属性
export function exportGroup(attrGroupIds) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrGroup/export',
    method: 'post',
    data: attrGroupIds,
    responseType: 'blob' // 设置响应类型为 blob
  }).then(response => {
    const filename = '导出group数据.xlsx';
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

