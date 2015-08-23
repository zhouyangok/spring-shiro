package com.wangzhixuan.code;



/**
 * 命令响应码
 */
public enum BaseResponseEnum implements ResultResponse {
    SUCCESS(1, "success"),
    CHECK_PARAM_ERROR(-10, "校验失败"),
    NORMAL_FORBID_WITH_REVERSE_STATUS(-11, "禁止普通命令再执行，已接收到冲正命令。"),
    NORMAL_ALREADY_SUCCESS(10, "该命令已成功，不用再次执行。"),
    REVERSE_ALREADY_SUCCESS(11, "冲正已成功，不用再次执行。"),
    REVERSE_SUCCESS_WITH_NORMAL_NOT_SUCCESS(12, "冲正已成功，普通命令未执行成功。"),
    INNER_ERROR(-1, "内部错误");


    private int errorCode;
    private String errorMsg;

    BaseResponseEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return this.errorMsg + "<" + this.errorCode + ">";
    }
}
