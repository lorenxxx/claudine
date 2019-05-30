package com.us.claudine.common.retry;

/**
 * @ClassName Demo
 * @Desciption TODO
 * @Author loren
 * @Date 2019/5/30 11:39 AM
 * @Version 1.0
 **/
public class Demo2 {

    public static void main(String[] args) {
        final Business business = new Business();

        String result = (String) RetryUtil.execute(new Retry() {
            @Override
            public Object retry() {
                return business.doBusiness();
            }
        }, 3, 3L);

        System.out.println("result = " + result);
    }


}
