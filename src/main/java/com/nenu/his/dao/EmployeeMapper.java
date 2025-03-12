package com.nenu.his.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nenu.his.pojo.po.Employee;
import com.nenu.his.pojo.po.Permit;
import com.nenu.his.pojo.vo.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    List<Permit> getPermitList(Integer userId);

    List<EmployeeVO> getAvailableDoctor(String deptName, String registerLevelName, Integer weekRuleIndex);
}
