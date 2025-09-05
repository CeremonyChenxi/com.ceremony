package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

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
    /**
     *启用、禁用员工账号
     * #344985104
     * POST
     * /admin/employee/status/{status}
     */
    Integer banEmployee(Integer id,Integer status);
    EmployeeDTO getEmployeeByID(Integer id);
    /**
     * 编辑员工信息
     * PUT
     * /admin/employee
     */
    Integer editEmployee(EmployeeDTO employeeDTO);
    /**
     * 修改密码
     * #344985103
     * PUT
     * /admin/employee/editPassword
     */
    Integer editPassword(PasswordEditDTO editDTO);
}
