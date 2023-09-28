package org.yqj.source.engine.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/28
 */
public class Main {
    public static void main(String[] args) {
        ReaderUtil readerUtil = new ReaderUtil(new GenericObjectPool<StringBuffer>(new StringBufferFactory()));
        Reader reader = new StringReader("foo");
        try {
            System.out.println(readerUtil.readToString(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
