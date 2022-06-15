package forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    public MainPage() {
        super(By.xpath("//button[@class='start__button']"), "main page");
    }

    public void clickStartLink() {
        ILink startLink = getElementFactory().getLink(By.xpath("//a[@class='start__link']"), "start link");
        startLink.click();
    }
}
