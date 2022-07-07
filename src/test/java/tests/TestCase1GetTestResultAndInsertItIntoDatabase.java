package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListenerForTEstResultsInsertion;

import java.util.concurrent.ThreadLocalRandom;

@Listeners(TestListenerForTEstResultsInsertion.class)

public class TestCase1GetTestResultAndInsertItIntoDatabase {

    @Test
    public void testCanFailWith50PercentProbability() {
        boolean bool = ThreadLocalRandom.current().nextBoolean();
        Assert.assertTrue(bool);
    }

    @AfterMethod()
    public void checkForAddingEntry() {

    }
}
