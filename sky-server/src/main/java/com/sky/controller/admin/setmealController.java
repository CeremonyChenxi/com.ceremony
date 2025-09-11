package com.sky.controller.admin;

import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.setmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/setmeal")
public class setmealController {
    @Autowired
    private setmealService setmealService;

    /**
     * 新增套餐
     * POST
     */
    @PostMapping
    public Result addSetmeal(@RequestBody SetmealDTO setmealDTO) {
        Integer integer = setmealService.addSetmeal(setmealDTO);
        return Result.success(integer);
    }

    /**
     * 分页查询
     * GET
     * /admin/setmeal/page
     * http://localhost/api/setmeal/page?page=1&pageSize=10&status=
     */
//
    @GetMapping("/page")
    public Result pageSetmeal(DishPageQueryDTO dishPageQueryDTO) {
        PageResult r = setmealService.pageSetmeal(dishPageQueryDTO);
        return Result.success(r);


    }

    /**
     * 套餐起售、停售
     * POST
     * /admin/setmeal/status/{status}
     */
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, Integer id) {
        Integer integer = setmealService.updateStatus(status, id);
        return Result.success(integer);
    }

    /**
     * 根据id查询套餐
     * GET
     * /admin/setmeal/{id}
     */
    @GetMapping("/{id}")
    public Result<SetmealDTO> getSetmealByid(@PathVariable Integer id) {
        SetmealDTO setmeal = setmealService.getSetmealByid(id);
        return Result.success(setmeal);
    }

    /**
     * 修改套餐
     * PUT
     * /admin/setmeal
     */
    @PutMapping
    public Result updateSetmeal(@RequestBody SetmealDTO setmealDTO) {
        Integer integer = setmealService.updateSetmeal(setmealDTO);
        return Result.success(integer);
    }

    /**
     * 批量删除套餐
     * DELETE
     * /admin/setmeal
     */
    @DeleteMapping
    public Result deleteSetmeal(String ids) {
        Integer integer = setmealService.deleteSetmeal(ids);
        return Result.success(integer);
    }
}
