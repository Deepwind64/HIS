package com.nenu.his.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nenu.his.pojo.po.CheckRequest;
import com.nenu.his.pojo.po.Register;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckRequestMapper extends BaseMapper<CheckRequest> {
    IPage<CheckRequest> getFinishedCheck(Page<Register> page, String registerName, String caseNumber);
}
