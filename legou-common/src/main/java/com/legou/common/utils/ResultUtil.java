package com.legou.common.utils;


import com.legou.common.pojo.Result;

public class ResultUtil<T> {

    private Result<T> result;


    public ResultUtil(){
        result = new Result<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage("success");
    }

    public Result<T> setData(T t){
        this.result.setCode(200);
        this.result.setResult(t);
        return this.result;
    }


    public Result<T> setData(T t,String message){
        this.result.setCode(200);
        this.result.setResult(t);
        this.result.setMessage(message);
        return this.result;
    }

    public Result<T> setErrorMsg(String message){
        this.result.setCode(500);
        this.result.setMessage(message);
        this.result.setSuccess(false);
        return this.result;
    }

    public Result<T> setErrorMsg(Integer code,String message){
        this.result.setCode(code);
        this.result.setMessage(message);
        this.result.setSuccess(false);
        return this.result;
    }


}
