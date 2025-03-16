<script setup>
import { onMounted, ref, watch } from "vue";
import { getLocalStorage, setLocalStorage } from "../../utils/common";
import { ElMessage } from "element-plus";
import { getRegisterNum, getInfoByDcotor } from "../../api/registration";
import {
  getFinishedNum,
  addMedicalRecord,
  updateVisitState,
} from "../../api/physician";
import { useRouter } from "vue-router";

// 路由
const router = useRouter();

// 一、数据
// 1.排队人数
const queueCount = ref(0);

// 2.已看人数
const seenCount = ref(0);

// 3.登录的个人信息
let emp = getLocalStorage("emp");

// 患者列表数据
const registerList = ref([]);

// 5.页码数据
const pageInfo = ref({
  // 医生id
  employeeId: null,
  // 当前第几页
  current: 1,
  // 每一页多少条数据
  size: 10,
  // 挂号人员的id
  caseNumber: "",
  // 挂号人员的姓名
  realName: "",
});

// 6.总页数
const total = ref();

// 二、页面一加载就进行的函数
onMounted(() => {
  if (emp) {
    gainRegisterNum();

    gainFinishedNum();

    gainRegisterInfo();
  }
});

// 三、功能函数

// 1.根据医生模糊查询病人挂号信息
function gainRegisterInfo() {
  pageInfo.value.empId = emp.id;
  let obj = pageInfo.value;
  console.log(obj);
  getInfoByDcotor(obj).then((response) => {
    console.log("根据医生模糊查询病人挂号信息", response);
    registerList.value = response.records;
    queueCount.value = response.total;
    total.value = response.total;
  });
}

// 2.根据医生id查询挂号人数 => 排队人数
function gainRegisterNum() {
  let id = emp.id;
  console.log(id);
  getRegisterNum(id).then((response) => {
    console.log("查询挂号人数", response);
  });
}

// 3.根据医生id查询今日已看诊人数
function gainFinishedNum() {
  let id = emp.id;
  getFinishedNum(id).then((response) => {
    console.log("查询看诊人数", response);
    seenCount.value = response;
  });
}

// 5.分页切换
function showPage(current) {
  pageInfo.value.current = current;
  gainRegisterInfo();
}

// 6.创建病历
function createRecord(index, row) {
  console.log("Index:", index);
  console.log("Row:", row);
  let obj = {
    registerId: row.caseNumber,
  };
  addMedicalRecord(obj).then((response) => {
    console.log("创建病历", response);
    setLocalStorage("patientInfo", row);
    // 跳转路径（需要本地存储/路由传参 => 患者的id）
    router.push("/home_medical_record");
  });
}

// 7.叫号
function callNum(index, row) {
  console.log("Index:", index);
  console.log("Row:", row.caseNumber);
  let obj = {
    registerState: 2,
    registerId: row.caseNumber,
  };
  updateVisitState(obj).then((response) => {
    console.log("根据挂号id修改当前挂号状态", response);
    ElMessage({
      message: "叫号成功！",
      type: "success",
    });
  });
}

// 8.跳号
function skipNum(index, row) {
  console.log("Index:", index);
  console.log("Row:", row.caseNumber);
  let obj = {
    registerState: 4,
    registerId: row.caseNumber,
  };
  updateVisitState(obj).then((response) => {
    console.log("根据挂号id修改当前挂号状态", response);
    ElMessage({
      message: "跳号成功！",
      type: "success",
    });
  });
}

// 9.输入信息搜索
function searchInfo() {
  if (pageInfo.value.realName || pageInfo.value.caseNumber) {
    pageInfo.value.id = null;
    let obj = pageInfo.value;
    console.log(obj);
    getInfoByDcotor(obj).then((response) => {
      console.log("根据病历号和姓名查询挂号信息", response);
      registerList.value = response.records;
      total.value = response.total;
    });
  } else {
    ElMessage({
      message: "请输入搜索信息！",
      type: "warning",
    });
  }
}
</script>

<template>
  <el-tag class="ml-2" color="#d1edc4" size="large">
    今日已看{{ seenCount }}人
  </el-tag>
  <el-tag class="ml-2" color="#f8e3c5" size="large">
    当前有{{ queueCount }}人在排队
  </el-tag>
  <el-divider />
  <h3>患者叫号</h3>
  <el-divider />
  <el-input
    size="large"
    v-model="pageInfo.caseNumber"
    placeholder="请输入患者病历号"
    style="width: 200px; margin-right: 20px"
    clearable
  ></el-input>
  <el-input
    size="large"
    v-model="pageInfo.realName"
    placeholder="请输入患者姓名"
    style="width: 200px; margin-right: 20px"
    clearable
  ></el-input>
  <el-button type="primary" @click="searchInfo" size="large" style="width: 70px"
    >查 询</el-button
  >
  <el-divider />
  <el-table :data="registerList" style="width: 60%">
    <el-table-column label="编号" prop="id" />
    <el-table-column label="患者姓名" prop="realName" />
    <el-table-column label="患者病历号" prop="caseNumber" />
    <el-table-column align="right">
      <template #default="scope">
        <el-button size="small" @click="createRecord(scope.$index, scope.row)">
          创建病历
        </el-button>
        <el-button size="small" @click="callNum(scope.$index, scope.row)">
          叫号
        </el-button>
        <el-button size="small" @click="skipNum(scope.$index, scope.row)">
          跳过
        </el-button>
      </template>
    </el-table-column>
  </el-table>

  <div style="margin-top: 20px">
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      v-model:current-page="pageInfo.current"
      v-model:page-size="pageInfo.size"
      @current-change="showPage"
    />
  </div>
</template>

<style scoped>
.ml-2 {
  margin-right: 30px;
  color: #777;
}
</style>