package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.categoryService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/category")
@RestController
public class categoryController {
    @Autowired
    private categoryService categoryService;
    /**
     * 新增分类
     * #344985100
     * POST
     * /admin/category
     */
    @PostMapping()
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        Integer integer = categoryService.addCategory(category);
        return Result.success(integer);
    }
    /**
     * 分类分页查询
     * GET
     * /admin/category/page
     *
     */
    @GetMapping("/page")
    public Result<PageResult> pageCategory(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult = categoryService.pageCategory(categoryPageQueryDTO);
        return  Result.success(pageResult);
    }
}
