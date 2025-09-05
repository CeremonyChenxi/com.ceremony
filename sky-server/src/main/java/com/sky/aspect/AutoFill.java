package com.sky.aspect;
import com.sky.annotation.Autofill;
import com.sky.annotation.ensureAutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;

@Aspect
@Component
public class AutoFill {

    @Pointcut("execution(* com.sky.mapper.*.*(..))&& @annotation(com.sky.annotation.Autofill)")
    public void JoinCut() {

    }

    @Before("JoinCut()")
    public void ensureAutoFill(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Autofill annotation = method.getAnnotation(Autofill.class);
        OperationType type = annotation.type();
        //实际参数
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();
        //设置的值
        Long currentId = BaseContext.getCurrentId();
        LocalDateTime now = LocalDateTime.now();
        for(int i=0;i<parameters.length;i++) {
            //要获取形参上是否有@ensureAutoFill
            boolean annotationPresent = parameters[i].isAnnotationPresent(ensureAutoFill.class);
            //真实赋值的时候是真正的实参,也就是切入点的args[]
            Object parameter = args[i];
            try {
                if (annotationPresent && OperationType.INSERT == type) {
                    //该方法是insert
                    Method setCreateTime = parameter.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                    Method setCreateUser = parameter.getClass().getDeclaredMethod("setCreateUser", Long.class);
                    Method setUpdateTime = parameter.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                    Method setUpdateUser = parameter.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                    setUpdateUser.invoke(parameter, currentId);
                    setCreateUser.invoke(parameter, currentId);
                    setUpdateTime.invoke(parameter, now);
                    setCreateTime.invoke(parameter, now);
                } else if (annotationPresent && OperationType.UPDATE == type) {
                    Method setUpdateTime = parameter.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                    Method setUpdateUser = parameter.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                    setUpdateUser.invoke(parameter, currentId);
                    setUpdateTime.invoke(parameter, now);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("autofill after " + parameter);

        }

    }
}
