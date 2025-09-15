package com.sky.service.impl.client;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.mapper.dishMapper;
import com.sky.mapper.setmealMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpI implements ShoppingCartService {
    @Autowired
    private com.sky.mapper.ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private com.sky.mapper.dishMapper dishMapper;
    @Autowired
    private com.sky.mapper.setmealMapper setmealMapper;

    @Override
    public Integer clear() {
        Long currentId = BaseContext.getCurrentId();
        return shoppingCartMapper.clear(currentId);
    }

    @Override
    public List<ShoppingCart> list() {
        Long currentId = BaseContext.getCurrentId();
        List<ShoppingCart> list=shoppingCartMapper.list(currentId);
        return list;
    }

    @Override
    public Integer deleteGoods(ShoppingCartDTO shoppingCartDTO) {
        //获取菜品的数量,如果返回的>1,则update number-1
        //返回<=1 则delete
        Long currentId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(currentId);
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        ShoppingCart shoppingCartByID = shoppingCartMapper.getShoppingCartByID(shoppingCart);
        Integer result=-1;
        if(shoppingCartByID.getNumber()>1){
            result=shoppingCartMapper.decline(shoppingCartByID);
        }else{
            result=shoppingCartMapper.delete(shoppingCartByID);
        }
        return result;
    }

    @Override
    public Integer addCart(ShoppingCartDTO shoppingCartDTO) {
//
//        private Long dishId;
//        private Long setmealId;
//        private String dishFlavor;
        //如果表中有该用户的相关菜品,就直接update number ,
        //如果没有则添加
        //补全shoppingcart的信息
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartDTO);
        ShoppingCart shoppingCartByID = shoppingCartMapper.getShoppingCartByID(shoppingCart);
        if (shoppingCartByID != null) {
            Integer integer=-1;
            //说明当前已经有该菜品数据,
            if(shoppingCartByID.getDishFlavor()!=null&&!(shoppingCartByID.getDishFlavor().equals(shoppingCartDTO.getDishFlavor()))){
             integer = shoppingCartMapper.addShoppingCart(shoppingCart);
            }else{
                 integer = shoppingCartMapper.updateShopping(shoppingCartByID);
            }
            return integer;
        } else {
            //没有则添加
            shoppingCart.setCreateTime(LocalDateTime.now());
            Integer integer = shoppingCartMapper.addShoppingCart(shoppingCart);
            return integer;
        }

    }

    private ShoppingCart getShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        Long currentId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(currentId);
        //判断是菜品还是套餐
        if (shoppingCartDTO.getDishId() != null) {
            Dish dishByID = dishMapper.getDishByID(shoppingCartDTO.getDishId().intValue());
            shoppingCart.setName(dishByID.getName());
            shoppingCart.setAmount(dishByID.getPrice());
            shoppingCart.setImage(dishByID.getImage());
        }
        if (shoppingCartDTO.getSetmealId() != null) {
            Setmeal setmealByid = setmealMapper.getSetmealByid(shoppingCartDTO.getSetmealId().intValue());
            shoppingCart.setName(setmealByid.getName());
            shoppingCart.setImage(setmealByid.getImage());
            shoppingCart.setAmount(setmealByid.getPrice());
        }
        return shoppingCart;
    }
}
