package com.nenu.his.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nenu.his.pojo.dto.CheckDTO;
import com.nenu.his.pojo.po.CheckRequest;
import com.nenu.his.pojo.po.MedicalTechnology;
import com.nenu.his.service.MedicalRecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SaCheckLogin
@RestController
@RequestMapping("/check")
public class CheckController {
    @Resource
    private MedicalRecordService medicalRecordService;
    @GetMapping("/finished")
    public IPage<CheckRequest> getFinishedCheck(CheckDTO checkDTO) {
        return medicalRecordService.getFinishedCheck(checkDTO);
    }

    @GetMapping("/search-technology")
    public List<MedicalTechnology> searchTechnology(String checkName, String checkCode) {
        return medicalRecordService.searchTechnology(checkName, checkCode);
    }

}
