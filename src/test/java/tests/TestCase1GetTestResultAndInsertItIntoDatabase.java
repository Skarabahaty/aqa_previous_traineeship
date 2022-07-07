package tests;

import listeners.TestListenerForTestResultsInsertion;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JDBConnector;

import java.util.concurrent.ThreadLocalRandom;

@Listeners(TestListenerForTestResultsInsertion.class)

public class TestCase1GetTestResultAndInsertItIntoDatabase {

    @BeforeMethod
    public void setUp() {
        JDBConnector.insertNeededFieldsInTable();
    }

    @Test()
    public void testCanFailWith50PercentProbability() {
        boolean bool = ThreadLocalRandom.current().nextBoolean();
        Assert.assertTrue(bool);
    }
}
