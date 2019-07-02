package com.us.claudine.common.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author liangliang
 * @date 2019/5/28 4:27 PM
 */
@SpringBootTest
public class RetryUtilTest {

    @Test
    public void times() throws Exception {
        RetryUtil retryUtil = RetryUtil.times(3);
        Assert.assertNotNull(retryUtil);
    }

    @Test
    public void retry() throws Exception {
        retryTask();
    }

    public String retryTask() {
        RetryUtil.times(3).retry();
        System.out.println(Thread.currentThread() + ", retry running...");
        //int i = 1 / 0;
        return null;
    }

}