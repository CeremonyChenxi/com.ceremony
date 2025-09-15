package com.sky.controller.admin.user;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.entity.Orders;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderReportVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/order")
public class ordersController {
    @Autowired
    private OrderService orderService;
    /**
     * 用户下单
     * POST
     * /user/order/submit
     */
    @PostMapping("/submit")
    public Result<OrderSubmitVO> orderSubmit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        OrderSubmitVO orderSubmitVO=orderService.orderSubmit(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }
    /**
     * 订单支付
     * #349194055
     * PUT
     * /user/order/payment
     */
    @PutMapping("/payment")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersController){
        Integer result=orderService.updateStatus(ordersController);
        OrderPaymentVO orderPaymentVO = new OrderPaymentVO();
        return Result.success(orderPaymentVO);

    }
    /**
     * 查询订单详情
     * GET
     * /user/order/orderDetail/{id}
     */
    @GetMapping("/orderDetail/{id}")
    public Result<OrderVO> orderDetail(@PathVariable Long id){
        OrderVO orderVO= orderService.orderDetail(id);
        return Result.success(orderVO   );
    }
}
