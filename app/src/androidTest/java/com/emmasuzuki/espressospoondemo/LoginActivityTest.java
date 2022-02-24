package com.emmasuzuki.espressospoondemo;

import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity activity;

    @Test
    public void testInvalidEmail() throws InterruptedException {
        activity = activityActivityTestRule.getActivity();

        onView(withId(R.id.email)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("phonepe"));

        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
        Thread.sleep(5000);

    }


}
