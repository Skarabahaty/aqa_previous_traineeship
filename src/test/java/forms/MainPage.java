package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    public MainPage() {
        super(By.className("VkIdForm"), "main page");
    }

    private final By signInButtonLocator = By.xpath("//button[contains(@class, \"VkIdForm__signInButton\")]");
    private final IButton signInButton = getElementFactory().getButton(signInButtonLocator, "sign in button");

    public void clickSignInButton() {
        signInButton.click();
    }

}
