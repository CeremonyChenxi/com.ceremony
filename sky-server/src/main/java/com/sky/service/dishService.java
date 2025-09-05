package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;

import java.util.List;

public interface dishService {
    /**
     * 菜品分页查询
     * GET
     * /admin/dish/page
     */
    PageResult pageDish(DishPageQueryDTO dishPageQueryDTO);
    List<Dish> ListDish(Long categoryId);
    /**
     * 批量删除菜品
     * DELETE
     * /admin/dish
     */
    Integer deleteBatchDish(String ids);
    Dish getDishByID(Integer id);
    List<Dish> getDishBycategory_id(Integer id);
    Integer updateBanDish(Integer ID,Integer status);
    Integer addDish(DishDTO dishDTO);
}
