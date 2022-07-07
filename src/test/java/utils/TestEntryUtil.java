package utils;

import models.TestEntry;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class TestEntryUtil {

    public static TestEntry simulateTestRun(TestEntry testEntry) {
        TestEntry clone = testEntry.clone();
        clone.setAuthorId(1);
        clone.setProjectId(7);
        clone.setStatusId(ThreadLocalRandom.current().nextInt(1, 4));
        clone.setStartTime(Calendar.getInstance().getTimeInMillis());
        clone.setEndTime(Calendar.getInstance().getTimeInMillis() + 10000);
        return clone;
    }
}
