package org.yqj.source.pattern.abstractdocument.domain;


import org.yqj.source.pattern.abstractdocument.Document;

import java.util.stream.Stream;

/**
 * Created by yaoqijun on 2017/6/8.
 */
public interface HasParts extends Document {

    String properties = "parts";

    default Stream<Part> getParts(){
        return children(properties, Part::new);
    }
}
