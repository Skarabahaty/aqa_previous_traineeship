package models.forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NavigationForm extends Form {

    public NavigationForm() {
        super(By.id("side_bar"), "navigation form");
    }

    private final By myPageLinkLocator = By.id("l_pr");
    private final ILink myPageLink = getElementFactory().getLink(myPageLinkLocator, "my page link");

    public void clickMyPageButton() {
        myPageLink.click();
    }
}
