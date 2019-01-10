package cn.porkchop.springbootstarter.util;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean success;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public Result() {
    }

    public static Result success() {
        Result result = new Result();
        result.success = true;
        result.message = "操作成功";
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.success = true;
        result.message = "操作成功";
        result.data = data;
        return result;
    }

    public static Result fail(String message) {
        Result result = new Result();
        result.success = false;
        result.message = message;
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}