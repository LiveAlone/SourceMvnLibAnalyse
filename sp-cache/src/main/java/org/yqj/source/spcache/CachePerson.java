package org.yqj.source.spcache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/4
 * Email: yaoqijunmail@foxmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CachePerson {
    private long id;
    private String name;
    private int age;
}
