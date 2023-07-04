package org.yqj.source.spcore.framework.env;

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
@Configuration
public class ConfigurationBean {

    @Value("${config.value_first:value_first}")
    @Getter
    private String valueFirst;

    @Value("${config.value_second:value_second}")
    @Getter
    private String valueSecond;
}
