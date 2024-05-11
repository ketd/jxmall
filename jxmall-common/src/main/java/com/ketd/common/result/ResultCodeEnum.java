package com.ketd.common.result;

/**
 * 统一返回结果状态信息类
 *
 */
public enum ResultCodeEnum {




    /*--------------------通用状态码------------------------------------*/
    SUCCESS(200, "操作成功"),
    ERROR(201, "操作失败"),
    EMPTY(202, "不能为空"),
    NO_PERMISSIONS(203, "无权限操作"),
    INVALID_VERIFICATION_CODE(206, "无效验证码"),
    NOT_FOUND(404, "未找到"),

    /*--------------------用户相关状态码------------------------------------*/
    /* 成功操作 */
    LOGIN_SUCCESS(302, "登录成功"),
    REVISE_SUCCESS(303, "修改成功"),
    REGISTER_SUCCESS(304, "注册成功"),
    DELETE_SUCCESS(305, "删除成功"),
    FOUND_SUCCESS(307, "查找成功"),

    /* 错误/失败操作 */
    USERNAME_ERROR(400, "用户名错误"),
    PASSWORD_ERROR(401, "密码错误"),
    NOTLOGIN(402, "未登录"),
    USER_MOBILE_EXISTENCE(403, "手机号已被使用，请更换"),
    EMAIL_NOT_FOUND(405, "邮箱不存在"),
    EMAIL_EXISTENCE(406, "注册邮箱已存在"),
    PASSWORD_INCONSISTENT(408, "密码不一致");




    /*--------------------------------------------------------*/






    private final Integer code;
    private final String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}