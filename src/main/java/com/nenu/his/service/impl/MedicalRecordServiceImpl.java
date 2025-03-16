package com.nenu.his.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nenu.his.dao.CheckRequestMapper;
import com.nenu.his.dao.DiseaseMapper;
import com.nenu.his.dao.MedicalRecordMapper;
import com.nenu.his.dao.MedicalTechnologyMapper;
import com.nenu.his.pojo.dto.CheckDTO;
import com.nenu.his.pojo.po.*;
import com.nenu.his.pojo.vo.DiseaseVO;
import com.nenu.his.service.MedicalRecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServiceImpl extends ServiceImpl<MedicalRecordMapper, MedicalRecord> implements MedicalRecordService {
    @Resource
    MedicalRecordMapper medicalRecordMapper;

    @Resource
    DiseaseMapper diseaseMapper;

    @Resource
    CheckRequestMapper checkRequestMapper;

    @Resource
    MedicalTechnologyMapper medicalTechnologyMapper;

    @Override
    public List<DiseaseVO> searchDisease(String code, String name) {
        return diseaseMapper.selectList(new LambdaQueryWrapper<Disease>()
                .like(!code.isEmpty(), Disease::getDiseaseCode, code)
                .like(!name.isEmpty(), Disease::getDiseaseName, name)
        ).stream().map(v -> {
            DiseaseVO diseaseVO = new DiseaseVO();
            BeanUtil.copyProperties(v, diseaseVO);
            return diseaseVO;
        }).toList();
    }

    @Override
    public void addDiseaseToMedicalRecord(Integer medicalRecordId, Integer diseaseId) {
        medicalRecordMapper.addDisease(medicalRecordId, diseaseId);
    }

    @Override
    public IPage<CheckRequest> getFinishedCheck(CheckDTO checkDTO) {
        Page<Register> page = new Page<>(checkDTO.getCurrent(), checkDTO.getSize());
        return checkRequestMapper.getFinishedCheck(page, checkDTO.getName(), checkDTO.getCaseNumber());
    }

    @Override
    public List<MedicalTechnology> searchTechnology(String checkName, String checkCode) {
        return medicalTechnologyMapper.selectList(new LambdaQueryWrapper<MedicalTechnology>()
                .like(!checkName.isEmpty(), MedicalTechnology::getTechName, checkName)
                .like(!checkCode.isEmpty(), MedicalTechnology::getTechCode, checkCode)
        );
    }
}
