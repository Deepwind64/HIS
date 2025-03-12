package com.neuedu.pojo;

public class MedicalTechnology {
	private Integer id;
	private String techCode;
	private String techName;
	private String techFormat;
	private Double techPrice;
	private String techType;
	private String priceType;
	private Integer deptmentId;
	private CheckRequest checkRequest =null;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTechCode() {
		return techCode;
	}
	public void setTechCode(String techCode) {
		this.techCode = techCode;
	}
	public String getTechName() {
		return techName;
	}
	public void setTechName(String techName) {
		this.techName = techName;
	}
	public String getTechFormat() {
		return techFormat;
	}
	public void setTechFormat(String techFormat) {
		this.techFormat = techFormat;
	}
	public Double getTechPrice() {
		return techPrice;
	}
	public void setTechPrice(Double techPrice) {
		this.techPrice = techPrice;
	}
	public String getTechType() {
		return techType;
	}
	public void setTechType(String techType) {
		this.techType = techType;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public Integer getDeptmentId() {
		return deptmentId;
	}
	public void setDeptmentId(Integer deptmentId) {
		this.deptmentId = deptmentId;
	}
	public CheckRequest getCheckRequest() {
		return checkRequest;
	}
	public void setCheckRequest(CheckRequest checkRequest) {
		this.checkRequest = checkRequest;
	}
	
}
