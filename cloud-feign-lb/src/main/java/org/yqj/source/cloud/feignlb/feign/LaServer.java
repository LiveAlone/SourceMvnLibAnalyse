package org.yqj.source.cloud.feignlb.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yqj.source.cloud.feignlb.dto.BaseRequest;
import org.yqj.source.cloud.feignlb.dto.BaseResponse;

@FeignClient(name = "feign-la", path = "/feign", fallback = LaServerCallback.class)
public interface LaServer {

    @PostMapping(value = "/server", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<String> server(@RequestBody BaseRequest request);

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    BaseResponse<String> info(@RequestParam("id") String id);

    @RequestMapping(value = "/timeout", method = RequestMethod.GET)
    BaseResponse<String> timeoutCall(@RequestParam("id") String id);

    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    BaseResponse<String> fail(@RequestParam("id") String id);
}
