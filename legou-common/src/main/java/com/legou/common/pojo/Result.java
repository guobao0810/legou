package com.legou.common.pojo;

import java.io.Serializable;

/**
 * @Author: clzhang
 * @Date: 2018/9/12 19:29
 * 前后端交互的数据格式定义
 */
public class Result<T> implements Serializable {

    /**
     * 成功标识
     */
    private boolean success;

    /**
     * 失败消息
     */
    private String message;

    /**
     * 返回code码
     */
    private Integer code;

    /**
     * 当前系统的时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 响应数据
     */
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
