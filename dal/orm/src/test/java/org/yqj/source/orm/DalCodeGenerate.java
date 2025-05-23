package org.yqj.source.orm;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Types;
import java.util.Collections;

/**
 * @author 10126730
 * Date: 2024/8/27 19:59
 * Description:
 */
@Slf4j
public class DalCodeGenerate {

//    @Test
    public void generateCode() {
        log.info("start main auto generate");
        String url = "jdbc:mysql://127.0.0.1:3306/orders?useSSL=false&useUnicode=true&characterEncoding=UTF-8&remarks=true&useInformationSchema=true";
        String outputDir = "orm/target/auto_generator";
        String parentPackageName = "org.yqj.source";
        String moduleName = "orm";

        FastAutoGenerator.create(url, "root", "root")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(outputDir); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent(parentPackageName) // 设置父包名
                                .moduleName(moduleName) // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir)) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("user") // 设置需要生成的表名
                                .addTablePrefix("u") // 设置过滤表前缀
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
