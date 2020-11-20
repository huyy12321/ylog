package com.hyy.ylog.common.note;

import com.hyy.ylog.common.enums.LogType;

import java.lang.annotation.*;

/**
 * @author hyy
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YLogNote {
    LogType type() default LogType.SELECT;
}
