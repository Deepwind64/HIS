package com.nenu.his.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nenu.his.dao.DiseaseMapper;
import com.nenu.his.dao.MedicalRecordMapper;
import com.nenu.his.pojo.dto.CheckDTO;
import com.nenu.his.pojo.po.CheckRequest;
import com.nenu.his.pojo.po.MedicalRecord;
import com.nenu.his.pojo.po.MedicalTechnology;
import com.nenu.his.pojo.vo.DiseaseVO;
import jakarta.annotation.Resource;

import java.util.List;

public interface MedicalRecordService extends IService<MedicalRecord> {

    List<DiseaseVO> searchDisease(String code, String name);

    void addDiseaseToMedicalRecord(Integer medicalRecordId, Integer diseaseId);

    IPage<CheckRequest> getFinishedCheck(CheckDTO checkDTO);

    List<MedicalTechnology> searchTechnology(String checkName, String checkCode);
}
