package org.yqj.source.cloud.feignla;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yqj.source.cloud.feignla.dto.BaseRequest;
import org.yqj.source.cloud.feignla.dto.BaseResponse;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/15
 */
@RestController("/feign")
@Slf4j
@RequestMapping("/feign")
public class FeignController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/info")
    public BaseResponse<String> info(@RequestParam("id") String id){
        log.info("local info gain request id :{}", id);
        return BaseResponse.successResponse(String.format("hello %s", id));
    }

    @PostMapping(value = "/server", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> server(@RequestBody BaseRequest request){
        log.info("local server gain request :{}", request);
        return BaseResponse.successResponse(String.format("hello %s", applicationName));
    }
}
