package org.yqj.source.cloud.feignla;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yqj.source.cloud.feignla.dto.BaseResponse;

import java.util.Arrays;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/15
 */
@RestController
@Slf4j
public class ServerController {

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping(value = "/server", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> health(HttpServletRequest request){
        return BaseResponse.successResponse(String.format("hello %s", applicationName));
    }
}
