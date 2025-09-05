package com.sky.mapper;

import com.sky.annotation.Autofill;
import com.sky.annotation.ensureAutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface categoryMapper {
    @Autofill(type = OperationType.INSERT)
    Integer addCategory(@ensureAutoFill Category category);
    List<Category> pageCategory (CategoryPageQueryDTO categoryPageQueryDTO);
    Integer updateStatus(Long id,Integer status);
    Integer deleteCategory(Long id);
    Integer updateCategory(CategoryDTO categoryDTO);
    List<Category> getCategoryByCategoryID(Integer type);
}
