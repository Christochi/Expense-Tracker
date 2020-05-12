package com.example.android.expensetracker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    public static FirebaseUtil firebaseUtil;
    public static ArrayList<ExpenseStore> myExp;
    public static ArrayList<IncomeStore> myInc;

    //private constructor to prevent it from being instantiated from outside
    private FirebaseUtil()
    {}

    public static void openFBReference( String ref)
    {
        if (firebaseUtil == null )
        {
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }
        myExp = new ArrayList<ExpenseStore>();
        myInc = new ArrayList<IncomeStore>();
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }
}
