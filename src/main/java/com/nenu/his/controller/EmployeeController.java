package com.nenu.his.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nenu.his.common.constant.MessageConstant;
import com.nenu.his.common.enumeration.ErrorCode;
import com.nenu.his.common.exception.BaseException;
import com.nenu.his.pojo.dto.LoginDTO;
import com.nenu.his.pojo.po.Employee;
import com.nenu.his.pojo.vo.LoginVO;
import com.nenu.his.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    RedisTemplate<String, String> redisTemplate;
    @Resource
    EmployeeService employeeService;

    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public LoginVO login(@RequestBody @Validated LoginDTO loginDTO) {
        Employee user = employeeService.getOne(new LambdaQueryWrapper<Employee>()
                .eq(Employee::getRealName, loginDTO.getRealname())
                .eq(Employee::getPassword, loginDTO.getPassword()));

        if (ObjectUtil.isNull(user)) {
            throw new BaseException(ErrorCode.LOGIN_FAILED, MessageConstant.LOGIN_FAILED);
        }

        StpUtil.login(user.getId());

        return LoginVO.builder()
                .id(user.getId())
                .realName(user.getRealName())
                .token(StpUtil.getTokenValue())
                .permitList(employeeService.getPermitList(user.getId()))
                .build();
    }

}
