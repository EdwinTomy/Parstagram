package com.example.parstagram.network;

import android.app.Application;

import com.example.parstagram.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Register your parse models
        ParseObject.registerSubclass(Post.class);

        //Heroku server accessed
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("edwinparstagram") //APP_ID env variable
                .clientKey(null)
                .server("https://edwinparstagram.herokuapp.com/parse/").build());
    }
}
