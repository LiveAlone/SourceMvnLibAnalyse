package org.yqj.source.pattern.abstractdocument.domain;


import org.yqj.source.pattern.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * Created by yaoqijun on 2017/6/8.
 * 文档的 Part 对应的 Part 应该实现接口 Part 的配置方式。
 */
public class Part extends AbstractDocument implements HasModel, HasPrice, HasType {
    public Part(Map<String, Object> properties) {
        super(properties);
    }
}
