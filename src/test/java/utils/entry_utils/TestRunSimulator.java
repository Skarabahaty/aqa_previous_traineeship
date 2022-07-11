package utils.entry_utils;

import models.TestEntry;
import utils.Randomizer;

import java.util.Calendar;
import java.util.Map;

public class TestRunSimulator {

    private TestRunSimulator() {
    }

    public static TestEntry simulateTestRuns(TestEntry testEntry, Map<String, Integer> map) {
        TestEntry clone = testEntry.clone();
        clone.setAuthorId(map.get("author"));
        clone.setProjectId(map.get("project"));
        clone.setSessionId(map.get("session"));
        clone.setStatusId(Randomizer.getRandomIntFromOneToBorder(3));
        clone.setStartTime(Calendar.getInstance().getTimeInMillis());
        clone.setEndTime(Calendar.getInstance().getTimeInMillis() + 10000);
        return clone;
    }
}
