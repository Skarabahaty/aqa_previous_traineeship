package steps;

import models.forms.SignInPage;

public class SignInPageSteps {

    private final SignInPage signInPage;

    public SignInPageSteps() {
        this.signInPage = new SignInPage();
    }

    public void signIn(String login, String password) {
        signInPage.setLogin(login);
        signInPage.clickSubmitButton();
        signInPage.setPassword(password);
        signInPage.clickSubmitButton();
    }

    public void waitForDisplayed() {
        signInPage.state().waitForDisplayed();

    }
}
