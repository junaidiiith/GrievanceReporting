package com.example.gra.gra;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Created by sachin on 31/3/17.
 */
public class MyProfileActivityTest {
    //Specifies that this activity is launched
    @Rule
    public ActivityTestRule<MyProfileActivity> mActivityTestRule = new ActivityTestRule<MyProfileActivity>(MyProfileActivity.class);

    private MyProfileActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.bUpdateProfile);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}