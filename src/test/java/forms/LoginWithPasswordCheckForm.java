package forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;


public class LoginWithPasswordCheckForm extends Form {

    public LoginWithPasswordCheckForm() {
        super(By.xpath("//div[@class='login-form-with-pw-check']"), "login with password check form");
    }

    private final By acceptCookiesButtonLocator = By.xpath("//button[text()='Not really, no']");
    private final By acceptTermsCheckBoxLocator = By.xpath("//label[@for='accept-terms-conditions']");
    private final By cookiesFormLocator = By.className("cookies");
    private final By comboBoxElementLocator = By.xpath("//div[@class='dropdown__list-item']");
    private final By domainTextBoxLocator = By.xpath("//input[@placeholder='Domain']");
    private final By dotSomethingComboBoxLocator = By.xpath("//div[@class='dropdown__opener']");
    private final By emailTextBoxLocator = By.xpath("//input[@placeholder='Your email']");
    private final By helpFormContentLocator = By.className("help-form__title");
    private final By nextButtonLocator = By.className("button--secondary");
    private final By passwordTextBoxLocator = By.xpath("//input[@placeholder='Choose Password']");
    private final By sendToBottomButtonLocator = By.xpath("//button[contains(@class, 'help-form__send-to-bottom-button')]");
    private final By timerLabelLocator = By.xpath("//div[contains(@class, 'timer')]");

    private final ILabel acceptTermsCheckbox = getElementFactory().getLabel(acceptTermsCheckBoxLocator, "accept terms checkbox");
    private final IButton acceptCookiesButton = getElementFactory().getButton(acceptCookiesButtonLocator, "accept cookies button", ElementState.DISPLAYED);
    private final ILabel cookiesForm = getElementFactory().getLabel(cookiesFormLocator, "cookies form", ElementState.DISPLAYED);
    private final ITextBox domainTextBox = getElementFactory().getTextBox(domainTextBoxLocator, "domain textbox");
    private final IComboBox dotSomethingComboBox = getElementFactory().getComboBox(dotSomethingComboBoxLocator, "dot something combobox");
    private final ITextBox emailTextBox = getElementFactory().getTextBox(emailTextBoxLocator, "email textbox");
    private final ILabel helpFormContent = getElementFactory().getLabel(helpFormContentLocator, "help form content");
    private final ILink nextButton = getElementFactory().getLink(nextButtonLocator, "next button");
    private final ITextBox passwordTextBox = getElementFactory().getTextBox(passwordTextBoxLocator, "password textbox");
    private final IButton sendToBottomButton = getElementFactory().getButton(sendToBottomButtonLocator, "send to bottom button");
    private final ILabel timerLabel = getElementFactory().getLabel(timerLabelLocator, "timer label");


    public void setEmail(String email) {
        emailTextBox.clearAndType(email);
    }

    public void setDomain(String domain) {
        domainTextBox.clearAndType(domain);
    }

    public void clickDotSomethingComboBox() {
        dotSomethingComboBox.getJsActions().scrollToTheCenter();
        dotSomethingComboBox.click();
    }

    public void setDotSomething(int index) {
        List<ILabel> comboBoxElements = getElementFactory().findElements(comboBoxElementLocator, ILabel.class);
        comboBoxElements.get(index - 1).click();
    }

    public void setPassword(String password) {
        passwordTextBox.clearAndType(password);
    }

    public void acceptTerms() {
        acceptTermsCheckbox.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public boolean isCookiesFormDisappeared() {
        return cookiesForm.state().waitForNotDisplayed();
    }

    public void clickSendToBottomButton() {
        sendToBottomButton.click();
    }

    public void waitForHelpFormContentToDisappear() {
        helpFormContent.state().waitForNotDisplayed();
    }

    public boolean isHelpFormContentDisplayed() {
        return helpFormContent.state().isDisplayed();
    }

    public String getTimerState() {
        return timerLabel.getText();
    }

    public int getComboBoxOptionsAmount() {
        List<ILabel> comboBoxElements = getElementFactory().findElements(comboBoxElementLocator, ILabel.class);
        return comboBoxElements.size();
    }
}
