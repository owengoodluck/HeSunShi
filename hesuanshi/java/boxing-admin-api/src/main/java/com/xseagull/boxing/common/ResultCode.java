package com.xseagull.boxing.common;

public enum  ResultCode {
	
    SUCCESS(200, "处理成功"),
    UNAUTHTOKEN(402, "登入失效，请重新登入！"),
    NOT_FOUND(404, "很抱歉你访问的地址不存在！"),
    NOT_SUPPORTED(405, "请求方式错误"),
    INTERNAL_SERVER_ERROR(500, "系统繁忙，请稍后再试！"),
    
    PASSWORD_ERROR(10000, "用户名或密码错误"),
    PARAMETER_ERROR(10001, "参数错误"),
    REGISTER_ERROR(10002, "该手机号已经注册了"),
	VERIFY_CODE_ERROR(10003, "验证码输入有误"),
	SMS_CODE_ERROR(10004, "短信异常"),
	NO_USER_ERROR(10005, "该用户不存在"),
	JSON_RESOLVE_ERROR(10006, "json数据解析错误"),
	PAY_ERROR(10007, "支付相关问题"),
	INVALID_CODE(10008, "invalid code"),
	NOT_AUTH_ERROR(10009, "无效认证"),
	INVALID_RED_ENVELOP(10010, "红包无效"),
	APPLY(10011, "审批中"),
	ACCOUNT_BINDING(10012, "账号绑定"),
	SIGN_ERROR(10013, "签名失败"),
	NOT_TEAM(10014, "无效班级码"),
	YES_SIGN(10015, "今日已签名"),
	NOT_EMPOWER(10016, "该用户没有权限"),
	NOT_NULL(10017,"名称不能为空格"),
	NOT_DELETE(10018, "删除失败"),
	QUANTITY_DEFICIENCY(10019,"金额不足"),
	OUTNUMBER(10020,"你已签到,每天只能签到一次哦"),
	SHAREIMG_ERROR(10021,"图片尺寸必须5:4"),
	DOCTOR_BE_OUT(10022,"医师不在线"),
	NO_CUSTOM_NUMBER(20011,"方案编号不存在");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
