package org.yqj.source.common.pool;

import org.apache.commons.pool2.ObjectPool;

import java.io.IOException;
import java.io.Reader;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/8/12
 * Email: yaoqijunmail@foxmail.com
 */
public class ReaderUtil {

    private ObjectPool<StringBuffer> pool;

    public ReaderUtil(ObjectPool<StringBuffer> pool) {
        this.pool = pool;
    }

    public String readToString(Reader in) throws IOException {
        StringBuffer buf = null;
        try {
            buf = pool.borrowObject();
            for (int c = in.read(); c != -1; c = in.read()) {
                buf.append((char) c);
            }
            return buf.toString();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unable to borrow buffer from pool" + e.toString());
        } finally {
            try {
                // close buffer
                in.close();
            } catch (Exception e) {
                // ignored
            }
            try {
                // return bar
                if (null != buf) {
                    pool.returnObject(buf);
                }
            } catch (Exception e) {
                // ignored
            }
        }
    }
}
