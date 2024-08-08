package com.hjyp.miaowenserver.interfaces.common;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS_CODE("00000");

    private final String code;

    ResultCode(String code) {
        this.code = code;
    }

    public static enum ArticleResultCode {

    }

}
