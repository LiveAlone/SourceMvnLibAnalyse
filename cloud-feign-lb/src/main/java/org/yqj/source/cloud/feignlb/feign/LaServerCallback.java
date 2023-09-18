package org.yqj.source.cloud.feignlb.feign;

import org.springframework.stereotype.Component;
import org.yqj.source.cloud.feignlb.dto.BaseRequest;
import org.yqj.source.cloud.feignlb.dto.BaseResponse;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/18
 */
@Component
public class LaServerCallback implements LaServer {
    @Override
    public BaseResponse<String> server(BaseRequest request) {
        return BaseResponse.failResponse(500, "server callback");
    }

    @Override
    public BaseResponse<String> info(String id) {
        return BaseResponse.failResponse(500, "info callback");
    }

    @Override
    public BaseResponse<String> timeoutCall(String id) {
        return BaseResponse.failResponse(500, "timeout callback");
    }

    @Override
    public BaseResponse<String> fail(String id) {
        return BaseResponse.failResponse(500, "fail callback");
    }
}
