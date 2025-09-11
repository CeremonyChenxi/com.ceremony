package com.sky.mapper;

import com.sky.annotation.Autofill;
import com.sky.annotation.ensureAutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface setmealMapper {
    @Autofill(type = OperationType.INSERT)
     Integer addSetmeal(@ensureAutoFill Setmeal setmeal);
    List<Setmeal> pageSetmeal(DishPageQueryDTO dishPageQueryDTO)   ;
    Integer updateStatus(Integer status,Integer id);
    Setmeal getSetmealByid(Integer id);
    @Autofill(type=OperationType.UPDATE)
    Integer updateSetmeal(@ensureAutoFill Setmeal setmeal);
    Integer deleteSetmeal(List ids);
}
