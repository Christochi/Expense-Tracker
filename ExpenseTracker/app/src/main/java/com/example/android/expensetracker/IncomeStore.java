package com.example.android.expensetracker;

public class IncomeStore {
    private String amount;
    private String period;

    public IncomeStore(String amount, String period) {
        this.setAmount(amount);
        this.setPeriod(period);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
