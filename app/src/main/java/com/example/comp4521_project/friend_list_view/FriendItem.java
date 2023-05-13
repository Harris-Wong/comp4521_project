package com.example.comp4521_project.friend_list_view;

public class FriendItem {

    String friendName;
    String oweMe;

    public FriendItem(String friendName, String oweMe) {
        this.friendName = friendName;
        this.oweMe = oweMe;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getOweMe() {
        return oweMe;
    }

    public void setOweMe(String oweMe) {
        this.oweMe = oweMe;
    }
}
