package org.yqj.source.pattern.abstractdocument.domain;


import org.yqj.source.pattern.abstractdocument.Document;

import java.util.Optional;

/**
 * Created by yaoqijun on 2017/6/8.
 */
public interface HasType extends Document {

    String type = "type";

    default Optional<String> getType() {
        return Optional.ofNullable((String) get(type));
    }

}
