<script setup>
import { onMounted, ref, computed } from "vue";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import { getLocalStorage, removeLocalStorage } from "../../utils/common";

// 一、数据
// 1.获得路由对象
let router = useRouter();

// 2.获得menu数组
const menuList = ref();
// menuList.value = router.options.routes;

// 3.获取本地信息
const emp = getLocalStorage("emp") || {};

// 4.窗口高度
const containerHeight = ref("");
containerHeight.value = `${window.innerHeight - 60}px`;


// 二、在页面初始化时，加载menuList数据
onMounted(() => {
  if (emp != null) {
    menuList.value = Object.values(emp.permitList);
  }
});

// 三、功能函数
function logout() {
  ElMessage({
    message: "成功退出账号！",
    type: "success",
  });
  //删除登录用户的信息
  removeLocalStorage("emp");
  router.push("/");
}

//

</script>


<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <div class="header-left">
          <h3>东软云医院HIS系统</h3>
          
        </div>
        <div class="header-right">
          
          <div class="logo">Neuedu</div>
          <el-dropdown>
            <span class="el-dropdown-link">
              {{ emp.realName }}
              <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-container :style="{ height: containerHeight }">
        <el-aside>
          <el-menu
            class="el-menu-demo"
            :unique-opened="true"
            :router="true"
           
          >
            <!-- 遍历模块 -->
            <el-sub-menu
              v-for="(menu, index) in emp.permitList"
              :key="menu.permitPath"
              :index="String(index)"
            >
              <template #title>
                <el-icon>
                  <component :is="menu.permitIcon"></component>
                </el-icon>
               
                  {{ menu.permitName }}
              </template>

              <el-menu-item
                v-for="child in menu.permitList"
                :key="child.permitPath"
                :index="String(child.permitPath)"
              >
                {{ child.permitName }}
              </el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.header-left {
  margin-left: 50px;
  display: flex;
  align-items: center;
}
.icon {
  margin-left: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
.header-right {
  margin-right: 50px;
  display: flex;
  align-items: center;
}
.el-header {
  background-color: #104d85;
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: white;
}
.logo {
  font-weight: bold;
  font-size: 30px;
}
.el-aside {
  background-color: #d3dce6;
}
.permitName {
  font-size: 16px;
  margin-left: 25px;
}
.el-main {
  background-color: #e9eef3;
}
.el-dropdown-link {
  color: white;
  margin-left: 50px;
  text-align: center;
  outline: none;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  /* width: 200px; */
  min-height: 400px;
}
</style>