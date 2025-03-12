package com.nenu.his.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nenu.his.dao.DeptMapper;
import com.nenu.his.dao.PaymentMapper;
import com.nenu.his.dao.RegisterLevelMapper;
import com.nenu.his.dao.RegisterMapper;
import com.nenu.his.pojo.po.Register;
import com.nenu.his.pojo.vo.DeptVO;
import com.nenu.his.pojo.vo.PaymentVO;
import com.nenu.his.pojo.vo.RegisterLevelVO;
import com.nenu.his.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements RegisterService {
    @Resource
    private DeptMapper deptMapper;

    @Resource
    private RegisterLevelMapper registerLevelMapper;

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public List<DeptVO> getAllDept() {
        return deptMapper.selectList(null).stream()
                .map(v -> DeptVO.builder()
                        .id(v.getId())
                        .deptName(v.getDeptName())
                        .build())
                .toList();
    }

    @Override
    public List<RegisterLevelVO> getAllRegisterLevel() {
        return registerLevelMapper.selectList(null).stream()
                .map(v -> RegisterLevelVO.builder()
                        .id(v.getId())
                        .name(v.getRegisterLevelName())
                        .build())
                .toList();
    }

    @Override
    public List<PaymentVO> getAllPayment() {
        return paymentMapper.selectList(null).stream()
                .map(v -> PaymentVO.builder()
                        .value(String.valueOf(v.getId()))
                        .label(v.getSettleName())
                        .build())
                .toList();
    }
}
