<script setup>
import { ElMessage } from "element-plus";
import { ref, computed, onMounted } from "vue";
import { getLocalStorage, setLocalStorage } from "../../utils/common";
import { getInfoById } from "../../api/registration";

// 一、数据
//  1.表格数据
let checkData = ref([]);

// 2.项目金额
let projectAmount = ref("0.00");

// 3.选中的数据
let selectedData = ref([]);

// 4.患者数据
let patientInfo = ref({});

// 二、功能函数

// 1.处理选中的数据
function dealSelectedDate(value) {
  selectedData.value = value;

  // 计算项目金额（累加）
  projectAmount.value = selectedData.value
    .reduce((total, item) => {
      const techPrice = parseFloat(item.techPrice);

      if (!isNaN(techPrice)) {
        total += techPrice;
      }
      return total;
    }, 0)
    .toFixed(2);
}

// 2.结算函数
function chargeProject() {
  // 判断是否选择了结算项目
  if (selectedData.value.length === 0) {
    ElMessage({
      message: "没有选择收费结算的项目",
      type: "warning",
    });
    return;
  }

  // 判断患者信息是否完整
  const patientIncomplete = Object.values(patientInfo.value).some(
    (value) => !value
  );
  if (patientIncomplete) {
    ElMessage({
      message: "患者信息不完整",
      type: "warning",
    });
    return;
  }

  ElMessage({
    message: "收费结算成功！",
    type: "success",
  });
}

// 3.根据挂号编号和姓名查询挂号信息
function gainBaseInfo() {
  let baseInfo = getLocalStorage("patientInfo");
  let obj = {
    realName: baseInfo.realName,
    caseNumber: baseInfo.caseNumber,
  };
  getInfoById(obj).then((response) => {
    console.log("根据挂号编号和姓名查询挂号信息", response);

    patientInfo.value = response.records[0];
  });
}

// 4.自动生成createTime
function generateRandomDate() {
  // 随机生成年份 (2021-2024)
  const year = Math.floor(Math.random() * 4) + 2021;

  // 随机生成月份 (1-12)
  const month = Math.floor(Math.random() * 12) + 1;

  // 计算该月的天数
  const daysInMonth = new Date(year, month, 0).getDate();

  // 随机生成天数 (1 - 天数)
  const day = Math.floor(Math.random() * daysInMonth) + 1;

  // 返回生成的日期，格式为 'yyyy-MM-dd'
  return `${year}-${String(month).padStart(2, "0")}-${String(day).padStart(
    2,
    "0"
  )}`;
}

// 三、页面一加载进行的函数
onMounted(() => {
  gainBaseInfo();

  let checkInfo = getLocalStorage("checkInfo");

  checkData.value = checkInfo.map((item) => {
    return {
      ...item,
      createTime: generateRandomDate(),
    };
  });
});
</script>

<template>
  <div>
    <el-divider />
    <div class="headline">
      <el-icon><DocumentChecked /></el-icon>窗口收费
    </div>
    <el-divider />
    <div class="title">患者信息查询</div>
    <div class="search">
      <div class="input">
        <el-icon><Tickets /></el-icon>病历号：
        <el-input
          style="width: 350px"
          placeholder="病历号"
          size="large"
          clearable
          v-model="patientInfo.caseNumber"
          :disabled="true"
        />
      </div>
      <div class="input">
        <el-icon><User /></el-icon>患者名：
        <el-input
          style="width: 350px"
          placeholder="请输入患者名"
          size="large"
          clearable
          v-model="patientInfo.realName"
        />
      </div>
      <el-button type="primary" size="large" class="btn">搜索</el-button>
    </div>
    <el-divider />
    <div class="title">患者信息确认</div>
    <div class="confirm">
      <div class="input">
        <el-icon><User /></el-icon>
        患者名：
        <el-input
          style="width: 200px"
          placeholder="患者名"
          size="large"
          :disabled="true"
          v-model="patientInfo.realName"
        />
      </div>
      <div class="input">
        <el-icon><Postcard /></el-icon>
        身份证号：
        <el-input
          style="width: 200px"
          placeholder="身份证号"
          size="large"
          :disabled="true"
          v-model="patientInfo.cardNumber"
        />
      </div>
      <div class="input">
        <el-icon><Calendar /></el-icon>
        年龄：
        <el-input
          style="width: 200px"
          placeholder="年龄"
          size="large"
          :disabled="true"
          v-model="patientInfo.age"
        />
      </div>
      <div class="input">
        <el-icon v-if="patientInfo.gender === '女'"><Male /></el-icon>
        <el-icon v-else><Female /></el-icon>性别：
        <el-input
          style="width: 180px"
          placeholder="性别"
          size="large"
          :disabled="true"
          v-model="patientInfo.gender"
        />
      </div>
    </div>
    <div class="button">
      <el-button type="primary" size="large" disabled
        ><el-icon><Tickets /></el-icon>项目金额：{{
          projectAmount
        }}元</el-button
      >
      <el-button
        type="primary"
        size="large"
        :disabled="projectAmount == 0"
        @click="chargeProject"
        ><el-icon><Tickets /></el-icon>收费结算</el-button
      >
    </div>
    <!-- 表格 -->
    <el-table
      :data="checkData"
      :table-layout="'auto'"
      style="width: 90%"
      ref="tableRef"
      @selection-change="dealSelectedDate"
    >
      <el-table-column type="selection" width="80" />

      <el-table-column label="项目名称" width="850">
        <template #default="scope">{{ scope.row.techName }}</template>
      </el-table-column>

      <el-table-column label="单价" width="250">
        <template #default="scope">{{ scope.row.techPrice }}</template>
      </el-table-column>

      <el-table-column label="类型" width="250">
        <template #default="scope">{{ scope.row.techType }}</template>
      </el-table-column>

      <el-table-column label="开立时间" width="190">
        <template #default="scope">{{ scope.row.createTime }}</template>
      </el-table-column>
    </el-table>
  </div>
</template>
  
<style scoped>
.headline {
  display: flex;
  align-items: center;
}
.title {
  font-weight: bold;
  height: 60px;
  line-height: 60px;
  color: #303133;
}
.search,
.confirm {
  display: flex;
  align-items: center;
}
.input {
  display: flex;
  align-items: center;
  justify-content: center;
}
.input:nth-child(2) {
  margin-left: 180px;
}
.input:nth-child(3) {
  margin-left: 180px;
}
.input:nth-child(4) {
  margin-left: 180px;
}
.button {
  margin: 15px 0;
}
.btn {
  margin-left: 80px;
}
</style>