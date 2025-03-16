import request from "../utils/request";

// 1.添加检查信息
export function addCheckInfo(data) {
  return request({
    url: "/Physician/CheckRequest/addCheckRequest",
    method: "post",
    data,
  });
}

// 2.根据患者id查询当前已出结果的项目
export function getFinishedPro(id) {
  return request({
    url: `/Physician/CheckRequest/findGivenResultCheckRequestByRegister&registerId=${id}`,
    method: "get",
  });
}

// 3.根据疾病名称实现模糊查询
export function getByDiseaseName(name) {
  return request({
    url: `/Physician/CheckRequest/findAllDiseaseByname&diseaseName=${name}`,
    method: "get",
  });
}

// 4.添加病人病历信息
export function addMedicalRecord(data) {
  return request({
    url: "/medical-record/add",
    method: "post",
    data,
  });
}

// 5.更新病人病历信息
export function updateMedicalRecord(data) {
  return request({
    url: "/medical-record/update",
    method: "post",
    data,
  });
}

// 6.添加病历id和疾病id
export function addId(data) {
  return request({
    url: `/Physician/MedicalRecordDisease/addMedicalRecordDisease?medicalRecordId=${data.id1}&diseaseId=${data.id2}`,
    method: "get",
  });
}

// 7.根据病历id查询关联疾病
export function getMedicalRecordById(id) {
  return request({
    url: `/Physician/MedicalRecordDisease/updateMedicalRecordById?medicalRecordId=${id}`,
    method: "get",
  });
}

// 8.根据名称和编号模糊查询检查项目
export function getByIdName(data) {
  return request({
    url: "/check/search-technology",
    method: "get",
    params: data,
  });
}

// 9.添加处方信息
export function addPrescription(data) {
  return request({
    url: "/Physician/Prescription/addPrescription",
    method: "post",
    data,
  });
}

// 10.根据医生id查询今日已看诊人数
export function getFinishedNum(id) {
  return request({
    url: `/register/doctor/count-end?doctorId=${id}`,
    method: "get",
  });
}

// 11.根据挂号id修改当前挂号状态
export function updateVisitState(data) {
  return request({
    url: "/register/state",
    method: "post",
    data,
  });
}

// 12.根据患者姓名和编号查询已经医生接诊的挂号信息
export function getInfoTreated(data) {
  return request({
    url: "/Physician/Register/findAllRegisterReceivedByNameAndcaseId",
    method: "post",
    data,
  });
}
