package com.nenu.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nenu.his.pojo.dto.AvailableDoctorDTO;
import com.nenu.his.pojo.po.Employee;
import com.nenu.his.pojo.vo.EmployeeVO;
import com.nenu.his.pojo.vo.PermitVO;

import java.util.List;

public interface EmployeeService extends IService<Employee> {
    List<PermitVO> getPermitList(Integer userId);

    List<EmployeeVO> getAvailableDoctor(AvailableDoctorDTO availableDoctorDTO);
}
