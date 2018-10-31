package com.legou.common.exception;

/**
 * @Author: clzhang
 * @Date: 2018/10/30 19:00
 */
public class LeGouException extends RuntimeException {

    private String msg;

    public LeGouException(String msg){
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
