package org.yqj.source.trans.db1;

/**
 * @author yaoqijun on 2018-04-08.
 * person pretend mapper not real mapper
 */
public interface PersonPretendMapper {

    default String sayHello(){
        return "hello world";
    }

//    Person selectById(@Param("id") Long id);
}
