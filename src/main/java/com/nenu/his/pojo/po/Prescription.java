package com.neuedu.pojo;

import java.util.Date;

public class Prescription {
	private Integer id;
	private Integer registerId;
	private Integer drugId;
	private String drugUsage;
	private Integer drugNumber;
	private Date creationTime;
	private String drugState;
	public Integer getDrugNumber() {
		return drugNumber;
	}
	public void setDrugNumber(Integer drugNumber) {
		this.drugNumber = drugNumber;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	private DrugInfo drug;
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
	public Integer getDrugId() {
		return drugId;
	}
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}
	public String getDrugUsage() {
		return drugUsage;
	}
	public void setDrugUsage(String drugUsage) {
		this.drugUsage = drugUsage;
	}
	public String getDrugState() {
		return drugState;
	}
	public void setDrugState(String drugState) {
		this.drugState = drugState;
	}
	public DrugInfo getDrug() {
		return drug;
	}
	public void setDrug(DrugInfo drug) {
		this.drug = drug;
	}
	@Override
	public String toString() {
		return "Prescription [id=" + id + ", registerId=" + registerId + ", drugId=" + drugId + ", drugUsage="
				+ drugUsage + ", drugNumber=" + drugNumber + ", creationTime=" + creationTime + ", drugState="
				+ drugState + ", drug=" + drug + "]";
	}
	
}
