package com.example.parstagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_IMAGE = "Image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";

    //Getter and setter methods to obtain Post data
    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String Description){
        put(KEY_DESCRIPTION, Description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }

    public Date getCreated() {
        return getDate(KEY_CREATED_AT);
    }

    public void setCreatedAt(Date createdAt){
        put(KEY_CREATED_AT, createdAt);
    }
}
