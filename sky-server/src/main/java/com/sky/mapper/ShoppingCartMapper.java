package com.sky.mapper;

import com.sky.annotation.Autofill;
import com.sky.annotation.ensureAutoFill;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    ShoppingCart getShoppingCartByID( ShoppingCart shoppingCart);

    Integer addShoppingCart( ShoppingCart shoppingCart);
    @Update("update shopping_cart set number=number+1 where id=#{id}")
    Integer updateShopping(ShoppingCart shoppingCart);
    @Update("update shopping_cart set number=number-1 where id=#{id}")
    Integer decline(ShoppingCart shoppingCart);

    @Select("select * from shopping_cart where user_id=#{id}")
    List<ShoppingCart> list(Long id);


    Integer delete(ShoppingCart shoppingCart);
    @Delete("delete from shopping_cart where user_id=#{userID}")
    Integer clear(Long userID);
}
