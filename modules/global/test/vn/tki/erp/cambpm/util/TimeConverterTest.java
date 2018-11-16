package vn.tki.erp.cambpm.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeConverterTest {

    @Test
    public void millisecondToDurationString() {
        long miliseconds = 186015000;
        String durationString = TimeConverter.millisecondToDurationString(miliseconds);
        assertEquals("2d 03:40:15", durationString);
    }
}