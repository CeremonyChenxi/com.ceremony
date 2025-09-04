package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import org.springframework.stereotype.Service;


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
}
