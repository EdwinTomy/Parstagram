package com.example.parstagram;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.parstagram.adapters.PostsAdapter;
import com.example.parstagram.models.Post;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static java.security.AccessController.getContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.parstagram", appContext.getPackageName());
    }

    //Add test cases for date formatting
    //Test case for future time
    @Test
    public void timeFormattingFuture(){
        Date date = new Date(120, 6, 12);

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PostsAdapter postsAdapter = new PostsAdapter(appContext, null);
        String newDate = postsAdapter.getRelativeTimeAgo(date);
        System.out.println(newDate);
        assertEquals("In 2 days", newDate);
    }

    //Test case for yesterday
    @Test
    public void timeFormattingYesterday(){
        Date date = new Date(120, 6, 9);

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PostsAdapter postsAdapter = new PostsAdapter(appContext, null);
        String newDate = postsAdapter.getRelativeTimeAgo(date);
        System.out.println(newDate);
        assertEquals("Yesterday", newDate);
    }

    //Test case for hours
    @Test
    public void timeFormattingHours(){
        Date date = new Date(120, 6, 10);

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PostsAdapter postsAdapter = new PostsAdapter(appContext, null);
        String newDate = postsAdapter.getRelativeTimeAgo(date);
        System.out.println(newDate);
        assertEquals("15 hours ago", newDate);
    }

    //Test case for long time ago
    @Test
    public void timeFormattingLongTime(){
        Date date = new Date(119, 6, 10);

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PostsAdapter postsAdapter = new PostsAdapter(appContext, null);
        String newDate = postsAdapter.getRelativeTimeAgo(date);
        System.out.println(newDate);
        assertEquals("Jul 10, 2019", newDate);
    }

}