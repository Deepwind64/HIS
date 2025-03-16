package com.nenu.his.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nenu.his.common.enumeration.Noon;
import com.nenu.his.common.enumeration.RegisterState;
import com.nenu.his.dao.DeptMapper;
import com.nenu.his.dao.PaymentMapper;
import com.nenu.his.dao.RegisterLevelMapper;
import com.nenu.his.dao.RegisterMapper;
import com.nenu.his.pojo.dto.RegisterDTO;
import com.nenu.his.pojo.dto.SearchRegisterDTO;
import com.nenu.his.pojo.po.Register;
import com.nenu.his.pojo.vo.DeptVO;
import com.nenu.his.pojo.vo.PaymentVO;
import com.nenu.his.pojo.vo.RegisterLevelVO;
import com.nenu.his.pojo.vo.RegisterMedicalVO;
import com.nenu.his.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements RegisterService {
    @Resource
    private DeptMapper deptMapper;

    @Resource
    private RegisterLevelMapper registerLevelMapper;

    @Resource
    private PaymentMapper paymentMapper;
    @Resource
    private RegisterMapper registerMapper;

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

    @Override
    public void addRegister(RegisterDTO registerDTO) {
        Register register = new Register();
        BeanUtil.copyProperties(registerDTO, register);
        register.setNoon(Noon.getNoonByNumber(registerDTO.getNoon()).getValue());
        register.setNeedBook(registerDTO.getIsBook() ? "是" : "否");
        register.setRegisterMoney(new BigDecimal(registerDTO.getRegisterMoney()));
        register.setVisitState(RegisterState.REGISTER_STATE_REGISTERED.getNumber());
        registerMapper.insert(register);
    }

    @Override
    public IPage<RegisterMedicalVO> searchRegister(SearchRegisterDTO searchRegisterDTO) {
        Page<Register> page = new Page<>(searchRegisterDTO.getCurrent(), searchRegisterDTO.getSize());
        LambdaQueryWrapper<Register> queryWrapper = new LambdaQueryWrapper<Register>()
                .eq(ObjectUtil.isNotEmpty(searchRegisterDTO.getEmployeeId()),
                        Register::getEmployeeId, searchRegisterDTO.getEmployeeId())
                .like(ObjectUtil.isNotEmpty(searchRegisterDTO.getCaseNumber()),
                        Register::getCaseNumber, searchRegisterDTO.getCaseNumber())
                .like(ObjectUtil.isNotEmpty(searchRegisterDTO.getRealName()),
                        Register::getRealName, searchRegisterDTO.getRealName());


        return registerMapper.selectPage(page, queryWrapper).convert(v -> {
            RegisterMedicalVO registerMedicalVO = new RegisterMedicalVO();
            BeanUtil.copyProperties(v, registerMedicalVO);
            return registerMedicalVO;
        });
    }
}
