package com.example.gra.gra;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by RAVI on 3/27/2017.
 */
public class HomeActivityTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);
    private HomeActivity mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.bLogComplaint);
        assertNotNull(view);
    }

    @Test
    public void testLaunchOfLogComplaintOnClick(){
        assertNotNull(mActivity.findViewById(R.id.bLogComplaint));
        onView(withId(R.id.bLogComplaint)).perform(click());
        Activity logComplaintActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(logComplaintActivity);
        logComplaintActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}