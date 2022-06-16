package utils;

import aquality.selenium.core.logging.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotUtil {

    public static void sendImageUsingRobot(int robotDelay) {

        try {
            Robot robot = new Robot();
            robot.delay(robotDelay);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(robotDelay);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(robotDelay);

        } catch (AWTException e) {
            Logger.getInstance().fatal("can't send an image", e);
            e.printStackTrace();
        }

    }
}
