package com.hjyp.miaowenserver.interfaces.common;

import lombok.Data;

/**
 * 统一的返回类
 */
@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    private static final String successMsg = "调用接口成功";

    public Result() {}

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.code = ResultCode.SUCCESS_CODE.getCode();
        this.msg = successMsg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result<T> success(T data) {
        return new Result<>(data);
    }

    public Result<T> error(String code, String msg) {
        return new Result<>(code, msg);
    }

    public static void checkResult(ResultCode resultCode) {

    }


}
