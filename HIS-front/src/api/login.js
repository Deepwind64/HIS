import request from "../utils/request";

// 登录
export function userLogin(data) {
  return request({
    url: "/employee/login",
    method: "post",
    data,
  });
}
