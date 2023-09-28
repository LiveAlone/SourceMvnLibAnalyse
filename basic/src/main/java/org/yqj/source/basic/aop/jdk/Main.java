package org.yqj.source.basic.aop.jdk;

import lombok.extern.slf4j.Slf4j;
import org.yqj.source.basic.aop.jdk.model.Coder;
import org.yqj.source.basic.aop.jdk.model.Person;
import org.yqj.source.basic.aop.jdk.model.Runner;
import org.yqj.source.basic.aop.jdk.model.Worker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/26
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        createInstanceWithInstance();
//
//        createInstanceWithClass();
    }

    public static void createInstanceWithClass() throws Exception {
        Person sourceTarget = new Person();
        Class proxyClass = Proxy.getProxyClass(sourceTarget.getClass().getClassLoader(), sourceTarget.getClass().getInterfaces());
        System.out.println(proxyClass.getName());
        System.out.println(Coder.class.isAssignableFrom(proxyClass));
        System.out.println(Worker.class.isAssignableFrom(proxyClass));
        System.out.println(Runner.class.isAssignableFrom(proxyClass));
    }

    public static void createInstanceWithInstance() {
        Person sourceTarget = new Person();

        Object proxyInstance = Proxy.newProxyInstance(sourceTarget.getClass().getClassLoader(), new Class[]{Coder.class, Runner.class, Worker.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result;
                try {
                    log.info("before proxy method :{}", method.getName());
                    result = method.invoke(sourceTarget, args);
                } catch (Exception e) {
                    throw new RuntimeException("unexpected invocation exception: " +
                            e.getMessage());
                } finally {
                    log.info("after proxy method :{}", method.getName());
                }
                return result;
            }
        });

        if (proxyInstance instanceof Runner) {
            ((Runner) proxyInstance).run("RunnerName");
        }

        if (proxyInstance instanceof Coder) {
            ((Coder) proxyInstance).code("coderName");
        }

        if (proxyInstance instanceof Worker) {
            ((Worker) proxyInstance).work("workerName");
        }
    }
}
