package com.emmasuzuki.espressospoondemo.tests;

import org.junit.Test;;
import com.emmasuzuki.espressospoondemo.pages.BasePage;
import com.emmasuzuki.espressospoondemo.pages.LoginPage;
import com.emmasuzuki.espressospoondemo.utils.annotations.Workshop;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class LoginActivityTest extends BaseTest {

    LoginPage loginPage;

    @Test
    public void test_Invalid_Email_Error() {
        loginPage = new LoginPage();
        loginPage.login("test", "phonepe")
                .getEmailField()
                    .check(matches(hasErrorText("Please enter your email")));
    }

    @Test
    public void test_Empty_Password_Error() {
        loginPage = new LoginPage();
        loginPage.login("test@test.com", "")
                .getPasswordField()
                    .check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void test_Set_Mismatch_Error() {
        loginPage = new LoginPage();
        loginPage.login("espresso@spoon.com", "bananacake")
                .getErrorMsg()
                    .check(matches(allOf(isDisplayed()
                            , withText("Your email or password is wrong"))));

    }

    @Workshop
    @Test
    public void test_Set_Correct_Cred() {
        loginPage = new LoginPage();
        loginPage.successfulLogin("phonepe@test.com", "phonepe")
                .getWelcomeMsg()
                .check(matches(allOf(isDisplayed()
                        , withText("Welcome! Everyone!"))));

    }

    @Test
    public void test_Set_Correct_Cred_failure() {
        loginPage = new LoginPage();
        loginPage.successfulLogin("phonepe@test.com", "phonepe1")
                .getWelcomeMsg()
                .check(matches(allOf(isDisplayed()
                        , withText("Welcome! Everyone!"))));
    }

}
