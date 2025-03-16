package com.nenu.his.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nenu.his.common.enumeration.RegisterState;
import com.nenu.his.pojo.dto.*;
import com.nenu.his.pojo.po.MedicalRecord;
import com.nenu.his.pojo.po.Register;
import com.nenu.his.pojo.vo.*;
import com.nenu.his.service.EmployeeService;
import com.nenu.his.service.MedicalRecordService;
import com.nenu.his.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SaCheckLogin
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Resource
    RegisterService registerService;

    @Resource
    EmployeeService employeeService;


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
        return employeeService.getAvailableDoctor(availableDoctorDTO);
    }

    @PostMapping("/add-register")
    public void addRegister(@RequestBody @Validated RegisterDTO registerDTO){
        registerService.addRegister(registerDTO);
    }

    @GetMapping("/doctor/count-end")
    public Integer countEndRegister(Integer employeeId){
        LocalDateTime today = LocalDateTime.now();
        return Math.toIntExact(registerService.count(new LambdaQueryWrapper<Register>()
                .eq(Register::getEmployeeId, employeeId)
                .eq(Register::getVisitState, RegisterState.REGISTER_STATE_END.getNumber())
                .between(Register::getVisitDate, today.with(LocalTime.MIN), today.with(LocalTime.MAX))));
    }
    /**
     * 根据医生id查询当前挂号人数
     */
    @GetMapping("/doctor/count")
    public Integer countRegister(Integer employeeId){
        LocalDateTime today = LocalDateTime.now();
        return Math.toIntExact(registerService.count(new LambdaQueryWrapper<Register>()
                .eq(Register::getEmployeeId, employeeId)
                .eq(Register::getVisitState, RegisterState.REGISTER_STATE_REGISTERED.getNumber())
                .between(Register::getVisitDate, today.with(LocalTime.MIN), today.with(LocalTime.MAX))));
    }

    /**
     * 根据挂号id修改当前挂号状态
     */
    @PostMapping("/state")
    public void updateRegisterState(@RequestBody @Validated UpdateRegisterStateDTO updateRegisterStateDTO){
        registerService.update(new UpdateWrapper<Register>()
                .eq("id", updateRegisterStateDTO.getRegisterId())
                .set("visit_state", updateRegisterStateDTO.getRegisterState()));
    }

    /**
     * 模糊查询病人挂号信息
     */
    @GetMapping("/search")
    public IPage<RegisterMedicalVO> searchRegister(SearchRegisterDTO searchRegisterDTO){
        return registerService.searchRegister(searchRegisterDTO);
    }
}
