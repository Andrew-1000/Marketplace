package com.example.marketplace;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertTrue;
import static org.robolectric.Robolectric.setupActivity;


@Config(sdk = Build.VERSION_CODES.O_MR1, manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity mainActivity;
    private StartActivity startActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = setupActivity(MainActivity.class);
        startActivity = setupActivity(StartActivity.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void validatesQuestiontoUser(){
        TextView questionToUser = mainActivity.findViewById(R.id.yourname);
        assertTrue("How can we call you?".equals(questionToUser.getText().toString()));
    }

    @Test
    public void newActivityStartsWhenProceedIsPressed(){
        mainActivity.findViewById(R.id.btnProceed).performClick();
        Intent expectedIntent = new Intent(mainActivity, StartActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @Test
    public void buyButtonInStartActivityOpensAJumiaLinkInABrowser(){
        startActivity.findViewById(R.id.buy).performClick();
        Intent expectedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jumia.co.ke/" ));
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(startActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}