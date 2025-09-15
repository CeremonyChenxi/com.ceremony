package com.sky.mapper;

import com.sky.annotation.Autofill;
import com.sky.annotation.ensureAutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    /**
     * 动态条件查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询菜品选项
     * @param setmealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);
}
