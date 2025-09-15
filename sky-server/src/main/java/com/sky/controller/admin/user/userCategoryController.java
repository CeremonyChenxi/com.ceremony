package com.sky.controller.admin.user;

import com.sky.entity.Category;
import com.sky.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/category")
public class userCategoryController {
    @Autowired
    private com.sky.service.categoryService categoryService;

    /**
     * 查询分类
     *
     * @param type
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.getCategoryByCategoryID(type);
        return Result.success(list);
    }
}
