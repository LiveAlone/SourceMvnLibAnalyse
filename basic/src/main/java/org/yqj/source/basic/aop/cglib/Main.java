package org.yqj.source.basic.aop.cglib;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/28
 */
public class Main {
    public static void main(String[] args) {
//        CglibBasic.proxyAbstractClass();

        CglibBasic.superMethodExecute();

        CglibBasic.animalInterfaceProxy();

        CglibBasic.personServiceProxy();
    }
}
