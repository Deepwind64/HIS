import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/index";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";

import "./style.css";

// 创建Vue实例
const app = createApp(App);

import * as ElementPlusIconsVue from "@element-plus/icons-vue";

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

// 应用ElementPlus插件
app.use(ElementPlus);

// 路由
app.use(router);

// 挂载到#app元素上
app.mount("#app");
