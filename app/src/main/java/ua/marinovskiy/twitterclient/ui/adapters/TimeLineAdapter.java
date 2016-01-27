package ua.marinovskiy.twitterclient.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.marinovskiy.twitterclient.R;
import ua.marinovskiy.twitterclient.models.db.Tweet;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {

    private List<Tweet> mTweetList;

    public TimeLineAdapter(List<Tweet> tweetList) {
        mTweetList = tweetList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.tweet_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setTweet(mTweetList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTweetList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_tweet_avatar)
        ImageView mIvAvatar;

        @Bind(R.id.tv_tweet_owner)
        TextView mTvOwner;

        @Bind(R.id.tv_tweet_date)
        TextView mTvDate;

        @Bind(R.id.tv_tweet_message)
        TextView mTvMessage;

        @Bind(R.id.tv_tweet_retweets)
        TextView mTvReTweets;

        @Bind(R.id.tv_tweet_favorites)
        TextView mTvFavorites;

        Tweet tweet;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setTweet(Tweet tweet) {
            this.tweet = tweet;
            mTvMessage.setText(tweet.getMessage());
        }
    }
}
