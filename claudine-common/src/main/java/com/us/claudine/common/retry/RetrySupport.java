package com.us.claudine.common.retry;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RetrySupport
 * @Desciption TODO
 * @Author loren
 * @Date 2019/5/30 11:02 AM
 * @Version 1.0
 **/
@Slf4j
public class RetrySupport {

    /**
     * 重试次数
     */
    private static ThreadLocal<Integer> retryTimes = new ThreadLocal<>();

    /**
     * 重试间隔
     */
    private static ThreadLocal<Long> retryInterval = new ThreadLocal<>();

    /**
     * 执行重试
     *
     * @param retry         重试任务
     * @param retryTimes    重试次数
     * @param retryInterval 重试间隔
     * @return
     */
    public static Object execute(Retry retry, Integer retryTimes, Long retryInterval) {
        try {
            RetrySupport.retryTimes.set(retryTimes);
            RetrySupport.retryInterval.set(retryInterval);

            Retry proxy = (Retry) new RetryProxy(retry).getProxy();
            return proxy.retry();
        } finally {
            RetrySupport.retryTimes.remove();
            RetrySupport.retryInterval.remove();
        }
    }

    static class RetryProxy implements InvocationHandler {

        private Object target;

        public RetryProxy(Object target) {
            this.target = target;
        }

        public Object getProxy() {
            return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            int rt = retryTimes.get();
            for (int i = rt; i > 0; i--) {
                try {
                    if (i < rt && retryInterval.get() > 0) {
                        log.info("sleep...");
                        TimeUnit.SECONDS.sleep(retryInterval.get());
                    }

                    return method.invoke(target, args);
                } catch (InterruptedException e) {
                    // ignore...
                } catch (Exception e) {
                    log.error("exception occur: {}", e);
                }
            }

            return null;
        }

    }

}
