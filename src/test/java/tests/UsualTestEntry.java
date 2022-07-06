package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

@Listeners(utils.TestListener.class)

public class UsualTestEntry {

    @Test()
    public void testName() {
        boolean bool = ThreadLocalRandom.current().nextBoolean();
        Assert.assertTrue(bool);
    }

    @AfterMethod
    public void tearDown() {

    }
}
