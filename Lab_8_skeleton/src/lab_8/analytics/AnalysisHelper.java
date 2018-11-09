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
        int maxid = 0;
        for (int id : userLikecount.keySet()) {
            if (userLikecount.get(id) > max) {
                max = userLikecount.get(id);
                maxid = id;
            }
        }
        System.out.println("User with most likes: " + max + "\n" + users.get(maxid));
    }

    public void getFiveMostLikedComment() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        Collections.sort(commentList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o2.getLikes() - o1.getLikes();
            }
        });
        System.out.println("5 most liked commets : ");
        for (int i = 0; i < commentList.size() && i < 5; i++) {
            System.out.println(commentList.get(i));
        }
    }

    public void inactiveUserComments() {
        Map<Integer, Integer> userLikecount = new HashMap<>();
        Map<Integer, User> users = DataStore.getInstance().getUsers();
        for (User user : users.values()) {
            int comments = 0;
            for (Comment c : user.getComments()) {
                comments++;
            }
            userLikecount.put(user.getId(), comments);
        }
        List<Map.Entry<Integer, Integer>> userList = new LinkedList<Map.Entry<Integer, Integer>>(userLikecount.entrySet());
        Collections.sort(userList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()) - (o2.getValue());
            }
        });
        System.out.println("5 most inactive Users as per Comments: ");
        for (int i = 0; i < userList.size() && i < 5; i++) {
            System.out.println("User id :" + userList.get(i));
        }
    }

    public void inactiveUserComPosLikes() {
        Map<Integer, Integer> userCount = new HashMap<>();
        Map<Integer, User> users = DataStore.getInstance().getUsers();
        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        for (User user : users.values()) {
            int comment = 0;
            int post = 0;
            for (Comment c : user.getComments()) {
//                if (c.getUserId() == p.getUserId()) {
                comment++;
            }
            for (Post p : posts.values()) {
                if (p.getUserId() == user.getId()) {
                    post++;
                }
            }
//            userCount.put(user.getId(), count);
        }
        List<Integer> commentList = new ArrayList<>(userCount.values());
        Collections.sort(commentList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println("5 most inactive Users as per comments & Posts : ");
        for (int i = 0; i < commentList.size() && i < 5; i++) {
            System.out.println("User id :" + commentList.get(i));
        }
    }
}
