package com.example.marketplace;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.marketplace.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);



    @Test
    public void appUsesAppContext() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.marketplace", context.getPackageName());
    }
    @Test
    public void validateEditTextWhereUserProvidesName() {
        onView(withId(R.id.edtusername)).perform(typeText("Allan")).check(matches(withText("Allan")));
    }

    @Test
    public void newUserIsSentToTheStartActivity() {
        String newUser = "Allan";
        onView(withId(R.id.edtusername)).perform(typeText(newUser)).perform(closeSoftKeyboard());
        try {
            Thread.sleep(300);

        }catch (InterruptedException ex) {
            System.out.println("Process was interrupted");
        }
        onView(withId(R.id.btnProceed)).perform(click());
        onView(withId(R.id.welcome)).check(matches(withText("Hi "  + newUser )));
    }


}
