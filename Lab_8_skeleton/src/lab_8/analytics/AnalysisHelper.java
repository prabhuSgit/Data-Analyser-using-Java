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
        Map<Integer, Integer> userLikecount = new HashMap<Integer, Integer>();
        Map<Integer, User> users = DataStore.getInstance().getUsers();
        System.out.println("---------------------------------------------------------------------------------------");
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
        int maxId = 0;
        for (int id : userLikecount.keySet()) {
            if (userLikecount.get(id) > max) {
                max = userLikecount.get(id);
                maxId = id;
            }
        }
        System.out.println("User with most likes: " + max + "\n" + users.get(maxId));
        System.out.println("---------------------------------------------------------------------------------------");
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
        System.out.println("5 most liked comments :");
        for (int i = 0; i < commentList.size() && i < 5; i++) {
            System.out.println(commentList.get(i));
        }
    }

    public void getAverage() {

        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        Map<Integer, Integer> userLikecount = new HashMap<Integer, Integer>();
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        int count = 0;

        int liked = 0;
        int totalcomm= 0;
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Average number of likes per comment: ");
        for (Post post : posts.values()) {
            //System.out.println("comment count: " + post.getComments());
            for (Comment c : post.getComments()) {
              //  System.out.println("post id: " + post.getPostId());
                int likes = 0;
                likes += c.getLikes();
                userLikecount.put(post.getPostId(), likes);
                //System.out.println("likes :" + likes);
                liked += likes;
                totalcomm++;
            }
        }
        for (int i = 0; i < commentList.size(); i++) {
            count++;
        }
        System.out.println("Commments :" + count);
        System.out.println("Likes :" + liked);
        System.out.println("average :" + (liked / count));
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public void getPostMostLikedComment() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        System.out.println("---------------------------------------------------------------------------------------");
        
        Collections.sort(commentList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                //so as to get decending list
                return o2.getLikes() - o1.getLikes();
            }
        });
        System.out.println("Post with most liked comments :");
        for (int i = 0; i < commentList.size() && i < 1; i++) {
            System.out.println(commentList.get(i));
        }
    }
}
