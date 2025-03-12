package com.neuedu.pojo;

import java.util.List;

public class Employee {
	private Integer id;
	private String realname;
	private String password;
	private Integer deptmentId;
	private Integer regist_levelId;
	private Integer schedulingId;
	private Integer delmark;
	private Integer role;
	private List<Permit> permitList;
	private String token;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDeptmentId() {
		return deptmentId;
	}
	public void setDeptmentId(Integer deptmentId) {
		this.deptmentId = deptmentId;
	}
	public Integer getRegist_levelId() {
		return regist_levelId;
	}
	public void setRegist_levelId(Integer regist_levelId) {
		this.regist_levelId = regist_levelId;
	}
	public Integer getSchedulingId() {
		return schedulingId;
	}
	public void setSchedulingId(Integer schedulingId) {
		this.schedulingId = schedulingId;
	}
	public Integer getDelmark() {
		return delmark;
	}
	public void setDelmark(Integer delmark) {
		this.delmark = delmark;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public List<Permit> getPermitList() {
		return permitList;
	}
	public void setPermitList(List<Permit> permitList) {
		this.permitList = permitList;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
