package com.example.android.expensetracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    // instance variables
    ArrayList<ExpenseStore> myExpense;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener; // listens to when an item is added and shows it to user


    public ExpenseAdapter()
    {
        FirebaseUtil.openFBReference("expensestore");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        this.myExpense = FirebaseUtil.myExp;
        mChildListener = new ChildEventListener()
        {

            // Once is the activity is loaded, ev ery item in the db will trigger this event
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                // dataSnapshot are immutable copies of the data in the db location.
                // They are passed to the method in listeners
                // through getValue method, data is serialized and put in the traveldeal class
                ExpenseStore exp = dataSnapshot.getValue(ExpenseStore.class);
                Log.d("Expense: ", exp.getName() );
                exp.setId(dataSnapshot.getKey());
                myExpense.add(exp);
                notifyItemInserted(myExpense.size()-1);
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
    }
    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       Context context = parent.getContext();
       View itemView = LayoutInflater.from(context)
               .inflate(R.layout.rv_row, parent, false);
        return new ExpenseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
       ExpenseStore exp = myExpense.get(position);
       holder.bind(exp);
    }

    @Override
    public int getItemCount() {
        return myExpense.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder
    {
        TextView expTitle;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            expTitle = (TextView) itemView.findViewById(R.id.expTitle);
        }

        public void bind(ExpenseStore expense)
        {
            expTitle.setText(expense.getName());
        }


    }


}
