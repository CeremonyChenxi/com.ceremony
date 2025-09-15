package com.sky.service;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    OrderSubmitVO orderSubmit(OrdersSubmitDTO ordersSubmitDTO);
    Integer updateStatus(OrdersPaymentDTO ordersPaymentDTO );
    OrderVO orderDetail(Long id);
}
