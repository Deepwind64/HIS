package com.neuedu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Register {
	private Integer id;
	private String caseNumber;
	private String realName;
	private String gender;
	private String cardNumber;
	private Date birthday;
	private Integer age;
	private String ageType;
	private String homeAddress;
	private Date visitDate;
	private String noon;
	private Integer deptmentId;
	private Integer employeeId ;
	private Integer registLevelId;
	private Integer settleCategoryId;
	private String isBook;
	private String registMethod;
	private Integer visitState;
	private Double registMoney; 
	private List<CheckRequest> checkRequestList =null;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAgeType() {
		return ageType;
	}
	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getNoon() {
		return noon;
	}
	public void setNoon(String noon) {
		this.noon = noon;
	}
	public Integer getDeptmentId() {
		return deptmentId;
	}
	public void setDeptmentId(Integer deptmentId) {
		this.deptmentId = deptmentId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getRegistLevelId() {
		return registLevelId;
	}
	public void setRegistLevelId(Integer registLevelId) {
		this.registLevelId = registLevelId;
	}
	public Integer getSettleCategoryId() {
		return settleCategoryId;
	}
	public void setSettleCategoryId(Integer settleCategoryId) {
		this.settleCategoryId = settleCategoryId;
	}
	public String getIsBook() {
		return isBook;
	}
	public void setIsBook(String isBook) {
		this.isBook = isBook;
	}
	public String getRegistMethod() {
		return registMethod;
	}
	public void setRegistMethod(String registMethod) {
		this.registMethod = registMethod;
	}
	public Integer getVisitState() {
		return visitState;
	}
	public void setVisitState(Integer visitState) {
		this.visitState = visitState;
	}
	public Double getRegistMoney() {
		return registMoney;
	}
	public void setRegistMoney(Double registMoney) {
		this.registMoney = registMoney;
	}
	@Override
	public String toString() {
		return "Register [id=" + id + ", caseNumber=" + caseNumber + ", realName=" + realName + ", gender=" + gender
				+ ", cardNumber=" + cardNumber + ", birthday=" + birthday + ", age=" + age + ", ageType=" + ageType
				+ ", homeAddress=" + homeAddress + ", visitDate=" + visitDate + ", noon=" + noon + ", deptmentId="
				+ deptmentId + ", employeeId=" + employeeId + ", registLevelId=" + registLevelId + ", settleCategoryId="
				+ settleCategoryId + ", isBook=" + isBook + ", registMethod=" + registMethod + ", visitState="
				+ visitState + ", registMoney=" + registMoney + "]";
	}
	public List<CheckRequest> getCheckRequestList() {
		return checkRequestList;
	}
	public void setCheckRequestList(List<CheckRequest> checkRequestList) {
		this.checkRequestList = checkRequestList;
	}
	
}
