package org.yqj.source.cloud.feignlb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest {

    private Long requestId;

    private String userName;

}
