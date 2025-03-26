package org.yqj.source.spcore.framework.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/26
 * Email: yaoqijunmail@foxmail.com
 */
//@Component
@Slf4j
public class BusinessTraceAdvisor extends AbstractPointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                if (AopUserService.class.equals(targetClass)) {
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public Advice getAdvice() {
        return new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                log.info("********** before business service start");
                Object result = invocation.proceed();
                log.info("********** after business service finish");
                return result;
            }
        };
    }
}
