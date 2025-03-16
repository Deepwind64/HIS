package com.nenu.his.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nenu.his.pojo.dto.RegisterDTO;
import com.nenu.his.pojo.dto.SearchRegisterDTO;
import com.nenu.his.pojo.po.Register;
import com.nenu.his.pojo.vo.*;

import java.util.List;

public interface RegisterService extends IService<Register>{
    List<DeptVO> getAllDept();

    List<RegisterLevelVO> getAllRegisterLevel();

    List<PaymentVO> getAllPayment();

    void addRegister(RegisterDTO registerDTO);

    IPage<RegisterMedicalVO> searchRegister(SearchRegisterDTO searchRegisterDTO);
}
