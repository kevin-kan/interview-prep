package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
* Implement a simplified version of Twitter which allows users to post tweets, follow/unfollow each other, and view the 10 most recent tweets within their own news feed.
* Users and tweets are uniquely identified by their IDs (integers).
*
* Implement the following methods:
*  Twitter() Initializes the twitter object.
*  void postTweet(int userId, int tweetId) Publish a new tweet with ID tweetId by the user userId. 
*   You may assume that each tweetId is unique.
*  List<Integer> getNewsFeed(int userId) Fetches at most the 10 most recent tweet IDs in the user's news feed. 
*   Each item must be posted by users who the user is following or by the user themself. 
*   Tweets IDs should be ordered from most recent to least recent.
*  void follow(int followerId, int followeeId) The user with ID followerId follows the user with ID followeeId.
*  void unfollow(int followerId, int followeeId) The user with ID followerId unfollows the user with ID followeeId.
* 
* Constraints:
* 1 <= userId, followerId, followeeId <= 100
* 0 <= tweetId <= 1000
*/
class Twitter {
    long timer;
    Map<Integer, List<Tweet>> userToTweet;
    Map<Integer, Set<Integer>> followerFollowee;

    public Twitter() {
        userToTweet = new HashMap<>();
        followerFollowee = new HashMap<>();
        timer = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        userToTweet.putIfAbsent(userId, new ArrayList<>());
        userToTweet.get(userId).add(new Tweet(tweetId, timer));
        timer++;
    }
    
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> feed = new PriorityQueue<>(Comparator.comparingLong((Tweet t) -> t.timestamp));
        Set<Integer> followees = followerFollowee.getOrDefault(userId, new HashSet<>());
        followees.add(userId);
        // Add the followees tweets
        for (int user : followees) {
            List<Tweet> userTweets = userToTweet.getOrDefault(user, new ArrayList<>());
            int count = 0;
            for (int i = userTweets.size()-1; i >= 0; i--) {
                Tweet tweet = userTweets.get(i);
                if (count > 10) {
                    break;
                }
                feed.offer(tweet);
                if (feed.size() > 10) {
                    feed.poll();
                }
                count++;
            }
        }

        List<Integer> output = new ArrayList<>();
        while (!feed.isEmpty()) {
            output.add(feed.poll().tweetId);
        }
        Collections.reverse(output);
        return output;
    }
    
    public void follow(int followerId, int followeeId) {
        followerFollowee.putIfAbsent(followerId, new HashSet<>());
        followerFollowee.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (followerFollowee.get(followerId) != null) {
            followerFollowee.get(followerId).remove(followeeId);
        }
    }

    class Tweet {
        int tweetId;
        long timestamp;

        Tweet(int tweetId, long timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }
}
