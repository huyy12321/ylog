package com.hyy.ylog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hyy
 * @since 2020-10-10
 */
public class Ylog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Date createTime;

    /**
     * 访问的controller名字
     */
    private String controllerName;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 访问的ip
     */
    private String ip;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 请求的参数
     */
    private String data;

    /**
     * 返回的信息
     */
    private String msg;

    /**
     * 返回的响应码
     */
    private String code;

    /**
     * 接口耗时，单位毫秒
     */
    private Long useTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    @Override
    public String toString() {
        return "Ylog{" +
                "id=" + id +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", controllerName='" + controllerName + '\'' +
                ", path='" + path + '\'' +
                ", ip='" + ip + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", useTime='" + useTime + '\'' +
                '}';
    }
}
