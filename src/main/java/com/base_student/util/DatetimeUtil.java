package com.base_student.util;

import static com.base_student.constants.Constants.UTILITY_CLASS;

import java.time.Instant;

public final class DatetimeUtil {

    private DatetimeUtil() {
        throw new UnsupportedOperationException(UTILITY_CLASS);
    }

    public static Long getTimestamptInMilliseconds() {
        return Instant.now().toEpochMilli();
    }

}
