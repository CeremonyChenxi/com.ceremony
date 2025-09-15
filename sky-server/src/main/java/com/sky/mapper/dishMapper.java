package com.sky.mapper;

import com.sky.annotation.Autofill;
import com.sky.annotation.ensureAutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Mapper
public interface dishMapper {
    List<Dish> pageDish(DishPageQueryDTO dishPageQueryDTO);

    Integer deleteBatchDish( List idList);
    Dish getDishByID(Integer id);
    List<DishFlavor> getDishFlavorByID(Integer id);
    /**
     * /admin/dish/list
     */
    List<Dish> getDishBycategory_id(Integer id);

     Integer updateBanDish(Integer id,Integer status);
    /***
     * 新增菜品
     * POST
     * /admin/dish
     */
    @Autofill(type = OperationType.INSERT)
    Integer addDish(@ensureAutoFill Dish  dish);
    Integer addDishFlavor(List list);
    @Autofill(type = OperationType.UPDATE)
    Integer updateDish(Dish dish);
    Integer deleteDishFlavorByDishID( Long dishid);
}
