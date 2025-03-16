import request from "../utils/request";

// 一、挂号模块

// 1.查询所有部门信息 => 挂号科室
export function getDeptlist() {
  return request({
    url: `/register/all-dept`,
    method: "get",
  });
}

// 2.查询所有部门名称及对应的id
export function getDeptname() {
  return request({
    url: "/registration/findAllDepartmentname",
    method: "get",
  });
}

// 3.查询当前值班医生 => 看诊医生列表
export function getDoctorlist(data) {
  return request({
    url: "/register/available-doctor",
    method: "get",
    params: data,
  });
}

// 4.修改检查申请表的状态
export function changeApplystatus(data) {
  return request({
    url: "/registration/updateCheckremarkbyId",
    method: "get",
    params: data,
  });
}

// 5.添加挂号人员信息
export function addRegister(data) {
  return request({
    url: "/register/add-register",
    method: "post",
    data,
  });
}

// 6.根据医生模糊查询病人挂号信息
export function getInfoByDcotor(data) {
  return request({
    url: "/register/search",
    method: "get",
    params: data,
  });
}

// 7.根据挂号编号和姓名查询挂号信息
export function getInfoById(data) {
  return request({
    url: "/register/search",
    method: "get",
    params: data,
  });
}

// 8.查询最大编号
export function getMaxNum() {
  return request({
    url: "/registration/findMaxCaseNumber",
    method: "get",
  });
}

// 9.根据医生id查询当前挂号人数
export function getRegisterNum(id) {
  return request({
    url: `/register/doctor/count?employeeId=${id}`,
    method: "get",
  });
}

// 10.根据患者id和状态查询处方和对应的药品
export function getOpenPrescription(data) {
  return request({
    url: "/registration/findAlOpenPrescription",
    method: "get",
    params: data,
  });
}

// 11.根据处方id修改对应的状态
export function changePrescribestatus(data) {
  return request({
    url: "/registration/UpdatePrescriptionState",
    method: "get",
    params: data,
  });
}

// 12.去重查询查询号别名称
export function getRegistName() {
  return request({
    url: "/registration/findAllregistName",
    method: "get",
  });
}

// 13.查询所有号别信息
export function getRegistLevel() {
  return request({
    url: "/register/all-register-level",
    method: "get",
  });
}

// 14.查询所有的缴费方式
export function getSettleName() {
  return request({
    url: "/register/all-payment",
    method: "get",
  });
}

// 15.获取所有的疾病
export function getAllDisease(data) {
  return request({
    url: "/medical-record/search-disease",
    method: "get",
    params: data,
  });
}
