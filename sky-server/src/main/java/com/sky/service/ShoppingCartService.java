package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    Integer addCart(ShoppingCartDTO shoppingCartDTO);
    List<ShoppingCart > list();
    Integer deleteGoods(ShoppingCartDTO shoppingCartDTO);
    Integer clear();
}
