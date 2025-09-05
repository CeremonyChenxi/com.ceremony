package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;


public interface categoryService {
    /**
     * 新增分类
     * #344985100
     * POST
     * /admin/category
     */
    Integer addCategory(Category category);
    /**
     * 分类分页查询
     * GET
     * /admin/category/page
     *
     */
    PageResult pageCategory(CategoryPageQueryDTO categoryPageQueryDTO);
    /**
     * 启用、禁用分类
     * POST
     * /admin/category/status/{status}
     */
    Integer updateStatus(Long id,Integer status);
    /**
     * 根据id删除分类
     * #344985101
     * DELETE
     * /admin/category
     */
    Integer deleteCategory( Long id);
    /**
     *   /**
     *      * 修改分类
     *      * #344985097
     *      * PUT
     *      * /admin/category
     *      */
    Integer updateCategory(CategoryDTO categoryDTO);
    List<Category> getCategoryByCategoryID(Integer type);
}
