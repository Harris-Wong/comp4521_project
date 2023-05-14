package com.example.comp4521_project;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

public class Bill {

    private String title;
    private Double total;
    private String paidBy;
    private Mode mode;
    private Instant createInstant;
    private HashMap<String, Double> debts;

    public Bill() {
        title = "";
        total = 0.0;
        paidBy = "";
        mode = Mode.EVENLY;
        createInstant = Instant.now();
        debts = new HashMap<String, Double>();
    }

    public Bill(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Instant.class, new InstantAdapter())
                .create();
        Bill bill = gson.fromJson(json, Bill.class);
        this.title = bill.title;
        this.total = bill.total;
        this.paidBy = bill.paidBy;
        this.mode = bill.mode;
        this.createInstant = bill.createInstant;
        this.debts = bill.debts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Double getTotalIn(Currency currency) {
        return CurrencyConverter.hkdTo(currency, this.total);
    }

    public void setTotalFrom(Currency currency, Double newTotal) {
        this.total = CurrencyConverter.toHKD(currency, newTotal);
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String username) {
        this.paidBy = username;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode newMode) {
        this.mode = newMode;
    }

    public Instant getCreateInstant() {
        return createInstant;
    }

    public void setCreateInstant(Instant newInstant) {
        this.createInstant = newInstant;
    }

    public HashMap<String, Double> getDebts() {
        return debts;
    }

    public void setDebts(HashMap<String, Double> newDebts) {
        this.debts = newDebts;
    }

    public Double getDebtBetween(String currentUsername, String anotherUsername) {
        if (currentUsername.equals(anotherUsername)) {
            return 0.0;
        } else if (currentUsername.equals(paidBy)) {
            for (String name : debts.keySet()) {
                if (currentUsername.equals(name))
                    continue;
                if (anotherUsername.equals(name)) {
                    return debts.get(name);
                }
            }
        } else if (anotherUsername.equals(paidBy)) {
            for (String name : debts.keySet()) {
                if (currentUsername.equals(name)) {
                    return -1 * debts.get(name);
                }
            }
        }
        return 0.0;
    }

    public Double getLent(String currentUsername) {
        Double lent = 0.0;
        if (paidBy.equals(currentUsername)) {
            for (String name : debts.keySet()) {
                if (!name.equals(currentUsername)) {
                    lent += debts.get(name);
                    Log.d("log", "name " + name + " username " + currentUsername + " debt " + debts.get(name));
                }
            }
        }
        return lent;
    }

    public Double getBorrowed(String currentUsername) {
        Double borrowed = 0.0;
        if (!paidBy.equals(currentUsername)) {
            for (String name : debts.keySet()) {
                if (name.equals(currentUsername)) {
                    borrowed += debts.get(name);
                }
            }
        }
        return borrowed;
    }

    public String[] getPeople() {
        return debts.keySet().toArray(new String[debts.keySet().size()]);
    }

    public String historyFrom(Instant now, Instant before) {
        Duration duration = Duration.between(before, now);
        long hours = duration.toHours();

        if (hours < 24) {
            return String.valueOf(hours) + "h ago";
        } else {
            long days = hours / 24;
            return days + "d ago";
        }
    }

    public String toJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Instant.class, new InstantAdapter())
                .create();
        return gson.toJson(this);
    }
}

/*
            mode: EVENLY
            paidBy: "Keane"
            total: 100
            debt: {
                "Keane": 33.3,
                "Amy": 33.3,
                "Tom": 33.3
            }

            mode: EVENLY
            paidBy: "Amy"
            total: 100
            debt: {
                "Amy": 33.3,
                "Keane": 33.3,
                "Tom": 33.3
            }

            mode: INDIVIDUALLY
            paidBy: "Keane"
            total: 100
            debt: {
                "Keane": 20.0
                "Amy": 30.0,
                "Tom": 50.0
            }

         */

