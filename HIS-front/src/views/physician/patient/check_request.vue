<script setup>
import { onMounted, ref } from "vue";
import { ElMessage } from "element-plus";
import { getLocalStorage, setLocalStorage } from "../../../utils/common";
import { getInfoById } from "../../../api/registration";
import { getByIdName } from "../../../api/physician";
import { useRouter } from "vue-router";

const router = useRouter();
// 一、数据

// 1.病人基本信息
let patientInfo = ref({
  name: "张三",
  medicalRecordId: "10086",
  age: "23",
  gender: "男",
});

// 2.项目金额
let projectAmount = ref("0.00");

// 3.表格数据
let allCheckPro = ref([]);

// 4.决定要检查的数据
let decideCheckPro = ref([]);

// 5.选中的数据
let selectedData = ref([]);

// 6.检查的信息
let checkInfo = ref({
  // 目的要求
  checkPurpose:
    "监测伤寒引起的并发症，如肠穿孔、脓毒症等，以便及时干预。确认患者是否感染伤寒，明确诊断。评估患者体内细菌的耐药性，选择合适的抗生素治疗。",
  // 检验部位
  checkPart: "血液标本（血液培养）用于检测伤寒沙门氏菌（Salmonella typhi）",
  // 备注
  remarks:
    "若患者有长期高热、肠道症状或体弱，需密切观察病情变化。需要注意伤寒沙门氏菌的抗药性情况，并及时调整治疗方案。建议患者在治疗期间隔离，以防止疾病传播。",
  // 检查内容
  checkContent: decideCheckPro.value,
});

// 6.tableRef 用来引用 el-table
let decideTable = ref();
let allTable = ref();

// 7.抽屉显示/隐藏的变量
let drawer = ref(false);

// 8.查询的信息
let searchInfo = ref({
  // 检查名称
  checkName: "红细胞",
  // 检查编号
  checkCode: "",
});

// 二、功能函数

// 1.处理选中的数据
function dealAllCheck(value) {
  selectedData.value = value;
}
// 2.处理决定的数据
function dealDecideCheck(value) {
  console.log("dealDecideCheck", value);
  // decideCheckPro.value = value;
  selectedData.value = value;
}

// 3.删除选中的数据
function deleteSelectedData() {
  if (decideCheckPro.value.length === 0) {
    ElMessage({
      message: "请先选择要删除的项目！",
      type: "warning",
    });
    return;
  }
  console.log("选中的数据:", selectedData.value);
  console.log("删除前的数据:", decideCheckPro.value);
  decideCheckPro.value = decideCheckPro.value.filter(
    (item) =>
      !selectedData.value.some((selectedItem) => selectedItem.id === item.id)
  );

  // 清空选中的数据
  selectedData.value = [];

  // 如果有表格实例，清除表格的选中状态
  decideTable.value.clearSelection();
  ElMessage({
    message: "删除成功！",
    type: "success",
  });
}

// 4.清空表格
function resetTable() {
  const fieldsToClear = [
    "remarks",
    "checkPurpose",
    "checkPart",
    "checkContent",
  ];

  // 遍历并清空字段
  fieldsToClear.forEach((field) => {
    checkInfo.value[field] = field === "checkContent" ? [] : "";
  });
  decideTable.value.clearSelection();
  decideCheckPro.value = [];
  allTable.value.clearSelection();
}

// 5.根据名称和编号模糊查询检查项目 搜索检查列表
function searchCheckPro() {
  if (searchInfo.value.checkName) {
    console.log("搜索");
    let obj = {
      checkCode: searchInfo.value.checkCode,
      checkName: searchInfo.value.checkName,
    };
    getByIdName(obj).then((response) => {
      console.log("根据名称和编号模糊查询检查项目", response);
      allCheckPro.value = response;
    });
  } else {
    ElMessage({
      message: "请填写完整的信息！",
      type: "warning",
    });
  }
}

// 6.添加检查项目
function addCheckPro() {
  if (selectedData.value.length === 0) {
    ElMessage({
      message: "请先选择至少一个检查项目！",
      type: "warning",
    });
  } else {
    decideCheckPro.value = selectedData.value;
    ElMessage({
      message: "检查项目添加成功！",
      type: "success",
    });
    console.log("selectedData", selectedData.value[0]);
    projectAmount.value = selectedData.value
      .reduce((total, item) => {
        const techPrice = parseFloat(item.techPrice);

        if (!isNaN(techPrice)) {
          total += techPrice;
        }
        return total;
      }, 0)
      .toFixed(2);

    // 清空选中的疾病
    selectedData.value = [];

    drawer.value = false;
    allTable.value.clearSelection();

    console.log("金额", projectAmount.value);
    console.log("已选择的检查项目：", decideCheckPro.value);
  }
}

