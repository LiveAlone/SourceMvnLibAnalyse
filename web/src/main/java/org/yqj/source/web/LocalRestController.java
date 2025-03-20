package org.yqj.source.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yqj.source.web.dto.AddressResponse;
import org.yqj.source.web.dto.BaseResponse;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2019-08-07
 * Email: yaoqijunmail@foxmail.com
 */
@RestController
@Slf4j
public class LocalRestController {

    @RequestMapping(value = "/health", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> health(HttpServletRequest request){
        request.getParameterMap().forEach((key, value) -> {
            log.info("request params gain key:{}, value:{}", key, Arrays.asList(value));
        });
        log.info("current thread is :{}", Thread.currentThread().getName());
        return BaseResponse.successResponse("success");
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<AddressResponse> address() throws UnknownHostException {
        return BaseResponse.successResponse(new AddressResponse(InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress()));
    }
}
