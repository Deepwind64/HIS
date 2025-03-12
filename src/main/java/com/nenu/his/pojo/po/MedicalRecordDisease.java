package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("medical_record")
public class MedicalRecordDisease {
	@TableField(value = "medical_record_id")
	private Integer medicalRecordId;
	@TableField(value = "disease_id")
	private Integer diseaseId;
}
