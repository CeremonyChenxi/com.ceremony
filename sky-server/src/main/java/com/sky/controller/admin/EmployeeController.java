package com.sky.controller.admin;

import com.github.pagehelper.PageHelper;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.service.impl.EmployeeServiceImpl;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     * POST
     * /admin/employee
     */
    @PostMapping
    public Result<String> insertEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Integer integer = employeeService.insertEmployee(employeeDTO);
        return Result.success();
    }

    /**
     * 员工分页查询
     * GET
     * /admin/employee/page
     */
    @GetMapping("/page")
    public Result<PageResult> getListEmployeeByPage(@RequestParam("page") Integer page,
                                                        @RequestParam("pageSize") Integer pageSize,
                                                        String name){
        PageResult pageResult = employeeService.pageEmployee(page, pageSize, name);
        return Result.success(pageResult);
    }
    /**
     * 启用、禁用员工账号
     * #344985104
     * POST
     * /admin/employee/status/{status}
     */
    @PostMapping("/status/{status}")
    public Result banEmployee(Integer id,@PathVariable("status") Integer status){
        Integer integer = employeeService.banEmployee(id,status);
        return Result.success();
    }
    /**
     * 根据id查询员工
     * GET
     * /admin/employee/{id}
     */
    @GetMapping("/{id}")
    public Result<EmployeeDTO> getEmployeeByID( @PathVariable  Integer id){
        EmployeeDTO employeeByID = employeeService.getEmployeeByID(id);
        return Result.success(employeeByID);
    }
    /**
     * 编辑员工信息
     * PUT
     * /admin/employee
     */
    @PutMapping
    public Result<Integer> editEmployee(@RequestBody  EmployeeDTO employeeDTO){
        Integer employee = employeeService.editEmployee(employeeDTO);
        return Result.success(employee);
    }
    /**
     * 修改密码
     * #344985103
     * PUT
     * /admin/employee/editPassword
     */
    @PutMapping("/editPassword")
    public Result<Integer> editPassword(@RequestBody PasswordEditDTO passwordEditDTO){
        Integer integer = employeeService.editPassword(passwordEditDTO);
        if(integer==1){
            return Result.success(1);
        }else {
            return Result.error("原密码错误");
        }
    }
}
