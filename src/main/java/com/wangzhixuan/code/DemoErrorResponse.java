package com.wangzhixuan.code;



/**
 * demo 业务 错误码
 */
public enum DemoErrorResponse implements ResultResponse {
    CHECK_ERROR(2000, "demo业务校验失败"),
    ALERT_POINT_ERROR(2001, "修改账户可用金额失败"),
    APPEND_POINT_DETAIL_ERROR(2002, "添加资金详情失败！");

    private int errorCode;
    private String errorMsg;

    DemoErrorResponse(int errorCode, String errorMsg) {
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
