package com.sky.service;

import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import io.swagger.models.auth.In;

import java.util.List;

public interface setmealService {
    Integer addSetmeal(SetmealDTO setmealDTO);
    PageResult pageSetmeal(DishPageQueryDTO dishPageQueryDTO)   ;
    Integer updateStatus(Integer status,Integer id);
    SetmealDTO getSetmealByid(Integer id);
    Integer updateSetmeal(SetmealDTO setmealDTO);
    Integer deleteSetmeal(String ids);
    List<Setmeal> list( Setmeal Setmeal);
}
