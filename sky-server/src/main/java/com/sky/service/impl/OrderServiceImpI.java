package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.entity.AddressBook;
import com.sky.entity.Orders;
import com.sky.entity.User;
import com.sky.mapper.AddressBookMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.userMapper;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderServiceImpI implements OrderService {
    @Autowired
    private com.sky.mapper.userMapper userMapper;
    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderVO orderDetail(Long id) {
        OrderVO vo= orderMapper.orderDetail(id);
        return vo;
    }

    @Override
    public Integer updateStatus(OrdersPaymentDTO orderNumber) {
        System.out.println(orderNumber);
        return  orderMapper.updateStatus( orderNumber);
    }

    @Override
    public OrderSubmitVO orderSubmit(OrdersSubmitDTO ordersSubmitDTO) {
        Orders orders = copyOrders(ordersSubmitDTO);
         orderMapper.OrderSubmit(orders);
         //构造vo
        OrderSubmitVO orderSubmitVO = copyOrderSubmitVO(orders);
        return orderSubmitVO;
    }

    private  OrderSubmitVO copyOrderSubmitVO(Orders orders) {
        OrderSubmitVO orderSubmitVO = new OrderSubmitVO();
        orderSubmitVO.setId(orders.getId());
        orderSubmitVO.setOrderAmount(orders.getAmount());
        orderSubmitVO.setOrderNumber(orders.getNumber());
        orderSubmitVO.setOrderTime(orders.getOrderTime());
        return orderSubmitVO;
    }

    private Orders copyOrders(OrdersSubmitDTO ordersSubmitDTO) {
        Orders orders = new Orders();
        //基本信息
        orders.setNumber(UUID.randomUUID().toString());
        orders.setRemark(ordersSubmitDTO.getRemark());
        //用户信息
        orders.setUserId(BaseContext.getCurrentId());
        User userbyid = userMapper.getByid(BaseContext.getCurrentId());
        orders.setUserName(userbyid.getName());
        //地址类
        orders.setAddressBookId(ordersSubmitDTO.getAddressBookId());
        AddressBook addbyid = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
        orders.setAddress(addbyid.getDetail());
        orders.setPhone(addbyid.getPhone());
        orders.setConsignee(addbyid.getConsignee());
        //时间
        orders.setOrderTime(LocalDateTime.now());
        orders.setEstimatedDeliveryTime(ordersSubmitDTO.getEstimatedDeliveryTime());
        //支付
        orders.setPayMethod(ordersSubmitDTO.getPayMethod());
        orders.setAmount(ordersSubmitDTO.getAmount());
        //配送
        orders.setDeliveryStatus(ordersSubmitDTO.getDeliveryStatus());
        orders.setPackAmount(ordersSubmitDTO.getPackAmount());
        orders.setTablewareNumber(ordersSubmitDTO.getTablewareNumber());
        orders.setTablewareStatus(ordersSubmitDTO.getTablewareStatus());
        return orders;
    }
}

