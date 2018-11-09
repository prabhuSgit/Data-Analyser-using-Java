/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_8.analytics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lab_8.entities.Comment;
import lab_8.entities.Post;
import lab_8.entities.User;

/**
 *
 * @author harshalneelkamal
 */
public class AnalysisHelper {

    public void userWithMostLikes() {

        Map<Integer, Integer> userLikecount = new HashMap<>();

        Map<Integer, User> users = DataStore.getInstance().getUsers();
        for (User user : users.values()) {
            for (Comment c : user.getComments()) {
                int likes = 0;
                if (userLikecount.containsKey(user.getId())) {
                    likes = userLikecount.get(user.getId());
                }
                likes += c.getLikes();
                userLikecount.put(user.getId(), likes);
            }
        }

        int max = 0;
        int maxID = 0;
        for (int id : userLikecount.keySet()) {
            if (userLikecount.get(id) > max) {
                max = userLikecount.get(id);
                maxID = id;
            }
        }
        System.out.println("No of most likes: " + max + "\n" + users.get(maxID));
        //System.out.println("User with most likes: " + maxID);
    }

    public void getFiveMostLikedComment() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();

        List<Comment> commentList = new ArrayList<>(comments.values());

        Collections.sort(commentList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                //so as to get decending list
                return o2.getLikes() - o1.getLikes();
            }
        });

        System.out.println("5 most liked comments : ");
        for (int i = 0; i < commentList.size() && i < 5; i++) {
            System.out.println(commentList.get(i));
        }
    }

    public void getPostWithMostComments() {

        Map<Integer, Integer> postCommentcount = new HashMap<Integer, Integer>();

        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        for (Post post : posts.values()) {
            int comments = 0;
            for (Comment c : post.getComments()) {
                comments++;
                postCommentcount.put(post.getPostId(), comments);

            }
        }

        int max = 0;
        int maxID = 0;
        for (int id : postCommentcount.keySet()) {
            if (postCommentcount.get(id) > max) {
                max = postCommentcount.get(id);
                maxID = id;
            }
        }

        System.out.println("No of most comments: " + max + " " + "PostID:" + posts.get(maxID));

    }

    public void getInactiveUsersBasedOnPosts() {
        Map<Integer, Integer> userpostcount = new HashMap<Integer, Integer>();
        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        for (Post p : posts.values()) {
            if (userpostcount.containsKey(p.getUserId())) {
                int count = userpostcount.get(p.getUserId());
                userpostcount.put(p.getUserId(), count + 1);
            } else {
                userpostcount.put(p.getUserId(), 1);
            }

        }

        List<Map.Entry<Integer, Integer>> postList
                = new LinkedList<Map.Entry<Integer, Integer>>(userpostcount.entrySet());

        Collections.sort(postList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                    Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()) - (o2.getValue());
            }
        });
        System.out.println("5 most inactive Users as per posts: ");
        for (int i = 0; i < postList.size() && i < 5; i++) {
            System.out.println("User id :" + postList.get(i));
        }
    }
}
