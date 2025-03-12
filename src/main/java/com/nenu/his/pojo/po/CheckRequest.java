package com.nenu.his.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("check_request")
public class CheckRequest extends Base{
    /**
     * 检查单id
     */
    @TableField(value = "register_id")
    private Integer registerId;

    @TableField(value = "medical_technology_id")
    private Integer medicalTechnologyId;

    @TableField(value = "check_info")
    private String checkInfo;

    @TableField(value = "check_position")
    private String checkPosition;

    /**
     * 检查医生id
     */
    @TableField(value = "check_employee_id")
    private Integer checkEmployeeId;

    /**
     * 录入检查医生id
     */
    @TableField("inputcheck_employee_id")
    private Integer inputCheckEmployeeId;

    @TableField("check_time")
    private Date checkTime;

    @TableField("check_result")
    private String checkResult;

    /**
     * 检查进行状态
     */
    @TableField("check_state")
    private String checkState;

    @TableField("check_remark")
    private String checkRemark;

    @TableField(exist = false)
    private MedicalTechnology medicalTechnology;

    @TableField(exist = false)
    private Employee checkEmployee;

    @TableField(exist = false)
    private Employee resultEmployee;

    @Override
    public String toString() {
        return "CheckRequest [id=" + id + ", registerId=" + registerId + ", medicalTechnologyId=" + medicalTechnologyId
                + ", checkInfo=" + checkInfo + ", checkPosition=" + checkPosition + ", createTime=" + createTime
                + ", checkEmployeeId=" + checkEmployeeId + ", inputcheckEmployeeId=" + inputCheckEmployeeId
                + ", checkTime=" + checkTime + ", checkResult=" + checkResult + ", checkState=" + checkState
                + ", checkRemark=" + checkRemark + "]";
    }
}
