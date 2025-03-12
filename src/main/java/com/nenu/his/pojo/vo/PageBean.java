package com.nenu.his.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
	private int pageSize;
	private int currentPage;
	private int totalPage;
	private int totalCount;
}
