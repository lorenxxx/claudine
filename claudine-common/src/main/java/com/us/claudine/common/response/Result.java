package com.us.claudine.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangliang
 * @date 2018/9/17 4:01 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {

    private boolean succeed;

    private Integer code;

    private String msg;

    private T data;

    public static <T> Result success(String msg, T data) {
        return new Result(true, null, null, data);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(false, code, msg, null);
    }

}
