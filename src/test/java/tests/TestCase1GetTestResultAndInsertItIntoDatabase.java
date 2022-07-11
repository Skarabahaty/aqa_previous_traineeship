package tests;

import listeners.ListenerForTestCase1;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

@Listeners(ListenerForTestCase1.class)

public class TestCase1GetTestResultAndInsertItIntoDatabase {

    @Test()
    public void testCanFailWith50PercentProbability() {
        boolean bool = ThreadLocalRandom.current().nextBoolean();
        Assert.assertTrue(bool);
    }
}
