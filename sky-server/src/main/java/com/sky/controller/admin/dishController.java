package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.dishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/dish")
@RestController
public class dishController {
    @Autowired
    private dishService dishService;
    /**
     * 菜品分页查询
     * GET
     * /admin/dish/page
     * 菜品相关接口
     */
    @GetMapping("/page")
    public Result<PageResult> pageDish(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishService.pageDish(dishPageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 根据分类id查询菜品
     * GET
     * /admin/dish/list
     */
    /**
     * 批量删除菜品
     * DELETE
     * /admin/dish
     */
    @DeleteMapping
    public Result deleteBatchDish(String ids){
        Integer integer = dishService.deleteBatchDish(ids);
        return Result.success(integer);
    }
    /**
     * 根据id查询菜品
     * GET
     * /admin/dish/{id}
     */
    @GetMapping("{id}")
    public Result getDishByID(@PathVariable  Integer id){
       Dish dish= dishService.getDishByID(id);
       return Result.success(dish);
    }
    /**
     * 根据分类id查询菜品
     * GET
     * /admin/dish/list
     */
    @GetMapping("/list")
    public Result getDishBycategory_id(Integer category_id){
        List<Dish> dishBycategory_id = dishService.getDishBycategory_id(category_id);
        return Result.success(dishBycategory_id);
    }
    /**
     * 菜品起售、停售
     * POST
     * /admin/dish/status/{status}
     */
    @PostMapping("/status/{status}")
    public Result updateBanDish(Integer id,@PathVariable Integer status ){
        Integer integer = dishService.updateBanDish(id, status);
        return Result.success(integer);
    }
    /**
     * 新增菜品
     * POST
     * /admin/dish
     */
    //需要把private List<DishFlavor> flavors = new ArrayList<>();
    //给赋值给一个新的DishFlavor
    //相当于 ,插入两条数据
    @PostMapping
    public Result addDish(@RequestBody  DishDTO dishDTO){
        Integer integer = dishService.addDish(dishDTO);
        return Result.success(integer);
    }
}
