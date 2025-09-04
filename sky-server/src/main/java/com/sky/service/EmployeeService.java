package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import io.swagger.models.auth.In;

import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    /**
     * 添加员工
     * admin/employee
     */
    Integer insertEmployee(EmployeeDTO employeeDTO);
    /**
     * 员工分页查询
     * GET
     * /admin/employee/page
     */
    PageResult pageEmployee(Integer page, Integer pageSize, String name);
}
