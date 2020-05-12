package com.example.android.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // This method goes to expense activity when the expense button is pressed
    public void expensePage(View view)
    {
        Intent expense = new Intent(this, ExpenseActivity.class);
        startActivity(expense);
    }

    // This method goes to income activity when the income button is pressed
    public void incomePage(View view)
    {
        Intent income = new Intent(this, IncomeActivity.class);
        startActivity(income);
    }

    // This method goes to history activity when the history button is pressed
    public void historyPage(View view)
    {
        Intent history = new Intent(this, HistoryActivity.class);
        startActivity(history);
    }

    // This method goes to history activity when the history button is pressed
    public void chartPage(View view)
    {
        Intent chart = new Intent(this, ChartActivity.class);
        startActivity(chart);
    }


}


