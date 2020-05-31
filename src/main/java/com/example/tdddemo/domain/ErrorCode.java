package com.example.tdddemo.domain;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public class ErrorCode {
    private int status;
    private String code;
    private String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public ErrorCode setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ErrorCode setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode setCustomMessage(String message) {
        return new ErrorCode(this.status, this.code, message);
    }

    public ErrorCode setCustomMessage(String message, Object ... args) {
        this.message = String.format(message, args);
        return new ErrorCode(this.status, this.code, this.message);
    }

    public ErrorCode appendCustomMessage(String message) {
        return new ErrorCode(this.status, this.code, this.message + " " + message);
    }

    public ErrorCode format(Object ... args) {
        this.message = String.format(message, args);
        return this;
    }

    public static ErrorCode EMAIL_INVALID = new ErrorCode(SC_BAD_REQUEST, "EMAIL_INVALID", "Email is invalid");
    public static ErrorCode MOBILE_INVALID = new ErrorCode(SC_BAD_REQUEST, "MOBILE_INVALID", "Mobile is invalid");
    public static ErrorCode PASSWORD_INVALID = new ErrorCode(SC_BAD_REQUEST, "PASSWORD_INVALID", "Password is invalid");
}
