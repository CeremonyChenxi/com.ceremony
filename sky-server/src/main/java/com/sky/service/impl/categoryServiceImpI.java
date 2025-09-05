package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.categoryMapper;
import com.sky.result.PageResult;
import com.sky.service.categoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class categoryServiceImpI implements categoryService {
    @Resource
    private categoryMapper categoryMapper;
    /**
     * 新增分类
     * #344985100
     * POST
     * /admin/category
     * @param Category
     * @return
     */
    @Override
    public Integer addCategory(Category category) {
        Long currentId = BaseContext.getCurrentId();
        category.setCreateUser(currentId);
        category.setUpdateUser(currentId);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Integer integer = categoryMapper.addCategory(category);
        return integer;
    }
    /**
     * 分类分页查询
     * GET
     * /admin/category/page
     *
     */
    @Override
    public PageResult pageCategory(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        List<Category> categories = categoryMapper.pageCategory(categoryPageQueryDTO);
        PageInfo<Category> categoryPageInfo = new PageInfo<>(categories);
        PageResult pageResult = new PageResult(categoryPageInfo.getTotal(), categories);
        return pageResult;
    }
    /**
     * 启用、禁用分类
     * POST
     * /admin/category/status/{status}
     */
    @Override
    public Integer updateStatus(Long id, Integer status) {
        Integer result =categoryMapper.updateStatus(id,status);
        return result;
    }

    /**
     * 根据id删除分类
     * #344985101
     * DELETE
     * /admin/category
     * @param id
     * @return
     */
    @Override
    public Integer deleteCategory(Long id) {
        Integer integer = categoryMapper.deleteCategory(id);
        return integer;
    }
    /**
     *   /**
     *      * 修改分类
     *      * #344985097
     *      * PUT
     *      * /admin/category
     *      */
     public Integer updateCategory(CategoryDTO categoryDTO){
         Integer r = categoryMapper.updateCategory(categoryDTO);
         return r;
     }

    @Override
    public List<Category> getCategoryByCategoryID(Integer type) {
        List<Category> categoryByCategoryID = categoryMapper.getCategoryByCategoryID(type);
        return categoryByCategoryID;
    }
}
