package com.example.comp4521_project.bill_recycler_view;

public class BillItem {

    String title;
    String people;
    String splitMode;
    String total;
    String paidBy;
    String howLongAgo;

    public BillItem(String title, String people, String splitMode, String total, String paidBy, String howLongAgo) {
        this.title = title;
        this.people = people;
        this.splitMode = splitMode;
        this.total = total;
        this.paidBy = paidBy;
        this.howLongAgo = howLongAgo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getSplitMode() {
        return splitMode;
    }

    public void setSplitMode(String splitMode) {
        this.splitMode = splitMode;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getHowLongAgo() {
        return howLongAgo;
    }

    public void setHowLongAgo(String howLongAgo) {
        this.howLongAgo = howLongAgo;
    }
}
