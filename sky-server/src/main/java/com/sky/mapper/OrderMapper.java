package com.sky.mapper;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {
    Integer OrderSubmit(Orders orders);

    Integer updateStatus(OrdersPaymentDTO ordersPaymentDTO) ;
    OrderVO orderDetail(Long id);
}
