package org.yqj.source.cloud.feignlb.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yqj.source.cloud.feignlb.dto.BaseRequest;
import org.yqj.source.cloud.feignlb.dto.BaseResponse;

@FeignClient(name = "feign-la")
public interface LaServer {

    @RequestMapping(value = "/server", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<String> server(@RequestBody BaseRequest request);

}
