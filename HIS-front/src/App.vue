<script setup>
// import Index from "./views/physician/patient/home_medical_record.vue";
</script>

<template>
  <div id="app">
    <router-view></router-view>
    <!-- <Index></Index> -->
  </div>
</template>

<style lang="less" scoped>
#app {
  width: 100%;
  height: 100vh;
  // overflow-x: hidden;
  // overflow-y: scroll;
  // background: url("./assets/bgi.png") no-repeat;
  background-size: cover;

  -ms-overflow-style: none;
  &::-webkit-scrollbar {
    width: 0 !important;
    height: 0 !important;
  }
}
</style>
