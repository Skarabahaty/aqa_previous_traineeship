package entities.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementFactory;
import aquality.selenium.elements.actions.JsActions;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.CredentialsGenerator;

import java.util.HashMap;


public class LoginWithPasswordCheckForm extends Form {

    public LoginWithPasswordCheckForm() {
        super(By.xpath("//div[@class='login-form-with-pw-check']"), "login with password check form");
    }

    public void fillForm() {
        HashMap<String, String> email = CredentialsGenerator.generateEmail();
        String emailString = email.get("email");
        String domainString = email.get("domain");
        String dotString = email.get("dot");

        ITextBox emailTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "email textbox");
        emailTextBox.clearAndType(emailString);

        ITextBox domainTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "domain textbox");
        domainTextBox.clearAndType(domainString);

        IComboBox dotSomethingComboBox = getElementFactory().getComboBox(By.xpath("//div[@class='dropdown__list']"), "dot something combobox");
        int index = Integer.parseInt(dotString);
        dotSomethingComboBox.selectByIndex(index);

        String password = CredentialsGenerator.generatePassword(emailString);
        ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password textbox");
        passwordTextBox.clearAndType(password);
    }

    public void acceptTerms() {
        ILink linkToTerms = getElementFactory().getLink(By.xpath("//span[@class='login-form__terms-conditions-underline']"), "scroller");
        linkToTerms.click(); //TODO complete

    }


}
