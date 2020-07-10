package com.example.parstagram.fragments;

import android.content.Context;
import android.util.Log;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.parstagram.Post;
import com.example.parstagram.PostsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    public static final String TAG = "ProfileFragment";

    @Override
    protected void queryPosts(int postLimit) {

        //Object to be queried (Post)
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(postsLimit);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        //Object id of Post
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                // Access data using the getter methods for the object
                for(Post post : posts){
                    Log.i(TAG, "Post: " + post.getDescription() + ", user: " + post.getUser().getUsername());
                }
                allPosts.clear();
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
