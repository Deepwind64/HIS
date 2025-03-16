import axios from "axios";
import { ElMessage, ElLoading } from "element-plus";
import { getLocalStorage, setLocalStorage } from "../utils/common";

const service = axios.create({
  baseURL: "http://47.92.210.237:8980",
  timeout: 5000,
  headers: {
    "Content-Type": "application/json",
  },
});

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    if (getLocalStorage("token")) {
      config.headers["token"] = getLocalStorage("token");
    }
    // 启动 Loading 动画
    config._loading = ElLoading.service({
      text: "加载中...",
      background: "rgba(0, 0, 0, 0.1)",
    });
    return config;
  },
  (error) => {
    // 错误请求时关闭 Loading 动画
    if (error.config._loading) {
      error.config._loading.close();
    }
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    // 请求成功时，关闭 Loading 动画
    if (response.config._loading) {
      response.config._loading.close();
    }
    console.log(response);
    // 判断状态码
    if (response.data.code === 0) {
      return response.data.data;
    } else {
      ElMessage({
        message: "请求失败",
        type: "error",
      });
      return Promise.reject(new Error("请求失败"));
    }
  },
  (error) => {
    // 请求失败时，关闭 Loading 动画
    if (error.config && error.config._loading) {
      error.config._loading.close();
    }

    const errorMessage =
      error.response && error.response.status === 400
        ? error.response.data.msg || "请求失败"
        : "请求失败，请稍后重试";
    // 错误消息提示
    ElMessage({
      message: errorMessage,
      type: error.response && error.response.code === 400 ? "warning" : "error", // 判断提示框类型
    });

    // return Promise.reject(new Error(errorMessage));
  }
);

export default service;
