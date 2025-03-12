package com.nenu.his.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.nenu.his.pojo.dto.AvailableDoctorDTO;
import com.nenu.his.pojo.dto.RegisterDTO;
import com.nenu.his.pojo.po.RegisterLevel;
import com.nenu.his.pojo.vo.DeptVO;
import com.nenu.his.pojo.vo.EmployeeVO;
import com.nenu.his.pojo.vo.PaymentVO;
import com.nenu.his.pojo.vo.RegisterLevelVO;
import com.nenu.his.service.EmployeeService;
import com.nenu.his.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SaCheckLogin
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Resource
    RegisterService registerService;

    @Resource
    EmployeeService EmployeeService;

    @GetMapping("/all-dept")
    public List<DeptVO> getAllDept(){
        return registerService.getAllDept();
    }

    @GetMapping("/all-register-level")
    public List<RegisterLevelVO> getAllRegisterLevel(){
        return registerService.getAllRegisterLevel();
    }

    @GetMapping("/all-payment")
    public List<PaymentVO> getAllPayment(){
        return registerService.getAllPayment();
    }

    @GetMapping("/available-doctor")
    public List<EmployeeVO> getAvailableDoctor(
            @Validated @ModelAttribute AvailableDoctorDTO availableDoctorDTO
    ) {
        return EmployeeService.getAvailableDoctor(availableDoctorDTO);
    }

//    @PostMapping("/add-register")
//    public void addRegister(@RequestBody @Validated RegisterDTO registerDTO){
//        registerService.addRegister(registerDTO);
//    }
}
