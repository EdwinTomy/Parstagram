package com.example.parstagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    public static final String TAG = "PostsAdapter";
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    //Binds view into a specific position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvTime;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super((itemView));
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTime = itemView.findViewById(R.id.tvTime);
            itemView.setOnClickListener(this);
            visibleChange();
        }

        public void bind(Post post) {
            //Bind post data into view elements
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }
            tvUsername.setText(post.getUser().getUsername());

            tvDescription.setText(post.getDescription());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = post.getCreatedAt();
            tvTime.setText(dateFormat.format(date));
            //Log.i(TAG, "time is " + post.getCreatedAt());


        }

        @Override
        public void onClick(View view) {
            //item position
            int position = getAdapterPosition();

            //Validity of position
            if(position != RecyclerView.NO_POSITION){

                //Get movie at position
                Post post = posts.get(position);
               visibleChange();

            }
        }

        public void visibleChange(){

            if(tvDescription.getVisibility() == View.VISIBLE){
                tvDescription.setVisibility(View.GONE);
                tvTime.setVisibility(View.GONE);
                Log.i(TAG, "invisble");
            }else{
                tvDescription.setVisibility(View.VISIBLE);
                tvTime.setVisibility(View.VISIBLE);
                Log.i(TAG, "again visible");
            }

        }
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }
}
