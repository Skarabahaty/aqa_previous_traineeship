package forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    private final By startLinkLocator = By.className("start__link");
    private final ILink startLink = getElementFactory().getLink(startLinkLocator, "start link");

    public MainPage() {
        super(By.className("start__button"), "main page");
    }

    public void clickStartLink() {
        startLink.click();
    }
}
