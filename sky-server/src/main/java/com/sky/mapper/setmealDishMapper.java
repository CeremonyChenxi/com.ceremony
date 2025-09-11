package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface setmealDishMapper {
    Integer addSetmealDish(List<SetmealDish> dishList);
    List<SetmealDish> getSermealDishBysetmeal_id(Long id);
    Integer deleteSetmealDishByID(Long id);

}
