package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordEditFailedException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.apache.poi.xwpf.usermodel.TOC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 添加员工
     * @param employeeDTO
     * @return
     */
    @Override
    public Integer insertEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //设置默认密码,通过md5
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setStatus(StatusConstant.ENABLE);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // : 2025/9/3 后面解决创建人的问题
        //使用ThreadLocal
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());
        System.out.println(employee);
        Integer affected = employeeMapper.insertEmployee(employee);
        return affected;
    }

    @Override
    public PageResult pageEmployee(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page,pageSize);
        List<Employee> listEmployeeByPage = employeeMapper.getListEmployeeByPage(name);
        PageInfo<Employee> employeePageInfo = new PageInfo<>(listEmployeeByPage);
        PageResult pageResult = new PageResult(employeePageInfo.getTotal(), employeePageInfo.getList());
        return pageResult;
    }

    @Override
    public Integer banEmployee(Integer id,Integer status) {
        Integer integer = employeeMapper.banEmployee(id,status);
        return integer;
    }

    @Override
    public EmployeeDTO getEmployeeByID(Integer id) {
        EmployeeDTO employeeByID = employeeMapper.getEmployeeByID(id);
        return employeeByID;
    }
    /**
     * 编辑员工信息
     * PUT
     * /admin/employee
     */
    @Override
    public Integer editEmployee(EmployeeDTO employeeDTO) {
        Integer employee = employeeMapper.editEmployee(employeeDTO);
        return employee;
    }

    /**
     *
     * @param editDTO
     * @return
     * 修改密码
     * #344985103
     * PUT
     * /admin/employee/editPassword
     */
    @Override
    public Integer editPassword(PasswordEditDTO editDTO) {
        Long currentId = BaseContext.getCurrentId();
        String passwordById = employeeMapper.getPasswordById(currentId);
        String oldPassword = editDTO.getOldPassword();
        String oldPasswordMD5 = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if(passwordById.equals(oldPasswordMD5)){
            throw new PasswordEditFailedException("原密码错误");

        }else {
            employeeMapper.editPassword(currentId,editDTO.getNewPassword());
            return  1;
        }
    }
}
