<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { userLogin } from "../../api/login";
import { useRouter } from "vue-router";
import { setLocalStorage } from "../../utils/common";

const router = useRouter();

// 一、登录数据
const loginInfo = ref({
  loginName: "root",
  password: "123",
});
console.log(loginInfo.value);

// 二、功能函数
// 1.登录函数
function login() {
  if (!loginInfo.value.loginName || !loginInfo.value.password) {
    ElMessage({
      message: "用户名或密码不能为空",
      type: "warning",
    });
    return;
  }
  let obj = {
    realname: loginInfo.value.loginName,
    password: loginInfo.value.password,
  };
  userLogin(obj).then((response) => {
    console.log(response);
    ElMessage({
      message: "登录成功！",
      type: "success",
    });
    setLocalStorage("emp", response);
    setLocalStorage("token", response.token);
    router.push("/layout");
  });
}
</script>
<template>
  <div class="container">
    <div class="loginBox">
      <div class="e-card playing">
        <div class="wave"></div>
        <div class="wave"></div>
        <div class="wave"></div>
        <div class="loginCon">
          <h2>欢迎来到医院信息管理系统！</h2>

          <input
            v-model="loginInfo.loginName"
            type="text"
            class="input"
            placeholder="请输入用户名"
          />
          <input
            type="password"
            class="input"
            placeholder="请输入密码"
            v-model="loginInfo.password"
          />
          <span class="fancy">
            <span class="top-key"></span>
            <span class="text" @click="login">登录</span>
            <span class="bottom-key-1"></span>
            <span class="bottom-key-2"></span>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>
  

<style scoped>
.container {
  width: 100vw;
  min-height: 100vh;
  max-height: 100vh;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  background-image: url("../../assets/bgc.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}
.container img {
  width: 100vw;
  min-height: 100vh;
}
.container .loginBox {
  width: 600px;
}
.e-card {
  margin: 100px auto;
  background: rgba(255, 255, 255, 0.523);
  backdrop-filter: blur(4px);
  box-shadow: 0px 0px 28px 12px rgba(16, 76, 133, 0.4);

  position: relative;
  width: 700px;
  height: 700px;
  border-radius: 16px;
  overflow: hidden;
  margin-left: 150px;
}

.wave {
  position: absolute;
  width: 2500px;
  height: 3500px;
  opacity: 0.6;
  left: 0;
  top: 0;
  margin-left: -50%;
  margin-top: -70%;
  background: linear-gradient(744deg, #104d85, #6588bda7 60%, #2d75b8);
}

.wave:nth-child(2),
.wave:nth-child(3) {
  top: 210px;
}

.playing .wave {
  border-radius: 40%;
  animation: wave 3000ms infinite linear;
}

.wave {
  border-radius: 40%;
  animation: wave 55s infinite linear;
}

.playing .wave:nth-child(2) {
  animation-duration: 5000ms;
}

/* .wave:nth-child(2) {
  animation-duration: 50s;
} */

.playing .wave:nth-child(3) {
  animation-duration: 5000ms;
}

/* .wave:nth-child(3) {
  animation-duration: 45s;
} */

@keyframes wave {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}
.loginCon {
  padding: 100px 0px;
  position: relative;
  z-index: 1000;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.loginCon h1 {
  text-shadow: 0px 0px 10px gray;
  margin: 40px auto;
}
.input {
  border: none;
  outline: none;
  border-radius: 15px;
  padding: 20px;
  background-color: #ccc;
  box-shadow: inset 2px 5px 10px rgba(0, 0, 0, 0.3);
  transition: 300ms ease-in-out;
  width: 500px;
  margin-bottom: 30px;
  font-size: 15px;
}

.input:focus {
  background-color: white;
  transform: scale(1.05);
  box-shadow: 13px 13px 100px #969696, -13px -13px 100px #ffffff;
}
.fancy {
  background-color: transparent;
  border: 2px solid #00004b;
  border-radius: 0;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  /* float: right; */
  font-weight: 700;
  letter-spacing: 0.05em;
  margin: 0;
  outline: none;
  overflow: visible;
  padding: 10px 50px;
  position: relative;
  text-align: center;
  text-decoration: none;
  text-transform: none;
  transition: all 0.3s ease-in-out;
  user-select: none;
  font-size: 20px;
  margin-top: 40px;
}

.fancy .text {
  font-size: 1.125em;
  line-height: 1.33333em;
  /* padding-left: 2em; */
  display: block;
  text-align: left;
  transition: all 0.3s ease-in-out;
  text-transform: uppercase;
  text-decoration: none;
  color: #00004b;
}

.fancy .top-key {
  height: 2px;
  width: 1.5625rem;
  top: -2px;
  left: 0.625rem;
  position: absolute;
  background: #86a5fc;
  background-color: white;
  transition: width 0.5s ease-out, left 0.3s ease-out;
}

.fancy .bottom-key-1 {
  height: 2px;
  width: 1.5625rem;
  right: 1.875rem;
  bottom: -2px;
  position: absolute;
  background: #86a5fc;
  background-color: white;

  transition: width 0.5s ease-out, right 0.3s ease-out;
}

.fancy .bottom-key-2 {
  height: 2px;
  width: 0.625rem;
  right: 0.625rem;
  bottom: -2px;
  position: absolute;
  background: #86a5fc;
  background-color: white;

  transition: width 0.5s ease-out, right 0.3s ease-out;
}

.fancy:hover {
  color: transparent;
  background: #00004b;
}

.fancy:hover::before {
  width: 0.9375rem;
  background: transparent;
}

.fancy:hover .text {
  color: white;
  /* padding-left: 1.5em; */
}

.fancy:hover .top-key {
  left: -2px;
  width: 0px;
}

.fancy:hover .bottom-key-1,
.fancy:hover .bottom-key-2 {
  right: 0;
  width: 0;
}
.loginCon a {
  color: #00004b;
  padding: 40px 0px;
  font-size: 20px;
}
h2 {
  line-height: 120px;
  font-size: 32px;
  text-shadow: 0px 0px 10px white;
}
</style>