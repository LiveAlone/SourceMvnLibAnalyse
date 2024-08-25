package org.yqj.source.trans.db1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.yqj.source.trans.domain.Person;

/**
 * @author yaoqijun on 2018-03-12.
 */
@Mapper
public interface PersonDB1Mapper {

    @Select("select * from t_person where id = #{id}")
    Person selectById(@Param("id") Long id);

    @Update("update t_person set score = #{score} where id = #{id}")
    int updatePersonScore(@Param("id") Long id, @Param("score") Double score);

}
