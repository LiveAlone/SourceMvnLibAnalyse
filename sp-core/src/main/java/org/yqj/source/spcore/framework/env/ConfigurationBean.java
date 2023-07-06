package org.yqj.source.spcore.framework.env;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/4
 * Email: yaoqijunmail@foxmail.com
 */
//@Configuration
@Data
public class ConfigurationBean {

    @Value("${config.value_first:value_first}")
    private String valueFirst;

    @Value("${config.value_second:value_second}")
    private String valueSecond;

    @Value("${processor.value}")
    private String processorValue;
}
