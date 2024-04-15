import axios from 'axios';
import {getToken} from "@/utils/auth";
const baseURL = process.env.VUE_APP_PRODUCT_BASE_API;

export function getMenus() {
  return axios({
    baseURL: baseURL,
    url: '/product/Category/list/tree',
    method: 'GET',
  }).catch(error => {
    console.error('Error fetching menus:', error);
  });
}
