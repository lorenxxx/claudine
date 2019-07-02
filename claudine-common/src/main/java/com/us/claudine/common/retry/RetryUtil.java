package com.us.claudine.common.retry;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RetryUtil
 * @Desciption TODO
 * @Author loren
 * @Date 2019/5/30 1:43 PM
 * @Version 1.0
 **/
@Slf4j
public class RetryUtil {

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
            RetryUtil.retryTimes.set(retryTimes);
            RetryUtil.retryInterval.set(retryInterval);

            int rt = RetryUtil.retryTimes.get();
            for (int i = rt; i > 0; i--) {
                try {
                    if (i < rt && RetryUtil.retryInterval.get() > 0) {
                        log.info("sleep...");
                        TimeUnit.SECONDS.sleep(RetryUtil.retryInterval.get());
                    }

                    return retry.retry();
                } catch (InterruptedException e) {
                    // ignore...
                } catch (Exception e) {
                    log.error("exception occur: {}", e);
                }
            }

            return null;
        } finally {
            RetryUtil.retryTimes.remove();
            RetryUtil.retryInterval.remove();
        }
    }

}
