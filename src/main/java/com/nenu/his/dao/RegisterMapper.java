package com.nenu.his.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nenu.his.pojo.po.Register;
import com.nenu.his.pojo.vo.RegisterMedicalVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterMapper extends BaseMapper<Register> {
    IPage<RegisterMedicalVO> searchRegister(IPage<RegisterMedicalVO> page, Integer employeeId, String caseNumber, String realName);
}
