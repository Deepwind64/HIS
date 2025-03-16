<script setup>
import { onMounted, ref } from "vue";
import { ElMessage } from "element-plus";
import { getLocalStorage, setLocalStorage } from "../../../utils/common";

import { getInfoById, getAllDisease } from "../../../api/registration";
import { updateMedicalRecord } from "../../../api/physician";
import { useRouter } from "vue-router";

const router = useRouter();
// 一、数据

// 1.患者基本信息
let patientInfo = ref({
  name: "张三",
  medicalRecordId: "10086",
  age: "23",
  gender: "男",
});
// 2.选中的数据
let selectedData = ref([]);

// 3.患者病历信息
let medicalRecordInfo = ref({
  id: "",
  // 挂号id
  // registerId: "",
  // 主诉
  readme: "阵发性咳嗽半月",
  // 现病史
  presentDisease:
    "半月前受凉后开始咳嗽，呈阵发性，无畏冷发热，无咯血及胸痛，伴有少量的白色黏稠痰。",
  // 现病治疗情况
  presentTreat: "曾服止咳糖浆等3天，效果不好。",
  // 既往史
  history:
    "既往有10年余慢性咳嗽史，曾诊断为“慢性支气管炎”，不吸烟。否认肺结核病史",
  // 过敏史
  allergy: "无",
  // 诊断结果
  diagnosis: "",
  // 检查/检验建议
  proposal: "血常规、胸片",
  // 注意事项
  precaution: "无",
  //
  physique:
    "BP 128/80mmHg,无呼吸困难，唇不发绀，双肺有散在干性啰音，未闻及湿啰性啰音，心率90次/min，律齐，无杂音，腹平软无压痛，肝脾未触及，双下肢无浮肿",
  //
  cure: "",
});

// 4.疾病表格数据
let tableData = ref([]);

// 5.allTable 用来引用 el-table
let decideTable = ref();
let allTable = ref();

// 6.所有疾病数据
let allDisease = ref([]);

// 7.// 存储临时选中的疾病数据
let selectedDisease = ref([]);

// 8.医生选择的疾病数据
let decideDisease = ref();

// 9.查询的信息
let searchInfo = ref({
  // 检查名称
  diseaseName: "伤寒",
  // 检查编号
  diseaseCode: "",
});

// 10.抽屉显示 / 隐藏的变量;
let drawer = ref(false);

// 二、功能函数

// 1.处理选中的数据
function dealSelectedDate(value) {
  selectedDisease.value = value;
}

// 2.删除选中的数据
function deleteSelectedData() {
  if (selectedDisease.value.length === 0) {
    // 检查选中的疾病是否为空
    ElMessage({
      message: "请先选择要删除的项目！",
      type: "warning",
    });
    return;
  }
  if (selectedDisease.value.length === decideDisease.value.length) {
    allTable.value.clearSelection();
  }
  console.log("选中的数据:", selectedDisease.value);
  console.log("删除前的数据:", decideDisease.value);

  decideDisease.value = decideDisease.value.filter(
    (item) =>
      !selectedDisease.value.some((selectedItem) => selectedItem.id === item.id) // 使用 id 判断是否匹配
  );

  // 清空选中的数据
  selectedDisease.value = [];

  ElMessage({
    message: "删除成功！",
    type: "success",
  });
}

// 3.清空表格
function resetTable() {
  const fieldsToClear = [
    "readme",
    "presentDisease",
    "presentTreat",
    "history",
    "allergy",
    "diagnosis",
    "proposal",
    "precaution",
    "checkContent",
  ];

  fieldsToClear.forEach((field) => {
    medicalRecordInfo.value[field] = field === "checkContent" ? [] : "";
  });
  decideTable.value.clearSelection();
  allTable.value.clearSelection();
  decideDisease.value = [];
}

// 4.处理选中的疾病
function handleSelection(value) {
  selectedDisease.value = value;
  selectedData.value = value;
}

// 5.确认添加选中的疾病
function addDisease() {
  if (selectedDisease.value.length === 0) {
    ElMessage({
      message: "请先选择至少一个疾病！",
      type: "warning",
    });
  } else {
    decideDisease.value = [...selectedDisease.value];
    // 清空选中的疾病
    selectedDisease.value = [];

    drawer.value = false;

    ElMessage({
      message: "疾病添加成功！",
      type: "success",
    });
    console.log("已选择的疾病：", decideDisease.value);
  }
}
// 6.获取所有的疾病
function searchCheckPro() {
  if (searchInfo.value.diseaseName) {
    // console.log("搜索");
    let obj = {
      diseaseName: searchInfo.value.diseaseName,
      diseaseCode: searchInfo.value.diseaseCode,
    };
    getAllDisease(obj).then((response) => {
      console.log("获取所有的疾病", response);
      allDisease.value = response.map((item, index) => {
        return { ...item, id: index };
      });
    });
  } else {
    ElMessage({
      message: "请填写完整的信息！",
      type: "warning",
    });
  }
}

// 7.根据挂号编号和姓名查询挂号信息
function gainBaseInfo() {
  let baseInfo = getLocalStorage("patientInfo");
  medicalRecordInfo.value.id = baseInfo.id;
  let obj = {
    realName: baseInfo.realName,
    caseNumber: medicalRecordInfo.value.registerId,
  };
  getInfoById(obj).then((response) => {
    console.log("根据挂号编号和姓名查询挂号信息", response);

    patientInfo.value.name = response.records[0].realName;
    patientInfo.value.medicalRecordId = response.records[0].caseNumber;
    patientInfo.value.age = response.records[0].age;
    patientInfo.value.gender = response.records[0].gender;
  });
}

