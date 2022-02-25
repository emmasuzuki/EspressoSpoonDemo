package com.emmasuzuki.espressospoondemo;

import android.app.Activity;
import android.util.Log;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import com.emmasuzuki.espressospoondemo.annotations.Workshop;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class LoginActivityTest {


    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

//    @Rule
//    public TestRule testWatcher = new TestWatcher() {
//        @Override
//        protected void failed(Throwable e, Description description) {
//            Spoon.screenshot(getActivityInstance(), "Test_failed", description.getClassName(), description.getMethodName());
//        }
//    };

    private LoginActivity mActivity;

    @Test
    public void test_Invalid_Email_Error() throws InterruptedException {
        mActivity = loginActivityActivityTestRule.getActivity();

        onView(withId(R.id.email)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("phonepe"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.email)).check(matches(hasErrorText(mActivity.getString(R.string.msg_email_error))));
    }

    @Test
    public void test_Empty_Password_Error() throws InterruptedException {
        mActivity = loginActivityActivityTestRule.getActivity();

        onView(withId(R.id.email)).perform(typeText("test@test.com"));
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        Log.i("Worshop", "My custom log");
        onView(withId(R.id.password)).check(matches(hasErrorText("test")));
    }

   // @Test
    public void test_Set_Mismatch_Error() throws InterruptedException {
        mActivity = loginActivityActivityTestRule.getActivity();

        onView(withId(R.id.email)).perform(typeText("espresso@spoon.com"));
        onView(withId(R.id.password)).perform(typeText("bananacake"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        onView(withText(R.string.msg_mismatch)).check(matches(isDisplayed()));
    }

   // @Test
    public void test_Set_Correct_Cred() throws InterruptedException {
        mActivity = loginActivityActivityTestRule.getActivity();

        onView(withId(R.id.email)).perform(typeText("phonepe@test.com"));
        onView(withId(R.id.password)).perform(typeText("phonepe"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());
        Thread.sleep(3000);
        onView(withText(mActivity.getString(R.string.welcome_msg))).check(matches(isDisplayed()));
    }


}
