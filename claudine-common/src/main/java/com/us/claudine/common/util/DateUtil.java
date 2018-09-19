package com.us.claudine.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Loren on 19/09/2018.
 */
public class DateUtil {

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

}
