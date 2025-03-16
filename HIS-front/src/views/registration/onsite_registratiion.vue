
<script setup>
import { onMounted, ref } from "vue";
import { ElMessage } from "element-plus";
import {
  getDeptlist,
  getRegistLevel,
  getDoctorlist,
  addRegister,
  getSettleName,
} from "../../api/registration";

// 一、需要后端返回的数据
// 1.科室列表
let deptList = ref([]);
// 2.号别列表
let registlevelList = ref([]);
// 3.看诊医生列表
let empList = ref([]);
// 4.结算类型信息
let settlementTypeList = ref();
// 5.初始号额
let quota = ref();
// 6.已用号额
let numUsed = ref();
// 7.号别对象
let registLevel = ref({});
// 8.格式化时间
let formattedDate = ref("");

// 二、用户填写信息后的数据
// 接收挂号信息的对象
let register = ref({
  // 病历号
  caseNumber: "",
  // 姓名
  realName: "xixi",
  // 性别
  gender: "女",
  // 身份证号
  cardNumber: "65313020050123231X",
  // 出生日期yyyy-MM-dd
  birthday: "",
  // 年龄
  age: 23,
  // 年龄类型（岁、天）
  ageType: "岁",
  // 地址
  homeAddress: "北京市朝阳区",
  // 看诊日期yyyy-MM-dd
  visitDate: "",
  // 午别（0-上午、1-下午）
  noon: null,
  // 挂号科室的id
  departmentId: null,
  // 看诊医生的id
  employeeId: null,
  // 号别的id
  registerLevelId: null,
  // 结算类型的id（自费、市医保）
  settleCategoryId: null,
  // 是否有病历本（true、false）
  isBook: false,
  // 收费方式
  registerMethod: "",
  // 应收金额
  registerMoney: "",
});

// 三、用于渲染选项的数据（死的）
// 1.性别信息
let sexList = ref([
  {
    value: "男",
    label: "男",
  },
  {
    value: "女",
    label: "女",
  },
]);

// 2.年龄信息
let ageType = ref([
  {
    value: "岁",
    label: "岁",
  },
  {
    value: "天",
    label: "天",
  },
]);

// 3.午别信息
let noonList = ref([
  {
    value: 0,
    label: "上午",
  },
  {
    value: 1,
    label: "下午",
  },
]);

// 4.收费方式列表;
let mannertallList = ref([
  {
    lable: "银行卡",
    value: "银行卡",
  },
  {
    lable: "医保卡",
    value: "医保卡",
  },
  {
    lable: "现金",
    value: "现金",
  },
  {
    lable: "微信",
    value: "微信",
  },
  {
    lable: "支付宝",
    value: "支付宝",
  },
]);

// 四、页面加载完成时自动调用
onMounted(() => {
  console.log("挂号页面");

  // 获取挂号科室列表
  gainDeptlist();
  // 获取号别列表
  gainLevelList();
  // 获取结算方式列表
  gainPayType();
  // 生成病历号
  generatecaseNumber();
  // 格式化时间
  formatTime();
  // 判断午别
  judgeNoon();
});

// 五、功能对应的函数
// 1.号别发生改变 => 查询当前值班医生 => 看诊医生列表
function selectEmp() {
  if (register.value.departmentId) {
    let deptName = deptList.value.find(
      (item) => item.id == register.value.departmentId
    )?.deptName;
    let registName = registlevelList.value.find(
      (item) => item.id == register.value.registerLevelId
    )?.name;
    let obj = {
      deptName: deptName, // 科室姓名
      registName: registName, // 科室级别
      dateTime: formattedDate.value, // 当前系统时间
    };
    console.log("对象", obj);
    getDoctorlist(obj).then((response) => {
      console.log("看诊医生列表", response);
      empList.value = response;
    });
  }
}

// 修改应收金额
switch (register.value.registerLevelId) {
  case 1:
    register.value.registerMoney = "50.00";
    quota.value = "35";
    numUsed.value = "5";
    break;
  case 2:
    register.value.registerMoney = "8.00";
    quota.value = "135";
    numUsed.value = "55";
    break;
  case 3:
    register.value.registerMoney = "20.00";
    quota.value = "85";
    numUsed.value = "25";
    break;
  default:
    register.value.registerMoney = "0.00";
    break;
}

// 2.选择病历本
function changeMedicalrecords() {
  register.value.registerMoney = (
    parseFloat(register.value.registerMoney) + (register.value.isBook ? 1 : -1)
  ).toFixed(2);
}

