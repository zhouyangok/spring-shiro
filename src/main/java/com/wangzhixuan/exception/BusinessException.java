package com.wangzhixuan.exception;

import com.wangzhixuan.code.ResultResponse;

/**
 * 业务异常
 *
 * @author grom
 */
public class BusinessException extends RuntimeException {
    private ResultResponse response;

    public BusinessException(ResultResponse response) {
        super();
        this.response = response;
    }

    public ResultResponse getResponse() {
        return response;
    }

    public int getErrorCode() {
        return response != null ? response.getCode() : ResultResponse.FAILURE;
    }

    public String getErrorMsg() {
        return response != null ? response.getMessage() : "inner exception!";
    }
}
