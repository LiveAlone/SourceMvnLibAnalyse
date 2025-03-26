package org.yqj.source.webflux.dto;


import lombok.Data;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2019-08-06
 * Email: yaoqijunmail@foxmail.com
 */
@Data
public class BaseResponse<T> {

    private boolean success;

    private int code;

    private T data;

    public static <T> BaseResponse<T> successResponse(T data){
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setSuccess(true);
        baseResponse.setCode(0);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static BaseResponse<String> failResponse(int code, String message){
        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setData(message);
        return baseResponse;
    }
}
