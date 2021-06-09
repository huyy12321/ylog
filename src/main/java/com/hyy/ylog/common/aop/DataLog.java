package com.hyy.ylog.common.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyy.ylog.entity.Ylog;
import com.hyy.ylog.common.note.YLogNote;
import com.hyy.ylog.service.YlogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyy
 */
@Aspect
@Component
public class DataLog {
    @Resource
    private YlogService ylogService;
    private final Logger log = LoggerFactory.getLogger(DataLog.class);

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.hyy.ylog.common.note.YLogNote)")
    public void addDateLog() {
    }


    @Around("addDateLog()")
    public Object doAround(ProceedingJoinPoint point) {
        Object proceed = null;
        try{
            YLogNote annotationLog = getAnnotationLog(point);
            if(annotationLog != null) {
                Ylog ylog = new Ylog();
                long start = System.currentTimeMillis();
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                assert requestAttributes != null;
                HttpServletRequest request = requestAttributes.getRequest();
                proceed = point.proceed();
                ylog.setControllerName(point.toLongString());
                ylog.setIp(DataLog.getIPAddress(request));
                ylog.setPath(request.getRequestURI());
                ylog.setData(DataLog.getAllRequestParam(request));
                long end = System.currentTimeMillis();
                ylog.setUseTime(end-start);
                ylog.setCreateTime(new Date());
                ylog.setType(request.getMethod());
//                ylogService.save(ylog);
                System.out.println(ylog);
            } else{
                proceed = point.proceed();
            }
        } catch (Throwable e) {
            log.error("日志处理出现异常：{}",e.toString());
        }
        return proceed;
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private YLogNote getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(YLogNote.class);
        }
        return null;
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static String getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
            }
        }
        return JSONObject.toJSON(res).toString();
    }
}
