package com.nenu.his.pojo.vo;

import lombok.Data;

@Data
public class PageBean {
	private Integer pageSize;
	private Integer currentPage;
	private Integer totalPage;
	private Integer totalCount;
}
