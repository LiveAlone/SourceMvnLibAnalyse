package org.yqj.source.orm.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.javassist.compiler.ast.StringL;
import org.yqj.source.orm.dataobject.BaseDO;
import org.yqj.source.orm.type.StringListTypeHandler;

import java.util.List;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2024/4/18
 * Email: yaoqijunmail@foxmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "`user`", autoResultMap = true)
public class User extends BaseDO {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> course;
}