// 3.挂号
function registration() {
  if (validateRegister(register.value)) {
    register.value.birthday = inputDate(register.value.birthday);
    register.value.visitDate = inputDate(register.value.visitDate);

    let obj = register.value;
    addRegister(obj).then((response) => {
      console.log("挂号", response);
      ElMessage.success("挂号成功！");
    });
  }
  console.log(register.value);
}

// 4.获取挂号科室列表
function gainDeptlist() {
  getDeptlist().then((response) => {
    console.log("科室列表", response);
    deptList.value = response;
  });
}

// 5.查询号别列表
function gainLevelList() {
  getRegistLevel().then((response) => {
    console.log("号别列表", response);
    registlevelList.value = response;
  });
}

// 6.查询结算方式方式
function gainPayType() {
  getSettleName().then((response) => {
    console.log("查询结算方式", response);
    settlementTypeList.value = response;
    settlementTypeList.value = settlementTypeList.value.map((item) => ({
      ...item,
      value: parseInt(item.value, 10),
    }));
  });
}

// 7.生成病历号
function generatecaseNumber() {
  const now = new Date();

  const year = now.getFullYear().toString().slice(-2);

  const month = String(now.getMonth() + 1).padStart(2, "0");
  const day = String(now.getDate()).padStart(2, "0");
  const date = month + day;

  const randomDigits = Array.from({ length: 4 }, () =>
    Math.floor(Math.random() * 10)
  ).join("");

  register.value.caseNumber = year + date + randomDigits;
}

// 8.格式化时间
function formatTime() {
  let date = new Date();
  console.log("时间", date);
  register.value.visitDate = date;

  // 转换格式 yyyy-MM-dd HH:mm:ss
  formattedDate.value =
    date.getFullYear() +
    "-" +
    String(date.getMonth() + 1).padStart(2, "0") +
    "-" +
    String(date.getDate()).padStart(2, "0") +
    " " +
    String(date.getHours()).padStart(2, "0") +
    ":" +
    String(date.getMinutes()).padStart(2, "0") +
    ":" +
    String(date.getSeconds()).padStart(2, "0");

  console.log("格式化时间:", formattedDate);
}

// 9.判断午别
function judgeNoon() {
  let date = new Date();

  // 根据时间判断午别
  let hours = date.getHours();
  if (hours < 12) {
    register.value.noon = 0; // 上午
  } else {
    register.value.noon = 1; // 下午
  }

  // console.log("当前午别: ", register.value.noon);
}

// 10.清空信息
function resetInfo() {
  Object.keys(register.value).forEach((key) => {
    if (key === "isBook") {
      register.value[key] = false;
    } else {
      register.value[key] = "";
    }
  });
  quota.value = null;
  numUsed.value = null;
}

// 11.判空处理
function validateRegister(register) {
  if (
    Object.values(register).some((value) => {
      // 针对不同类型进行处理
      if (typeof value === "string") {
        return value.trim() === ""; // 如果是字符串，判断是否为空字符串
      }
      if (typeof value === "number") {
        return isNaN(value) || value === 0; // 如果是数字，检查是否是 NaN 或 0
      }
    })
  ) {
    ElMessage.warning("请填写完整信息");
    return false;
  }
  return true;
}

// 12.处理日期格式
function inputDate(inputDate) {
  const date = new Date(inputDate);

  const time = date.toISOString().split("T")[0];
  return time;
}
</script>

