package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SignInPage extends Form {


    public SignInPage() {
        super(By.className("vkc__PassportBox__box"), "sign in page");
    }


    private final By passwordInputLocator = By.name("password");
    private final By loginInputLocator = By.name("login");
    private final ITextBox passwordInput = getElementFactory().getTextBox(passwordInputLocator, "password input field");
    private final ITextBox loginInput = getElementFactory().getTextBox(loginInputLocator, "login input field");
    private final By submitButtonLocator = By.xpath("//button[@type=\"submit\"]");
    private final IButton submitButton = getElementFactory().getButton(submitButtonLocator, "submit button");


    public void setPassword(String password) {
        passwordInput.clearAndType(password);
    }

    public void setLogin(String login) {
        loginInput.clearAndType(login);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }
}
