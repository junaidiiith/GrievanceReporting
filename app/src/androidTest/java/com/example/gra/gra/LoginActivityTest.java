package com.example.gra.gra;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by RAVI on 3/26/2017.
 */
public class LoginActivityTest {

    //Specifies that this activity is launched
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.etMobileNumber);
        assertNotNull(view);
    }

    @Test
    public void testLaunchOfRegisterOnClick(){
        assertNotNull(mActivity.findViewById(R.id.tvRegisterHere));
        onView(withId(R.id.tvRegisterHere)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(registerActivity);
        registerActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}