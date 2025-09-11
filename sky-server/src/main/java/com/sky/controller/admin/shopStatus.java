package com.sky.controller.admin;

import com.sky.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
public class shopStatus {
    @Autowired
    private RedisTemplate redisTemplate;
    private final String ShopKey="SHOP_STATUS";


    /**
     * 设置营业状态
     * PUT
     * /admin/shop/{status}
     * @return
     */
    @PutMapping("/{status}")
    public Result setShopStatus(@PathVariable Integer status){
        redisTemplate.opsForValue().set(ShopKey,status);
        return Result.success();
    }

    /**
     * 获取营业状态
     * GET
     * /admin/shop/status
     */
    @GetMapping("/status")
    public Result getShopStatus(){
        Object o = redisTemplate.opsForValue().get(ShopKey);
        return Result.success(o);
    }
}
