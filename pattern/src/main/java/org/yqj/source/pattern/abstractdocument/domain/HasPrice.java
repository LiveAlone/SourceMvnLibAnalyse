package org.yqj.source.pattern.abstractdocument.domain;


import org.yqj.source.pattern.abstractdocument.Document;

import java.util.Optional;

/**
 * Created by yaoqijun on 2017/6/8.
 */
public interface HasPrice extends Document {

    String PROPERTY = "price";

    default Optional<Number> getPrice(){
        return Optional.ofNullable((Number) get(PROPERTY));
    }

}
