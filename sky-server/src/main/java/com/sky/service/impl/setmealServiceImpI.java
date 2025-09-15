package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.setmealDishMapper;
import com.sky.mapper.setmealMapper;
import com.sky.result.PageResult;
import com.sky.service.setmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class setmealServiceImpI implements setmealService {
    @Autowired
    private setmealMapper setmealMapper;
    @Autowired
    private setmealDishMapper setmealDishMaper;

    @Override
    public Integer addSetmeal(SetmealDTO setmealDTO) {
        System.out.println("setmealDto"+setmealDTO);
        //1 插入setmeal
        //2 插入setmeal_dish
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        Integer addSetmeal = setmealMapper.addSetmeal(setmeal);
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmeal.getId());
        }
        System.out.println(setmealDishes);
        Integer addSetmealDish = setmealDishMaper.addSetmealDish(setmealDishes);
        return addSetmealDish+addSetmeal;
    }

    public PageResult pageSetmeal(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        List<Setmeal> setmeals = setmealMapper.pageSetmeal(dishPageQueryDTO);
        PageInfo<Setmeal> setmealPageInfo = new PageInfo<>(setmeals);
        List<Setmeal> list = setmealPageInfo.getList();
        long total = setmealPageInfo.getTotal();
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(list);
        return pageResult;
    }
    public Integer updateStatus(Integer status,Integer id){
        Integer result=setmealMapper.updateStatus(status, id);
        return result;
    }

    @Override
    public SetmealDTO getSetmealByid(Integer id) {
        SetmealDTO setmealDTO = new SetmealDTO();
        Setmeal setmeal=setmealMapper.getSetmealByid(id);
        BeanUtils.copyProperties(setmeal,setmealDTO);
        //根据setmeal_id来获得setmeal_dish
        List<SetmealDish> sermealDishBysetmeal_id = setmealDishMaper.getSermealDishBysetmeal_id(setmeal.getId());

        setmealDTO.setSetmealDishes(sermealDishBysetmeal_id);
        return setmealDTO;
    }

    @Override
    public Integer updateSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        Integer updateSetmeal = setmealMapper.updateSetmeal(setmeal);
        Long id = setmeal.getId();
        Integer deleteSetmealDishByID = setmealDishMaper.deleteSetmealDishByID(id);
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(id);
        }
        Integer addSetmealDish = setmealDishMaper.addSetmealDish(setmealDishes);
        return  updateSetmeal+deleteSetmealDishByID+addSetmealDish;
    }

    @Override
    public Integer deleteSetmeal(String ids) {
        String[] split = ids.split(",");
        List<? extends Serializable> serializables = Lists.asList(0, split);
        Integer integer = setmealMapper.deleteSetmeal(serializables);
        return  integer;
    }

    @Override
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }
}