// 8.更新病人病历信息
function updateData() {
  if (decideDisease.value) {
    let obj = medicalRecordInfo.value;
    updateMedicalRecord(obj).then((response) => {
      console.log("更新病人病历信息", response);
      ElMessage({
        message: "患者病历更新成功！",
        type: "success",
      });
      router.push("/check_request");
    });
  } else {
    ElMessage({
      message: "请选择诊断的疾病！",
      type: "warning",
    });
  }
}

// 三、页面一加载就进行的函数
onMounted(() => {
  gainBaseInfo();
});
</script>

<template>
  <div class="flex gap-2">
    患者信息
    <el-tag type="primary" size="large">姓名： {{ patientInfo.name }}</el-tag>
    <el-tag type="primary" size="large"
      >病历号： {{ patientInfo.medicalRecordId }}</el-tag
    >
    <el-tag type="primary" size="large">年龄： {{ patientInfo.age }}</el-tag>
    <el-tag type="primary" size="large">性别： {{ patientInfo.gender }}</el-tag>
  </div>
  <el-divider />
  <div class="headline">
    <el-icon><DocumentChecked /></el-icon>病历首页
  </div>
  <el-divider />
  <el-descriptions
    class="margin-top"
    title="病案首页"
    :column="1"
    border
    size="large"
  >
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">主诉</div>
      </template>
      <el-input
        v-model="medicalRecordInfo.readme"
        type="textarea"
        placeholder="请输入主诉"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">现病史</div>
      </template>
      <el-input
        v-model="medicalRecordInfo.presentDisease"
        type="textarea"
        placeholder="请输入现病史"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">现病治疗情况</div>
      </template>
      <el-input
        v-model="medicalRecordInfo.presentTreat"
        type="textarea"
        placeholder="请输入现病治疗情况"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">既往史</div>
      </template>
      <el-input
        v-model="medicalRecordInfo.history"
        type="textarea"
        placeholder="请输入既往史"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">过敏史</div>
      </template>
      <el-input
        v-model="medicalRecordInfo.allergy"
        type="textarea"
        placeholder="请输入过敏史"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">诊断</div>
      </template>
      <!-- 表格 -->
      <el-table
        :data="decideDisease"
        :table-layout="'auto'"
        style="width: 100%"
        ref="decideTable"
        @selection-change="dealSelectedDate"
      >
        <el-table-column type="selection" />

        <!-- 疾病编码列 -->
        <el-table-column label="疾病编码" width="250">
          <template #default="scope">{{ scope.row.diseaseCode }}</template>
        </el-table-column>

        <!-- 疾病名称列 -->
        <el-table-column label="疾病名称" width="250">
          <template #default="scope">{{ scope.row.diseaseName }}</template>
        </el-table-column>

        <!-- 国际ICD编码列 -->
        <el-table-column label="国际ICD编码" width="300" show-overflow-tooltip>
          <template #default="scope">{{ scope.row.diseaseICD }}</template>
        </el-table-column>

        <!-- 疾病类型列 -->
        <el-table-column label="疾病类型" width="200">
          <template #default="scope">{{ scope.row.diseaseCategory }}</template>
        </el-table-column>

        <!-- 操作列 -->
        <el-table-column label="操作">
          <template #header>
            <el-button type="primary" link @click="deleteSelectedData"
              >删除</el-button
            >
            <el-button type="primary" link @click="drawer = true"
              >增加</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-drawer v-model="drawer" direction="rtl">
        <template #header>
          <h3>疾病列表</h3>
        </template>

        <div class="confirm_btn">
          <el-input
            v-model="searchInfo.diseaseName"
            style="width: 180px"
            placeholder="请输入检查名称"
            clearable
          />
          <el-input
            v-model="searchInfo.diseaseCode"
            style="width: 180px"
            placeholder="请输入检查编码"
            clearable
          />
          <el-button type="primary" @click="searchCheckPro">搜索</el-button>
          <el-button type="primary" @click="addDisease">添加</el-button>
        </div>
        <el-table
          :data="allDisease"
          @selection-change="handleSelection"
          ref="allTable"
        >
          <el-table-column type="selection" />

          <!-- 疾病名称列 -->
          <el-table-column label="疾病名称" width="300">
            <template #default="scope">{{ scope.row.diseaseName }}</template>
          </el-table-column>

          <!-- 疾病类型列 -->
          <el-table-column label="疾病类型" width="200">
            <template #default="scope">{{
              scope.row.diseaseCategory
            }}</template>
          </el-table-column>
        </el-table>
      </el-drawer>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">检查/检验建议</div>
      </template>
      <el-input
        v-model="medicalRecordInfo.proposal"
        type="textarea"
        placeholder="请输入检查/检验建议"
      />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">注意事项</div>
      </template>
      <el-input
        v-model="medicalRecordInfo.precaution"
        type="textarea"
        placeholder="请输入注意事项"
      />
    </el-descriptions-item>
  </el-descriptions>
  <div class="buttons">
    <el-button type="primary" size="large" @click="updateData">保存</el-button>
    <el-button type="primary" size="large" @click="resetTable">清空</el-button>
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
  font-size: 14px;
}
.buttons {
  margin-top: 12px;
}
.margin-top {
  margin-top: 20px;
}
.confirm_btn {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 15px;
}
</style>