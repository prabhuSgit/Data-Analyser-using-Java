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
    //Lab
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
    
    //Lab
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
            System.out.println("User id :" + userList.get(i) + "<-Total Comments");
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
                comment++;
            }
            for (Post p : posts.values()) {
                if (p.getUserId() == user.getId()) {
                    post++;
                }
            }
            int totCount = comment + post;
            userCount.put(user.getId(), totCount);
        }
        List<Map.Entry<Integer, Integer>> userList = new LinkedList<Map.Entry<Integer, Integer>>(userCount.entrySet());
        Collections.sort(userList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()) - (o2.getValue());
            }
        });
        System.out.println("5 most inactive Users as per Comments & Posts : ");
        for (int i = 0; i < userList.size() && i < 5; i++) {
            System.out.println("User id :" + userList.get(i) + "<-Comments+Posts");
        }
    }
    
    public void proactiveUserComPosLikes() {
        Map<Integer, Integer> userCount = new HashMap<>();
        Map<Integer, User> users = DataStore.getInstance().getUsers();
        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        for (User user : users.values()) {
            int comment = 0;
            int post = 0;
            for (Comment c : user.getComments()) {
                comment++;
            }
            for (Post p : posts.values()) {
                if (p.getUserId() == user.getId()) {
                    post++;
                }
            }
            int totCount = comment + post;
            userCount.put(user.getId(), totCount);
        }
        List<Map.Entry<Integer, Integer>> userList = new LinkedList<Map.Entry<Integer, Integer>>(userCount.entrySet());
        Collections.sort(userList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o2.getValue()) - (o1.getValue());
            }
        });
        System.out.println("5 most proactive Users as per Comments & Posts : ");
        for (int i = 0; i < userList.size() && i < 5; i++) {
            System.out.println("User id :" + userList.get(i) + "<-Comments+Posts");
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

        System.out.println("No of most comments: " + max + "\n" + "PostID:" + posts.get(maxID));

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
