package com.nenu.his.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nenu.his.dao.EmployeeMapper;
import com.nenu.his.pojo.dto.AvailableDoctorDTO;
import com.nenu.his.pojo.po.Employee;
import com.nenu.his.pojo.vo.EmployeeVO;
import com.nenu.his.pojo.vo.PermitVO;
import com.nenu.his.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import com.nenu.his.pojo.po.Permit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<PermitVO> getPermitList(Integer userId) {
        List<Permit> permitList = employeeMapper.getPermitList(userId);
//        List<PermitVO> permitVOList = new ArrayList<>();
        Map<Integer, PermitVO> permitVOMap = new HashMap<>();
        // 保存父结点
        for (Permit permit : permitList) {
            if (ObjectUtil.isNull(permit.getPermitParent())) {
                PermitVO permitVO = new PermitVO();
                BeanUtil.copyProperties(permit, permitVO);
                permitVO.setPermitList(new ArrayList<>());
                permitVOMap.put(permit.getId(), permitVO);
            }
        }

        for (Permit permit : permitList) {
            if (ObjectUtil.isNotNull(permit.getPermitParent())) {
                PermitVO permitParentVO = permitVOMap.get(permit.getPermitParent());
                PermitVO permitVO = new PermitVO();
                BeanUtil.copyProperties(permit, permitVO);
                permitParentVO.getPermitList().add(permitVO);
            }
        }
        return new ArrayList<>(permitVOMap.values());
    }

    @Override
    public List<EmployeeVO> getAvailableDoctor(AvailableDoctorDTO availableDoctorDTO) {
        LocalDateTime dateTime = availableDoctorDTO.getDateTime();
        Integer week_rule_index = (dateTime.getDayOfWeek().getValue() - 1) * 2 + (dateTime.getHour() < 12 ? 0 : 1);

        return employeeMapper.getAvailableDoctor(availableDoctorDTO.getDeptName(),
                availableDoctorDTO.getRegisterLevelName(), week_rule_index);
    }
}
