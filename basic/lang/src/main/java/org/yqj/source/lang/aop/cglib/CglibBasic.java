package org.yqj.source.lang.aop.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.yqj.source.lang.aop.cglib.model.AbstractAnimal;
import org.yqj.source.lang.aop.cglib.model.Animal;
import org.yqj.source.lang.aop.cglib.model.Cat;
import org.yqj.source.lang.aop.cglib.model.CommonAnimal;
import org.yqj.source.lang.aop.cglib.model.PersonService;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/26
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class CglibBasic {

    public static void proxyAbstractClass() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(AbstractAnimal.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                log.info("method intercept before start :{}", method.getName());
                return proxy.invokeSuper(obj, args);
            }
        });

        Object proxy = enhancer.create();
        Animal animal = (Animal) proxy;
        animal.eat("jamama");
//        animal.run("huhaha"); // error
    }

    public static void superMethodExecute() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CommonAnimal.class);
//        enhancer.setSuperclass(Animal.class);     // error no method super found
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                log.info("method intercept before start :{}", method.getName());
                for (Object arg : args) {
                    log.info("*** req arg :{}", arg);
                }
//                return method.invoke(cat, args);
//                return proxy.invoke(cat, args);
                // 执行父级抽象方法
                return proxy.invokeSuper(obj, args);
            }
        });

        Object proxy = enhancer.create();
        Animal animal = (Animal) proxy;
        animal.run("jamama");
    }

    public static void animalInterfaceProxy() {
        Cat cat = new Cat();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Animal.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                log.info("method intercept before start :{}", method.getName());
                for (Object arg : args) {
                    log.info("*** req arg :{}", arg);
                }
                return proxy.invoke(cat, args);
//                return proxy.invokeSuper(obj, args);
            }
        });

        Object proxy = enhancer.create();
        Animal animal = (Animal) proxy;
        animal.run("jamama");
    }

    public static void personServiceProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "Hello Tom!");
        PersonService proxy = (PersonService) enhancer.create();

        System.out.println(proxy.sayHello(null));
        System.out.println(proxy.lengthOfName("wtf"));
    }
}
