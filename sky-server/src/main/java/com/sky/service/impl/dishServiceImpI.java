package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;

import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.dishMapper;
import com.sky.result.PageResult;
import com.sky.service.dishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
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


//    public List<Dish> ListDish(Long categoryId) {
//        List<Dish> dishes = dishMapper.ListDish(categoryId);
//        return dishes;
//    }

    @Override
    public Integer deleteBatchDish(String ids) {
        String[] split = ids.split(",");
        List<? extends Serializable> idList = Lists.asList(0, split);
        Integer integer = dishMapper.deleteBatchDish(idList);
        return integer;
    }
    public DishDTO getDishByID(Integer id){
        Dish dishByID = dishMapper.getDishByID(id);
        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dishByID,dishDTO);
        List<DishFlavor> dishFlavorByID = dishMapper.getDishFlavorByID(id);
        dishDTO.setFlavors(dishFlavorByID);
        return dishDTO;
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
        Integer addDish = dishMapper.addDish(dish);
        List<DishFlavor> flavors = dishDTO.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dish.getId());
        }
        System.out.println("reading "+flavors);
        Integer addDishFlavor = dishMapper.addDishFlavor(dishDTO.getFlavors());
        return addDish+addDishFlavor;
    }

    @Override
    public Integer updateDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        Integer updateDish = dishMapper.updateDish(dish);
        //需要把之前的dish_flavor相关的删掉,再插入
        Integer deleteDishFlavorByDishID = dishMapper.deleteDishFlavorByDishID(dishDTO.getId());
        for (DishFlavor flavor : dishDTO.getFlavors()) {
            flavor.setDishId(dishDTO.getId());
        }
        Integer addDishFlavor = dishMapper.addDishFlavor(dishDTO.getFlavors());
        return updateDish+deleteDishFlavorByDishID+addDishFlavor;
    }
    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    public List<DishVO> listWithFlavor(Dish dish) {

        List<Dish> dishList = dishMapper.getDishBycategory_id(dish.getCategoryId().intValue());

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishMapper.getDishFlavorByID(d.getId().intValue());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }
}
