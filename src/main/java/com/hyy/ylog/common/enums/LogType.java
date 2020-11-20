package com.hyy.ylog.common.enums;

/**
 * @author hyy
 */

public enum LogType {
    /**
     *日志类型 增删改查
     */
    ADD("add"),DELETE("delete"),UPDATE("update"),SELECT("select");
    private String name;

    LogType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
