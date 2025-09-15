package com.sky.controller.admin.user;

import com.sky.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/shop")
public class userShopStatus {
    @Autowired
    private RedisTemplate redisTemplate;
    private final String ShopKey="SHOP_STATUS";
    /**
     * 获取营业状态
     * GET
     * /user/shop/status
     */
    @GetMapping("/status")
    public Result getShopStatus(){
        Object o = redisTemplate.opsForValue().get(ShopKey);
        return Result.success(o);
    }
}
