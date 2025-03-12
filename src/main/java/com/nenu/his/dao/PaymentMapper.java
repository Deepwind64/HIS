package com.nenu.his.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nenu.his.pojo.po.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
