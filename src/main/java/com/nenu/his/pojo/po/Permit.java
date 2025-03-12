package com.neuedu.pojo;

import java.util.List;

public class Permit {
	private Integer id;
	private String permitName;
	private String permitPath;
	private String permitIcon;
	private String permitParent;
	private Integer relmark;
	private List<Permit> permitList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermitName() {
		return permitName;
	}
	public void setPermitName(String permitName) {
		this.permitName = permitName;
	}
	public String getPermitPath() {
		return permitPath;
	}
	public void setPermitPath(String permitPath) {
		this.permitPath = permitPath;
	}
	public String getPermitIcon() {
		return permitIcon;
	}
	public void setPermitIcon(String permitIcon) {
		this.permitIcon = permitIcon;
	}
	public String getPermitParent() {
		return permitParent;
	}
	public void setPermitParent(String permitParent) {
		this.permitParent = permitParent;
	}
	public Integer getRelmark() {
		return relmark;
	}
	public void setRelmark(Integer relmark) {
		this.relmark = relmark;
	}
	public List<Permit> getPermitList() {
		return permitList;
	}
	public void setPermitList(List<Permit> permitList) {
		this.permitList = permitList;
	}
	
}
