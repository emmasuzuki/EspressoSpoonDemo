package com.emmasuzuki.espressospoondemo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.emmasuzuki.espressospoondemo.annotations.Workshop;
import com.squareup.spoon.Spoon;

import java.util.Collection;
import java.util.Iterator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class LoginActivityTest {
    private Activity mActivity;

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public TestRule testWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            Spoon.screenshot(mActivity, "Test_failed", description.getClassName(), description.getMethodName());
        }
    };

    @Before
    public void launch() {
        mActivity = loginActivityActivityTestRule.getActivity();
    }

    @Test
    public void test_Invalid_Email_Error() throws InterruptedException {

        onView(withId(R.id.email)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("phonepe"), closeSoftKeyboard());

        onView(allOf(withId(R.id.submit), withChild(withText("Test me")))).perform(click());

        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
    }

    @Workshop
    @Test
    public void test_Empty_Password_Error() throws InterruptedException {
        onView(withId(R.id.email)).perform(typeText("test@test.com"));
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        Log.i("Worshop", "My custom log");
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void test_Set_Mismatch_Error() throws InterruptedException {

        onView(withId(R.id.email)).perform(typeText("espresso@spoon.com"));
        onView(withId(R.id.password)).perform(typeText("bananacake"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        onView(withText(R.string.msg_mismatch)).check(matches(isDisplayed()));
    }

    @Test
    public void test_Set_Correct_Cred() throws InterruptedException {

        onView(withId(R.id.email)).perform(typeText("phonepe@test.com"));
        onView(withId(R.id.password)).perform(typeText("phonepe"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());
        Thread.sleep(3000);
        onView(withText("Welcome! Everyone!")).check(matches(isDisplayed()));
    }

    @Test
    public void test_Set_Correct_Cred_failure() throws InterruptedException {

        onView(withId(R.id.email)).perform(typeText("phonepe@test.com"));
        onView(withId(R.id.password)).perform(typeText("phonepe1"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());
        Thread.sleep(3000);
        onView(withText("Welcome! Everyone!")).check(matches(isDisplayed()));
    }

    public static Activity getActivity() {
        final Activity[] currentActivity = new Activity[1];
        onView(allOf(withId(android.R.id.content), isDisplayed())).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view.getContext() instanceof Activity) {
                    Activity activity1 = ((Activity) view.getContext());
                    currentActivity[0] = activity1;
                }
            }
        });
        return currentActivity[0];
    }


}
