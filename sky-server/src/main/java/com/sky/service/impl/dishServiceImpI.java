package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;

import com.sky.entity.Dish;
import com.sky.mapper.dishMapper;
import com.sky.result.PageResult;
import com.sky.service.dishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class dishServiceImpI implements dishService {
    @Autowired
    private dishMapper dishMapper;
    @Override
    public PageResult pageDish(DishPageQueryDTO dishPageQueryDTO) {
        Page<Object> objects = PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        List<Dish> dishes = dishMapper.pageDish(dishPageQueryDTO);
        PageInfo<Dish> dishPageInfo = new PageInfo<>(dishes);
        PageResult pageResult = new PageResult();
        pageResult.setRecords(dishes);
        pageResult.setTotal(dishPageInfo.getTotal());
        return pageResult;
    }

    @Override
    public List<Dish> ListDish(Long categoryId) {
        List<Dish> dishes = dishMapper.ListDish(categoryId);
        return dishes;
    }

    @Override
    public Integer deleteBatchDish(String ids) {
        String[] split = ids.split(",");
        List<? extends Serializable> idList = Lists.asList(0, split);
        Integer integer = dishMapper.deleteBatchDish(idList);
        return integer;
    }
    public Dish getDishByID(Integer id){
        Dish dishByID = dishMapper.getDishByID(id);
        return dishByID;
    }

    @Override
    public List<Dish> getDishBycategory_id(Integer id) {
        List<Dish> r = dishMapper.getDishBycategory_id(id);
        return r;
    }
    public Integer updateBanDish(Integer ID,Integer status){
        Integer integer = dishMapper.updateBanDish(ID, status);
        return integer;
    }
    public Integer addDish(DishDTO dishDTO){
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        Integer integer = dishMapper.addDish(dish);
        return null;
    }
}
