package com.nenu.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nenu.his.pojo.po.Register;
import com.nenu.his.pojo.vo.DeptVO;
import com.nenu.his.pojo.vo.EmployeeVO;
import com.nenu.his.pojo.vo.PaymentVO;
import com.nenu.his.pojo.vo.RegisterLevelVO;

import java.util.List;

public interface RegisterService extends IService<Register>{
    List<DeptVO> getAllDept();

    List<RegisterLevelVO> getAllRegisterLevel();

    List<PaymentVO> getAllPayment();

}
