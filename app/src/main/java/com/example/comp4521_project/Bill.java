package com.example.comp4521_project;

import java.time.Instant;
import java.util.HashMap;

public class Bill {

    private String title;
    private Double total;
    private String paidBy;
    private Mode mode;
    private Instant createInstant;
    private HashMap<String, Double> debt;

    public Bill() {
        title = "";
        total = 0.0;
        paidBy = "";
        mode = Mode.EVENLY;
        createInstant = Instant.now();
        debt = new HashMap<String, Double>()
    }

    public String getTitle() {
        return title;
    }

    public Double getTotal() {
        return total;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public Mode getMode() {
        return mode;
    }

    public Instant getCreateInstant() {
        return createInstant;
    }

    public HashMap<String, Double> getDebt() {
        return debt;
    }

    public Double getDebtBetween(String currentUsername, String anotherUsername) {
        if (currentUsername.equals(anotherUsername)) {
            return 0.0;
        } else if (currentUsername.equals(paidBy)) {
            for (String name : debt.keySet()) {
                if (currentUsername.equals(name))
                    continue;
                if (anotherUsername.equals(name)) {
                    return debt.get(name);
                }
            }
        } else if (anotherUsername.equals(paidBy)) {
            for (String name : debt.keySet()) {
                if (currentUsername.equals(name)) {
                    return -1 * debt.get(name);
                }
            }
        }
        return 0.0;
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

