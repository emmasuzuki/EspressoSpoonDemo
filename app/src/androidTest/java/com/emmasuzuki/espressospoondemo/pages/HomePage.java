package com.emmasuzuki.espressospoondemo.pages;

import androidx.test.espresso.ViewInteraction;

import com.emmasuzuki.espressospoondemo.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class HomePage extends BasePage {

    public ViewInteraction getWelcomeMsg() {
        return onView(withId(R.id.welcomeMsg));
    }
}
