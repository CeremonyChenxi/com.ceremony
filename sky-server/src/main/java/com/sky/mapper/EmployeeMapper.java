package com.sky.mapper;

import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     * @return
     */
    Integer insertEmployee(Employee employee);
    /**
     * 员工分页查询
     * GET
     * /admin/employee/page
     * 通过pageHelper
     */
    List<Employee> getListEmployeeByPage(String name);
}
