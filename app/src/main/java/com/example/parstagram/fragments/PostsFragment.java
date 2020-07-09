package com.example.parstagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parstagram.Post;
import com.example.parstagram.PostsAdapter;
import com.example.parstagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    final private static String TAG = "PostsFragment";

    private RecyclerView rvPosts;
    protected SwipeRefreshLayout swipeContainer;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;

    public PostsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);

        // Lookup the swipe container view
        swipeContainer = view.findViewById(R.id.swipeContainer);
        refreshTimeline(swipeContainer);

        //Create adapter and data source
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        //Create layout for one row in the list


        //Set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        //Set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();


    }

    //Refresh timeline
    public void refreshTimeline(SwipeRefreshLayout swipeContainer){
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "Loading in");
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                queryPosts();
            }
        });
    }

    //Retrieving ParseObject
    protected void queryPosts() {

        //Object to be queried (Post)
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED);
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
                adapter.clear();
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
        swipeContainer.setRefreshing(false);
    }
}