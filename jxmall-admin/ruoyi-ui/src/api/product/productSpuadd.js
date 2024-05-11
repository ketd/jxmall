import axios from "axios";
const baseURL = process.env.VUE_APP_JXMALL_ADMIN_BASE_API;
export function getAttrGroupWithoutAttrs(cateLogId) {
  return axios({
    baseURL: baseURL,
    url: '/product/AttrGroup/list/withoutAttrs',
    method: 'get',
    params: {
      cateLogId
    }
  })
}


export function addSpuInfo(SpuSaveVo){
  return axios({
    baseURL: baseURL,
    url: '/product/SpuInfo/save/info',
    method: 'post',
    data: SpuSaveVo
  })
}
