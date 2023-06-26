package org.yqj.source.pattern.abstractdocument.domain;

import org.yqj.source.pattern.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * Created by yaoqijun on 2017/6/8.
 */
public class Car extends AbstractDocument implements HasModel, HasPrice, HasParts {
    public Car(Map<String, Object> properties) {
        super(properties);
    }
}
