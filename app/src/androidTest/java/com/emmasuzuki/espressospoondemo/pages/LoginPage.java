package com.emmasuzuki.espressospoondemo.pages;

import androidx.test.espresso.ViewInteraction;

import com.emmasuzuki.espressospoondemo.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginPage extends BasePage {

    public HomePage successfulLogin(String email, String password) {
        login(email, password);
        return new HomePage();
    }

    public LoginPage login(String email, String password) {
        getEmailField().perform(typeText(email));
        getPasswordField().perform(typeText(password));
        getSubmitField().perform(click());
        return this;
    }

    public ViewInteraction getEmailField() {
        return onView(withId(R.id.email));
    }

    public ViewInteraction getPasswordField() {
        return onView(withId(R.id.password));
    }

    public ViewInteraction getSubmitField() {
        return onView(withId(R.id.submit));
    }

    public ViewInteraction getErrorMsg() {
        return onView(withId(R.id.error));
    }
}
