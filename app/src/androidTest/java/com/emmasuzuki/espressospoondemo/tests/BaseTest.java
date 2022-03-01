package com.emmasuzuki.espressospoondemo.tests;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.emmasuzuki.espressospoondemo.LoginActivity;
import com.squareup.spoon.SpoonRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static android.Manifest.permission.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.rule.GrantPermissionRule.grant;
import static org.hamcrest.Matchers.*;

public class BaseTest {

    private Activity mActivity;

    @ClassRule
    public static ActivityTestRule<LoginActivity> loginActivityActivityTestRule;

    @Rule
    public GrantPermissionRule permissionRule =
            grant(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE);

    @Rule
    public GrantPermissionRule mRuntimePermissionRule = grant(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE);

    @Rule
    public SpoonRule spoonRule = new SpoonRule();

    @Rule
    public TestRule testWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            spoonRule.screenshot(getActivity(), "Test_failed");
        }
    };

    @Before
    public void launch() {
        loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
        mActivity = loginActivityActivityTestRule.launchActivity(new Intent());
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
