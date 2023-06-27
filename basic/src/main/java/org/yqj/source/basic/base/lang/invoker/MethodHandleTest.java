package org.yqj.source.basic.base.lang.invoker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

/**
 * @author yaoqijun on 2018-05-08.
 */
public class MethodHandleTest {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findStatic(MethodHandleTest.class, "doubleValue", MethodType.methodType(int.class, int.class));
        List<Integer> dataList = Arrays.asList(1, 2, 3, 4, 5);
        MethodHandleTest.transform(dataList, mh);// 方法做为参数
        for (Integer data : dataList) {
            System.out.println(data);//2,4,6,8,10
        }
    }

    public static void transform(List<Integer> row, MethodHandle methodHandle) throws Throwable {
        if (row == null || row.isEmpty()){
            return;
        }

        for (int i =0; i < row.size(); i++){
            row.set(i, (int) methodHandle.invoke(row.get(i)));
        }
    }

    public static int doubleValue(int val){
        return val * 2;
    }
}
