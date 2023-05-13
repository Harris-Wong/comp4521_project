package com.example.comp4521_project.bill_recycler_view;

public class BillItem {

    String title;
    String people;
    String mode;
    String total;
    String paidBy;
    String history;

    public BillItem(String title, String people, String mode, String total, String paidBy, String history) {
        this.title = title;
        this.people = people;
        this.mode = mode;
        this.total = total;
        this.paidBy = paidBy;
        this.history = history;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
