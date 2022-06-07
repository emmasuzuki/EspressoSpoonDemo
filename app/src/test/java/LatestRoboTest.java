import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.emmasuzuki.espressospoondemo.HomeActivity;
import com.emmasuzuki.espressospoondemo.LoginActivity;
import com.emmasuzuki.espressospoondemo.R;

import static androidx.test.core.app.ActivityScenario.launch;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class LatestRoboTest {

    @Test
    public void robot_test_Set_Correct_Cred() throws InterruptedException {
        ActivityScenario<LoginActivity> scenario = launch(LoginActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.moveToState(Lifecycle.State.RESUMED);

        onView(withId(R.id.email)).perform(typeText("phonepe@test.com"));
        onView(withId(R.id.password)).perform(typeText("phonepe"));
        onView(withId(R.id.submit)).perform(click());
    }
}
