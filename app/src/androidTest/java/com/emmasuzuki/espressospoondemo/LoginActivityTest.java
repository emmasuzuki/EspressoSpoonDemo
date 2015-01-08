package com.emmasuzuki.espressospoondemo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.squareup.spoon.Spoon;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by emmasuzuki on 7/25/14.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testSetEmailInvalidError() {
        Spoon.screenshot(getActivity(), "initial_state");

        // When view is hidden by keyboard perform("action") fails to do action.

        // Ideally Espresso.closeSoftKeyboard() after eash perform is called automatically but
        // has a bug and sometimes does not close keyboard. "\n" for every typeText is a workaround
        onView(withId(R.id.email)).perform(typeText("test\n"));
        onView(withId(R.id.password)).perform(typeText("lemoncake\n"));

        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.email)).check(matches(hasErrorText(getActivity().getString(R.string.msg_email_error))));

        Spoon.screenshot(getActivity(), "after_login");
    }

    public void testSetPasswordInvalidError() {
        Spoon.screenshot(getActivity(), "initial_state");

        onView(withId(R.id.email)).perform(typeText("test@test.com\n"));
        onView(withId(R.id.password)).perform(typeText("\n"));

        onView(withId(R.id.submit)).perform(click());

        Spoon.screenshot(getActivity(), "after_login");

        onView(withId(R.id.password)).check(matches(hasErrorText(getActivity().getString(R.string.msg_password_error))));
    }

    public void testSetMismatchError() {
        Spoon.screenshot(getActivity(), "initial_state");

        onView(withId(R.id.email)).perform(typeText("espresso@spoon.com\n"));
        onView(withId(R.id.password)).perform(typeText("bananacake\n\n"));

        onView(withId(R.id.submit)).perform(click());

        Spoon.screenshot(getActivity(), "after_login");

        onView(withText(R.string.msg_mismatch)).check(matches(isDisplayed()));
    }

    private static Matcher<? super View> hasErrorText(String expectedError) {
        return new ErrorTextMatcher(expectedError);
    }

    /**
     * Custom matcher to assert equal EditText.setError();
     */
    private static class ErrorTextMatcher extends TypeSafeMatcher<View> {

        private final String mExpectedError;

        private ErrorTextMatcher(String expectedError) {
            mExpectedError = expectedError;
        }

        @Override
        public boolean matchesSafely(View view) {
            if(!(view instanceof EditText)) {
                return false;
            }

            EditText editText = (EditText) view;

            return mExpectedError.equals(editText.getError());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with error: " + mExpectedError);
        }
    }
}
