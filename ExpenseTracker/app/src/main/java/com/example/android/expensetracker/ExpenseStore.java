package com.example.android.expensetracker;

public class ExpenseStore {
    private String name;
    private String amount;
    private String category;
    private String month;
    private String Id;

    public ExpenseStore( String name, String amount, String category, String month )
    {
        //this.setId(Id);
        this.setName(name);
        this.setAmount(amount);
        this.setCategory(category);
        this.setMonth(month);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
