package com.sky.service.impl;

import com.sky.entity.Setmeal;
import com.sky.mapper.setmealMapper;
import com.sky.service.userSetmealService;
import com.sky.vo.DishItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userSetmealServiceImpl implements userSetmealService {
    @Autowired
    private setmealMapper setmealMapper;

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    public List<Setmeal> list(Setmeal setmeal) {
        System.out.println("需要查询的套餐setmeal"+setmeal);
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }
}
