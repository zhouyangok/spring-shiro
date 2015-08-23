package com.wangzhixuan.code;


public interface ResultResponse {
    static final int FAILURE = Result.FAILURE;
    
    int getCode();

    String getMessage();
}
