package com.emmasuzuki.espressospoondemo.tests;

import android.content.Intent;

import org.junit.Test;;
import com.emmasuzuki.espressospoondemo.pages.BasePage;
import com.emmasuzuki.espressospoondemo.pages.HomePage;
import com.emmasuzuki.espressospoondemo.pages.LoginPage;
import com.emmasuzuki.espressospoondemo.utils.annotations.Failure;
import com.emmasuzuki.espressospoondemo.utils.annotations.Workshop;

import net.lightbody.bmp.core.har.Har;

import java.io.File;
import java.io.IOException;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class LoginActivityTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    @Workshop
//    @Test
    public void test_Invalid_Email_Error() throws IOException, InterruptedException {

        loginPage = new LoginPage();
        loginPage.login("phonepe", "phonepe")
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
    public void test_Set_Correct_Cred() throws InterruptedException {
        loginPage = new LoginPage();
        loginPage.successfulLogin("phonepe@test.com", "phonepe")
                .getWelcomeMsg()
                .check(matches(allOf(isDisplayed()
                        , withText("Welcome! Everyone!"))));

        Thread.sleep(10000);
    }

//    @Failure
//    @Test
//    public void test_Set_Correct_Cred_failure() {
//        loginPage = new LoginPage();
//        loginPage.successfulLogin("phonepe@test.com", "phonepe1")
//                .getWelcomeMsg()
//                .check(matches(allOf(isDisplayed()
//                        , withText("Welcome! Everyone!"))));
//    }
}
