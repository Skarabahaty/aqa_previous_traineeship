package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

@Listeners(utils.TestListener.class)

public class UsualTest {

    @Test
    public void testCanFailWith50PercentProbability() {
        boolean bool = ThreadLocalRandom.current().nextBoolean();
        Assert.assertTrue(bool);
    }

    @AfterMethod()
    public void checkForAddingEntry() {

    }
}