<template>
  <el-divider />
  <h3>窗口挂号</h3>
  <el-divider />
  <el-button type="primary" @click="registration">挂号</el-button>
  <el-button type="primary" @click="resetInfo">清空</el-button>

  <el-descriptions class="margin-top" title="基本信息" :column="3" border>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <user />
          </el-icon>
          病历号:
        </div>
      </template>
      <el-input
        v-model="register.caseNumber"
        style="width: 240px"
        placeholder="请输入病历号"
        size="large"
        :disabled="true"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          姓名:
        </div>
      </template>
      <el-input
        v-model="register.realName"
        style="width: 240px"
        placeholder="请输入姓名"
        size="large"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <location />
          </el-icon>
          性别:
        </div>
      </template>
      <el-select
        v-model="register.gender"
        placeholder="请选择性别"
        size="large"
        style="width: 240px"
      >
        <el-option
          v-for="sex in sexList"
          :key="sex.value"
          :label="sex.label"
          :value="sex.value"
        />
      </el-select>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <tickets />
          </el-icon>
          年龄:
        </div>
      </template>
      <el-input
        v-model="register.age"
        style="width: 120px"
        placeholder="0"
        size="large"
      />
      <el-select
        v-model="register.ageType"
        placeholder="年"
        size="large"
        style="width: 120px"
      >
        <el-option
          v-for="age in ageType"
          :key="age.value"
          :label="age.label"
          :value="age.value"
        />
      </el-select>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <office-building />
          </el-icon>
          出生日期:
        </div>
      </template>
      <div class="demo-date-picker">
        <div class="block">
          <el-date-picker
            v-model="register.birthday"
            type="date"
            placeholder="请选择出生日期"
            size="large"
          />
        </div>
      </div>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          身份证号:
        </div>
      </template>
      <el-input
        v-model="register.cardNumber"
        style="width: 240px"
        placeholder="请输入身份证号"
        maxlength="18"
        size="large"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          地址:
        </div>
      </template>
      <el-input
        v-model="register.homeAddress"
        aceholder="请输入地址"
        size="large"
      />
    </el-descriptions-item>
  </el-descriptions>
  <el-descriptions class="margin-top" title="挂号信息" :column="3" border>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <user />
          </el-icon>

          结算类型:
        </div>
      </template>
      <el-select
        v-model="register.settleCategoryId"
        placeholder="请选择结算类型"
        size="large"
        style="width: 240px"
      >
        <el-option
          v-for="settlement in settlementTypeList"
          :key="settlement.value"
          :label="settlement.label"
          :value="settlement.value"
        />
      </el-select>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          看诊日期：
        </div>
      </template>
      <div class="demo-date-picker">
        <div class="block">
          <el-date-picker
            v-model="register.visitDate"
            type="date"
            placeholder="Pick a day"
            size="large"
            :disabled="true"
          />
        </div>
      </div>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <location />
          </el-icon>
          午别：
        </div>
      </template>
      <el-select
        v-model="register.noon"
        placeholder="午别"
        size="large"
        style="width: 240px"
        :disabled="true"
      >
        <el-option
          v-for="noon in noonList"
          :key="noon.value"
          :label="noon.label"
          :value="noon.value"
        />
      </el-select>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <tickets />
          </el-icon>
          挂号科室：
        </div>
      </template>
      <!-- 需要后端返回科室列表的数据 -->
      <el-select
        v-model="register.departmentId"
        placeholder="请选择挂号科室"
        size="large"
        style="width: 240px"
        @change="selectEmp"
      >
        <el-option
          v-for="dept in deptList"
          :key="dept.id"
          :label="dept.deptName"
          :value="dept.id"
        />
      </el-select>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <tickets />
          </el-icon>
          号别：
        </div>
      </template>
      <!-- 需要后端返回号别列表的数据 -->
      <el-select
        v-model="register.registerLevelId"
        placeholder="请选择号别"
        size="large"
        style="width: 240px"
        @change="selectEmp"
      >
        <el-option
          v-for="level in registlevelList"
          :key="level.id"
          :label="level.name"
          :value="level.id"
        />
      </el-select>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <office-building />
          </el-icon>
          看诊医生：
        </div>
      </template>
      <!-- 需要后端返回看诊医生的数据 -->
      <el-select
        v-model="register.employeeId"
        placeholder="请选择看诊医生"
        size="large"
        style="width: 240px"
      >
        <el-option
          v-for="emp in empList"
          :key="emp.id"
          :label="emp.realName"
          :value="emp.id"
        />
      </el-select>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          初始号额：
        </div>
      </template>
      <el-input
        v-model="quota"
        style="width: 240px"
        size="large"
        :disabled="true"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          已用号额：
        </div>
      </template>
      <el-input size="large" :disabled="true" v-model="numUsed" />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          病历本：
        </div>
      </template>
      <el-switch v-model="register.isBook" @change="changeMedicalrecords" />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          应收金额：
        </div>
      </template>
      <el-input
        v-model="register.registerMoney"
        placeholder="0"
        :disabled="true"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          收费方式：
        </div>
      </template>
      <el-select
        v-model="register.registerMethod"
        placeholder="请选择收费方式"
        size="large"
        style="width: 240px"
      >
        <el-option
          v-for="pay in mannertallList"
          :key="pay.value"
          :label="pay.lable"
          :value="pay.value"
        />
      </el-select>
    </el-descriptions-item>
  </el-descriptions>
</template>


<style scoped>
.margin-top {
  margin-top: 20px;
}
</style>