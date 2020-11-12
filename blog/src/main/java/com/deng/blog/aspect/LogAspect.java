package com.deng.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * created by deng on 2020-11-12
 **/
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.deng.blog.web.*.*(..))")
    public void  log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attr.getRequest();
        String url = req.getRequestURL().toString();
        String ip =req.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object [] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("RequestLog : {}", requestLog);
    }

    @After("log()")
    public void doAfter () {
        logger.info("----------------doAfter------------------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
       logger.info("Result : {}", result);
    }

    private  class RequestLog  {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
