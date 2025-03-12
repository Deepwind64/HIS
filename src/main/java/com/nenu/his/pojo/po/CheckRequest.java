package com.neuedu.pojo;

import java.util.Date;

public class CheckRequest {
	private Integer id;
	private Integer registerId;
	private Integer medicalTechnologyId;
	private String checkInfo;
	private String checkPosition;
	private Date creationTime;
	private Integer checkEmployeeId;
	private Integer inputcheckEmployeeId;
	private Date checkTime;
	private String checkResult;
	private String checkState;
	private String checkRemark;
	private MedicalTechnology medicalTechnology;
	private Employee checkEmployeee;
	private Employee resultEmployee;
	public Employee getCheckEmployeee() {
		return checkEmployeee;
	}
	public void setCheckEmployeee(Employee checkEmployeee) {
		this.checkEmployeee = checkEmployeee;
	}
	public Employee getResultEmployee() {
		return resultEmployee;
	}
	public void setResultEmployee(Employee resultEmployee) {
		this.resultEmployee = resultEmployee;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRegisterId() {
		return registerId;
	}
	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}
	public Integer getMedicalTechnologyId() {
		return medicalTechnologyId;
	}
	public void setMedicalTechnologyId(Integer medicalTechnologyId) {
		this.medicalTechnologyId = medicalTechnologyId;
	}
	public String getCheckInfo() {
		return checkInfo;
	}
	public void setCheckInfo(String checkInfo) {
		this.checkInfo = checkInfo;
	}
	public String getCheckPosition() {
		return checkPosition;
	}
	public void setCheckPosition(String checkPosition) {
		this.checkPosition = checkPosition;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}
	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}
	public Integer getInputcheckEmployeeId() {
		return inputcheckEmployeeId;
	}
	public void setInputcheckEmployeeId(Integer inputcheckEmployeeId) {
		this.inputcheckEmployeeId = inputcheckEmployeeId;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	@Override
	public String toString() {
		return "CheckRequest [id=" + id + ", registerId=" + registerId + ", medicalTechnologyId=" + medicalTechnologyId
				+ ", checkInfo=" + checkInfo + ", checkPosition=" + checkPosition + ", creationTime=" + creationTime
				+ ", checkEmployeeId=" + checkEmployeeId + ", inputcheckEmployeeId=" + inputcheckEmployeeId
				+ ", checkTime=" + checkTime + ", checkResult=" + checkResult + ", checkState=" + checkState
				+ ", checkRemark=" + checkRemark + "]";
	}
	public MedicalTechnology getMedicalTechnology() {
		return medicalTechnology;
	}
	public void setMedicalTechnology(MedicalTechnology medicalTechnology) {
		this.medicalTechnology = medicalTechnology;
	}
	
}
