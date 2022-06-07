import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.EditText;

import androidx.test.core.app.ApplicationProvider;

import com.emmasuzuki.espressospoondemo.HomeActivity;
import com.emmasuzuki.espressospoondemo.LoginActivity;
import com.emmasuzuki.espressospoondemo.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;


import static androidx.test.core.app.ActivityScenario.launch;
import static junit.framework.TestCase.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class RoboTest {

    @Test
    public void robot_test_Set_Correct_Cred() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();

        ((EditText) activity.findViewById(R.id.email)).setText("phonepe@test.com");
        ((EditText) activity.findViewById(R.id.password)).setText("phonepe");
        activity.findViewById(R.id.submit).performClick();
        Intent expectedIntent = new Intent(activity, HomeActivity.class);
        Intent actual = Shadows.shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
}
