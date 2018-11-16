package vn.tki.erp.cambpm.util;

import java.util.concurrent.TimeUnit;

public class TimeConverter {
    public static String millisecondToDurationString(Long millisecond) {
        if (millisecond == null)
            return null;

        return String.format("%dd %02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toDays(millisecond),
                TimeUnit.MILLISECONDS.toHours(millisecond) % 24,
                TimeUnit.MILLISECONDS.toMinutes(millisecond) % 60,
                TimeUnit.MILLISECONDS.toSeconds(millisecond) % 60);
    }
}
