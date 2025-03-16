package com.nenu.his.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nenu.his.pojo.po.MedicalRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedicalRecordMapper extends BaseMapper<MedicalRecord> {
    void addDisease(Integer medicalRecordId, Integer diseaseId);
}
