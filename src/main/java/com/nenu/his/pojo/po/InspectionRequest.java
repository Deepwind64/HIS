package com.neuedu.pojo;

import java.util.Date;

public class InspectionRequest {
	private Integer id;
	private Integer registerId;
	private Integer medicalTechnologyId;
	private String inspectionInfo;
	private String inspectionPosition;
	private Date creationTime;
	private Integer inspectionEmployeeId;
	private Integer inputinspectionEmployeeId;
	private Date inspectionTime;
	private String inspectionResult;
	private String inspectionState;
	private String inspectionRemark;
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
	public String getInspectionInfo() {
		return inspectionInfo;
	}
	public void setInspectionInfo(String inspectionInfo) {
		this.inspectionInfo = inspectionInfo;
	}
	public String getInspectionPosition() {
		return inspectionPosition;
	}
	public void setInspectionPosition(String inspectionPosition) {
		this.inspectionPosition = inspectionPosition;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Integer getInspectionEmployeeId() {
		return inspectionEmployeeId;
	}
	public void setInspectionEmployeeId(Integer inspectionEmployeeId) {
		this.inspectionEmployeeId = inspectionEmployeeId;
	}
	public Integer getInputinspectionEmployeeId() {
		return inputinspectionEmployeeId;
	}
	public void setInputinspectionEmployeeId(Integer inputinspectionEmployeeId) {
		this.inputinspectionEmployeeId = inputinspectionEmployeeId;
	}
	public Date getInspectionTime() {
		return inspectionTime;
	}
	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}
	public String getInspectionResult() {
		return inspectionResult;
	}
	public void setInspectionResult(String inspectionResult) {
		this.inspectionResult = inspectionResult;
	}
	public String getInspectionState() {
		return inspectionState;
	}
	public void setInspectionState(String inspectionState) {
		this.inspectionState = inspectionState;
	}
	public String getInspectionRemark() {
		return inspectionRemark;
	}
	public void setInspectionRemark(String inspectionRemark) {
		this.inspectionRemark = inspectionRemark;
	}
	
}
