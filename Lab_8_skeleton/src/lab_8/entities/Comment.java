/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_8.entities;

/**
 *
 * @author harshalneelkamal
 */
public class Comment {
    private int id;
    private int userId;
    private int postId;
    private String text;
    private int likes;
    private int comments;

    public Comment(int id, int userId, int postId, String text, int likes, int comments) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.text = text;
        this.likes = likes;
        this.comments = comments;
    }

    

    public int getLikes() {
        return likes;
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
    

    @Override
    public String toString() {
        return "Comment{" + "id = " + id + ", userId = " + userId + ", postId = " + postId + ", text = " + text + ", likes = " + likes + '}';
    }
    
    
}
