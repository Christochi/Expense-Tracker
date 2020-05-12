package com.example.android.expensetracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IncomeActivity extends AppCompatActivity {

    // firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    // instances of views
    EditText amount_income;
    Spinner period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        // instance of firebase database
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // reference for the getReference in the database and child reference
        // that will pass the path
        mDatabaseReference = mFirebaseDatabase.getReference().child( "incomestore");

        //reference to views
        amount_income = findViewById(R.id.amount_income);
        period = findViewById(R.id.period);

        // for back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
    }

    // to write to the db by saving and back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //switch statement for if the button was pressed
        switch (item.getItemId())
        {
            case R.id.save_menu:
                saveDeal();
                // LENGTH_LONG is duration
                Toast.makeText(this, "Income Saved", Toast.LENGTH_LONG).show();
                clean(); // reset content of edit text after content has been sent to database
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //menu inflater object can create menu from xml resource
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    private void saveDeal()
    {
        //read content of views

        String amt = amount_income.getText().toString();
        String p = period.getSelectedItem().toString();

        //creating instance of TravelDeal
        IncomeStore income = new IncomeStore( amt, p );
        //to insert new object into the db, we need to call the push method
        mDatabaseReference.push().setValue(income);
    }

    // reset content of views after content has been sent to database
    private void clean()
    {
        amount_income.setText("");
        period.setSelection( 0 );
        amount_income.requestFocus();
    }
}
