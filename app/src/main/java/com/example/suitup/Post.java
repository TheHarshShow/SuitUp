package com.example.suitup;

import java.util.ArrayList;

public class Post {

    public String documentId;
    public String username;
    public ArrayList<String> liked;
    public String price;
    public String image_url;
    public String user_id;

    public Post(String documentId, String username, ArrayList<String> liked, String price, String image_url, String user_id) {
        this.documentId = documentId;
        this.username = username;
        this.liked = liked;
        this.price = price;
        this.image_url = image_url;
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLiked(ArrayList<String> liked) {
        this.liked = liked;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<String> getLiked() {
        return liked;
    }

    public String getPrice() {
        return price;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
