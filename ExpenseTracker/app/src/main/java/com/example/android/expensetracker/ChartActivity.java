package com.example.android.expensetracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


public class ChartActivity extends AppCompatActivity {

    // instance variables
    private int month;
    private static ArrayList<ExpenseStore> expenses;

    // firebase instances
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener; // listens to when an item is added and shows it to user

    public ChartActivity() {
        expenses = new ArrayList<ExpenseStore>();
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        this.expenses = FirebaseUtil.myExp;
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // dataSnapshot are immutable copies of the data in the db location.
                // They are passed to the method in listeners
                // through getValue method, data is serialized and put in the ExpenseStore class
                ExpenseStore exp = dataSnapshot.getValue(ExpenseStore.class);
                Log.d("Expense: ", exp.getAmount() );
                //exp.setId(dataSnapshot.getKey());
                expenses.add(exp);
                //notifyItemInserted(expenses.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);
        try {
            generateChart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

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
            //case R.id.save_menu:
                //saveDeal();
                // LENGTH_LONG is duration
                //Toast.makeText(this, "Income Saved", Toast.LENGTH_LONG).show();
                //clean(); // reset content of edit text after content has been sent to database
                //return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private static void generateChart() throws IOException {
        double total[] = new double[17];
        for (ExpenseStore exp : expenses) {
            if (exp.getCategory().equals("Clothing")) {
                total[0] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Drinks")) {
                total[1] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Education")) {
                total[2] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Grocery")) {
                total[3] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Fuel")) {
                total[4] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Fun")) {
                total[5] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Medical")) {
                total[6] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Hotel")) {
                total[7] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Loan")) {
                total[8] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Movie")) {
                total[9] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Pets")) {
                total[10] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Other")) {
                total[11] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Restaurant")) {
                total[12] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Salary")) {
                total[13] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Shopping")) {
                total[14] += Double.valueOf(exp.getAmount());
            }else if (exp.getCategory().equals("Tips")) {
                total[15] += Double.valueOf(exp.getAmount());
            } else if (exp.getCategory().equals("Transport")) {
                total[16] += Double.valueOf(exp.getAmount());
            }
        }

        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue("Clothing", total[0]);
        dataset.setValue("Drinks", total[1]);
        dataset.setValue("Education", total[2]);
        dataset.setValue("Grocery", total[3]);
        dataset.setValue("Fuel", total[4]);
        dataset.setValue("Fun", total[5]);
        dataset.setValue("Medical", total[6]);
        dataset.setValue("Hotel", total[7]);
        dataset.setValue("Loan", total[8]);
        dataset.setValue("Movie", total[9]);
        dataset.setValue("Pets", total[10]);
        dataset.setValue("Other", total[11]);
        dataset.setValue("Restaurant", total[12]);
        dataset.setValue("Salary", total[13]);
        dataset.setValue("Shopping", total[14]);
        dataset.setValue("Tips", total[15]);
        dataset.setValue("Transport", total[16]);

        JFreeChart chart = ChartFactory.createPieChart(
                "Expense Breakdown by Type",
                dataset,
                true,
                true,
                false);

        int width = 640;
        int height = 480;
        File pieChart = new File( "ExpenseBreakdown.jpeg" );
        ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
    }

}
