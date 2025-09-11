package com.sky.controller.admin.client;

import com.sky.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class shopStatusController {
    @Autowired
    private RedisTemplate redisTemplate;
    private final String ShopKey="SHOP_STATUS";
    @GetMapping("/status")
    public Result getShopStatus(){
        Object o = redisTemplate.opsForValue().get(ShopKey);
        return Result.success(o);
    }
}
