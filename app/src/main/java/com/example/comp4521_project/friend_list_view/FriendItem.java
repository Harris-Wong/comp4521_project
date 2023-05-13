package com.example.comp4521_project.friend_list_view;

public class FriendItem {

    private String friendName;
    private String text;

    public FriendItem(String friendName, String text) {
        this.friendName = friendName;
        this.text = text;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
