package com.sky.controller.admin.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
public class shoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * POST
     * /user/shoppingCart/add
     */
//    private Long dishId;
//    private Long setmealId;
//    private String dishFlavor;
    @PostMapping("/add")
    public Result addCart(@RequestBody  ShoppingCartDTO shoppingCartDTO) {
        Integer integer = shoppingCartService.addCart(shoppingCartDTO);
        return Result.success(integer);

    }
    /**
     * 查看购物车
     * GET
     * /user/shoppingCart/list
     */
    @GetMapping("/list")
    public Result<List<ShoppingCart>> list(){
        List<ShoppingCart > list=shoppingCartService.list();

        return  Result.success(list);
    }
    /**
     * 删除购物车中一个商品
     * POST
     * /user/shoppingCart/sub
     */
    @PostMapping("/sub")
    public Result deleteGoods(@RequestBody  ShoppingCartDTO shoppingCartDTO){
       Integer result= shoppingCartService.deleteGoods(shoppingCartDTO);
       return Result.success(result );
    }
    /**
     * 清空购物车
     * DELETE
     * /user/shoppingCart/clean
     */
    @DeleteMapping("/clean")
    public Result clearCart(){
       Integer result= shoppingCartService.clear();
       return Result.success(result);
    }
}