// 7.添加检查信息 提交申请
function submit() {
  if (!decideCheckPro.value.length) {
    ElMessage({
      message: "请为病人选择检查项目！",
      type: "warning",
    });
    return;
  }
  if (
    checkInfo.value.checkPart &&
    checkInfo.value.checkPurpose &&
    checkInfo.value.remarks
  ) {
    ElMessage({
      message: "提交成功！",
      type: "success",
    });

    checkInfo.value.checkContent = decideCheckPro.value;
    setLocalStorage("checkInfo", checkInfo.value.checkContent);
    router.push("/expense_charge");
  } else {
    ElMessage({
      message: "请填写完整的信息！",
      type: "warning",
    });
  }
}

// 8.根据挂号编号和姓名查询挂号信息
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

// 三、页面一加载就进行的函数
onMounted(() => {
  gainBaseInfo();
});
</script>


<template>
  <div class="flex gap-2">
    患者信息
    <el-tag type="primary" size="large"
      >姓名： {{ patientInfo.realName }}</el-tag
    >
    <el-tag type="primary" size="large"
      >病历号： {{ patientInfo.caseNumber }}</el-tag
    >
    <el-tag type="primary" size="large">年龄： {{ patientInfo.age }}</el-tag>
    <el-tag type="primary" size="large">性别： {{ patientInfo.gender }}</el-tag>
  </div>
  <el-divider />
  <div class="headline">
    <el-icon><DocumentChecked /></el-icon>检查申请
  </div>
  <el-divider />
  <el-button type="primary" size="large" disabled>
    <el-icon><PriceTag /></el-icon>
    项目金额：{{ projectAmount }}元
  </el-button>
  <!-- 表格 -->
  <el-table
    :data="decideCheckPro"
    :table-layout="'auto'"
    style="width: 100%"
    ref="decideTable"
    @selection-change="dealDecideCheck"
  >
    <el-table-column type="selection" />

    <el-table-column label="检查编码" width="250">
      <template #default="scope">{{ scope.row.techCode }}</template>
    </el-table-column>

    <el-table-column label="检查名称" width="900">
      <template #default="scope">{{ scope.row.techName }}</template>
    </el-table-column>

    <el-table-column label="单价" width="100">
      <template #default="scope">{{ scope.row.techPrice }}</template>
    </el-table-column>

    <el-table-column label="费用分类" width="150">
      <template #default="scope">{{ scope.row.techType }}</template>
    </el-table-column>

    <el-table-column label="操作">
      <template #header>
        <el-button type="primary" link @click="deleteSelectedData"
          >删除</el-button
        >
        <el-button type="primary" link @click="drawer = true">增加</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-drawer v-model="drawer" direction="rtl">
    <template #header>
      <h3>检查列表</h3>
    </template>
    <div class="searchInfo">
      <el-input
        v-model="searchInfo.checkName"
        style="width: 200px"
        placeholder="请输入检查名称"
        size="large"
        clearable
      />
      <el-input
        v-model="searchInfo.checkCode"
        style="width: 170px"
        placeholder="请输入检查编码"
        size="large"
        clearable
      />
      <el-button type="primary" size="large" @click="searchCheckPro"
        >搜索</el-button
      >
      <el-button type="primary" size="large" @click="addCheckPro"
        >添加</el-button
      >
    </div>
    <el-table
      :data="allCheckPro"
      :table-layout="'auto'"
      ref="allTable"
      @selection-change="dealAllCheck"
    >
      <el-table-column type="selection" />

      <el-table-column label="检查名称" width="300">
        <template #default="scope">{{ scope.row.techName }}</template>
      </el-table-column>

      <el-table-column label="单价" width="100">
        <template #default="scope">{{ scope.row.techPrice }}</template>
      </el-table-column>

      <el-table-column label="费用分类" width="120">
        <template #default="scope">{{ scope.row.techType }}</template>
      </el-table-column>
    </el-table>
  </el-drawer>

  <el-descriptions
    class="margin-top"
    title="医嘱"
    :column="1"
    border
    size="large"
    label-width="160"
  >
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">目的要求：</div>
      </template>
      <el-input
        v-model="checkInfo.checkPurpose"
        :rows="4"
        type="textarea"
        placeholder="请输入目的要求"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">检验部位：</div>
      </template>
      <el-input
        v-model="checkInfo.checkPart"
        type="textarea"
        placeholder="请输入检验部位"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">备注：</div>
      </template>
      <el-input
        v-model="checkInfo.remarks"
        :rows="4"
        type="textarea"
        placeholder="请输入检验事项"
      />
    </el-descriptions-item>
  </el-descriptions>
  <el-divider />
  <div class="buttons">
    <el-button type="primary" size="large" @click="submit">申请提交</el-button>
    <el-button type="primary" size="large" @click="resetTable"
      >清空表格</el-button
    >
  </div>
</template>


<style scoped>
.el-tag {
  margin-left: 15px;
}
.gap-2 {
  font-size: 14px;
}
.headline {
  display: flex;
  align-items: center;
  /* font-size: 18px; */
}
.title {
  /* font-size: 14px; */
  font-weight: bold;
  height: 60px;
  line-height: 60px;
  color: rgb(102, 102, 102);
}
.el-textarea__inner {
  height: 70px !important;
}
.buttons {
  margin-top: 12px;
}
.margin-top {
  margin-top: 20px;
}
.searchInfo {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
