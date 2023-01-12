package com.example.filesmanegar.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAdvive {
    Logger log= LoggerFactory.getLogger(LogginAdvive.class);
    @Pointcut(value = "execution(* com.example.filesmanegar.*.*.*(..))")
    public void myPointcut(){

    }
    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper maper=new ObjectMapper();
        String methodName=pjp.getSignature().getName();
        String className=pjp.getTarget().getClass().toString();
        Object[] array=pjp.getArgs();
        log.info("method invoke "+className+": "+methodName+"()"+"argument:"+maper.writeValueAsString(array));
        Object object=pjp.proceed();
        log.info(className+": "+methodName+"()"+"responce:"+maper.writeValueAsString(object));
        return object;
    }
}
