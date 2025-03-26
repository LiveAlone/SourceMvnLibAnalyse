package org.yqj.source.spcore.framework.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/12
 * Email: yaoqijunmail@foxmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContextPerson {

    private String id;
    
    private String name;

    private Integer age;
}
