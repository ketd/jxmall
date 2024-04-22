import axios from "axios";
const baseURL = process.env.VUE_APP_GULIMALL_ADMIN_BASE_API;

export function getUploadUrl(img) {
  return axios({
    baseURL: baseURL,
    url: '/gulimall-third-party/getUrl',
    method: 'get',
    params: { img: img },
  }).then(response => {
    return response.data; // 响应的数据中包含图片地址
  }).catch(error => {
    console.error('Error fetching brand image:', error);
    return ''; // 返回空字符串以处理错误情况
  });
}
