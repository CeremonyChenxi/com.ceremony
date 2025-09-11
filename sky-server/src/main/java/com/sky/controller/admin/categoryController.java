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

import java.util.List;

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
    /**
     * 启用、禁用分类
     * POST
     * /admin/category/status/{status}
     */
    @PostMapping("/status/{status}")
    public Result<Integer> updateStatus(Long id, @PathVariable  Integer status){
        Integer integer = categoryService.updateStatus(id, status);
        return Result.success(integer);
    }
    /**
     * 根据id删除分类
     * #344985101
     * DELETE
     * /admin/category
     */
    @DeleteMapping()
    public Result<Integer> deleteCategory(Long id){
        Integer integer = categoryService.deleteCategory(id);
        return Result.success(integer);
    }
    /**
     * 修改分类
     * #344985097
     * PUT
     * /admin/category
     */
    @PutMapping
    public Result<Integer > updateCategory(@RequestBody  CategoryDTO categoryDTO){
        Integer integer = categoryService.updateCategory(categoryDTO);
        return Result.success(integer);

    }
    /**
     * 根据类型查询分类
     * GET
     * /admin/category/list
     */
    @GetMapping("/list")
    public Result<List<Category>> getCategoryByCategoryID(Integer type){
        List<Category> categoryByCategoryID = categoryService.getCategoryByCategoryID(type);
        return Result.success(categoryByCategoryID);
    }
    /**
     * 根据分类id查询菜品
     * GET
     * /admin/dish/list
     */
}
