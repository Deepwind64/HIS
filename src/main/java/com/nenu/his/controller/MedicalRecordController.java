package com.nenu.his.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.bean.BeanUtil;
import com.nenu.his.dao.CheckRequestMapper;
import com.nenu.his.pojo.dto.AddDiseaseDTO;
import com.nenu.his.pojo.dto.DiseaseQueryDTO;
import com.nenu.his.pojo.dto.MedicalRecordDTO;
import com.nenu.his.pojo.po.CheckRequest;
import com.nenu.his.pojo.po.Disease;
import com.nenu.his.pojo.po.MedicalRecord;
import com.nenu.his.pojo.vo.DiseaseVO;
import com.nenu.his.service.MedicalRecordService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SaCheckLogin
@RestController
@RequestMapping("/medical-record")
public class MedicalRecordController {

    @Resource
    MedicalRecordService medicalRecordService;

    @Resource
    CheckRequestMapper checkRequestMapper;

    /**
     * 创建病人病历信息
     */
    @PostMapping("/add")
    public Integer addMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = MedicalRecord.builder().registerId(medicalRecordDTO.getRegisterId()).build();
        medicalRecordService.save(medicalRecord);
        return medicalRecord.getId();
    }

    @PostMapping("/update")
    public void updateMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = new MedicalRecord();
        BeanUtil.copyProperties(medicalRecordDTO, medicalRecord);
        medicalRecordService.updateById(medicalRecord);
    }

    @GetMapping("/search-disease")
    public List<DiseaseVO> searchDisease(DiseaseQueryDTO diseaseQueryDTO) {
        return medicalRecordService.searchDisease(diseaseQueryDTO.getDiseaseCode(), diseaseQueryDTO.getDiseaseName());
    }

    @PostMapping("/add-disease")
    public void addDisease(@RequestBody AddDiseaseDTO addDiseaseDTO) {
        medicalRecordService.addDiseaseToMedicalRecord(addDiseaseDTO.getMedicalRecordId(), addDiseaseDTO.getDiseaseId());
    }
}
