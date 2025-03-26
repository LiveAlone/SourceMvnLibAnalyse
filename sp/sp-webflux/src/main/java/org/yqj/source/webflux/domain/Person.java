package org.yqj.source.webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yaoqijun on 2017-11-05.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Integer id;

    private String name;

    private Integer age;
}
