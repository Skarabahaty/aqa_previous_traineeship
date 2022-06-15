package entities.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.CredentialsGenerator;

import java.time.Duration;
import java.util.List;
import java.util.Map;


public class LoginWithPasswordCheckForm extends Form {

    public LoginWithPasswordCheckForm() {
        super(By.xpath("//div[@class='login-form-with-pw-check']"), "login with password check form");
    }


    public void fillForm() {
        Map<String, String> email = CredentialsGenerator.generateEmail();
        String emailString = email.get("email");
        String domainString = email.get("domain");
        String dotString = email.get("dot");

        ITextBox emailTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "email textbox");
        emailTextBox.focus();
        emailTextBox.clearAndType(emailString);

        ITextBox domainTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "domain textbox");
        domainTextBox.focus();
        domainTextBox.clearAndType(domainString);

        AqualityServices.getBrowser().scrollWindowBy(0, 400);
        IComboBox dotSomethingComboBox = getElementFactory().getComboBox(By.xpath("//div[@class='dropdown__opener']"), "dot something combobox");
        int index = Integer.parseInt(dotString);
        dotSomethingComboBox.click();
        List<ILabel> elements = getElementFactory().findElements(By.xpath("//div[@class='dropdown__list-item']"), ILabel.class);
        elements.get(index).click();

        String password = CredentialsGenerator.generatePassword(emailString);
        ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password textbox");
        passwordTextBox.clearAndType(password);
    }

    public void acceptTerms() {
        ILabel acceptTermsCheckbox = getElementFactory().getLabel(By.xpath("//label[@for='accept-terms-conditions']"), "accept terms checkbox");
        acceptTermsCheckbox.click();
    }


    public void clickNextButton() {
        ILink nextButton = getElementFactory().getLink(By.className("button--secondary"), "next button");
        nextButton.click();
    }

    public void acceptCookies() {
        IButton acceptCookiesButton = getElementFactory().getButton(By.xpath("//button[text()='Not really, no']"),
                "accept cookies button",
                ElementState.DISPLAYED);
        acceptCookiesButton.click();
    }

    public boolean ifCookiesFormPresent() {
        ILabel cookiesForm = getElementFactory().getLabel(By.className("cookies"),
                "cookies form");
        return cookiesForm.state().isExist();
    }

    public void clickSendToBottomButton() {
        IButton sendToBottomButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'help-form__send-to-bottom-button')]"), "send to bottom button");
        sendToBottomButton.click();
    }

    public void waitForHelpFormContentToDisappear() {
        ILabel helpFormContent = getElementFactory().getLabel(By.className("help-form__title"), "help form content");
        AqualityServices.getConditionalWait()
                .waitFor(
                () -> ! helpFormContent.state().isDisplayed(),
                Duration.ofSeconds(30));
    }


    public boolean isHelpFormContentDisappear() {
        ILabel helpFormContent = getElementFactory().getLabel(By.className("help-form__title"), "help form content");
        return ! (helpFormContent.state().isDisplayed());
    }
}
