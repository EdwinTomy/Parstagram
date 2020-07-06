package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Heroku server accessed
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("edwinparstagram") //APP_ID env variable
                .clientKey(null)
                .server("https://edwinparstagram.herokuapp.com/parse/").build());
    }
}
