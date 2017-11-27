package com.example.cory.feedthekitty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddExpense extends AppCompatActivity {


    int hold = 0;
    EditText mExpenseName;
    TextView mMoneySum;
    Button mPlusOne, mPlusFive, mPlusTen, mPlusFifty, mReset, mSubmitExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);


        // get views
        mExpenseName = (EditText) findViewById(R.id.expense_name);

        mMoneySum = (TextView) findViewById(R.id.money_counter);
        mMoneySum.setText("0.00");

        mPlusOne = (Button) findViewById(R.id.one_dollar);
        mPlusFive = (Button) findViewById(R.id.five_dollars);
        mPlusTen = (Button) findViewById(R.id.ten_dollars);
        mPlusFifty = (Button) findViewById(R.id.fifty_dollars);

        mReset = (Button) findViewById(R.id.reset_amount);
        mSubmitExpense = (Button) findViewById(R.id.submit_expense);

        // set click listeners
        mPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hold += 1;
                mMoneySum.setText(""+hold+".00");
                Toast.makeText(getBaseContext(), hold+"", Toast.LENGTH_SHORT).show();
            }
        });

        mPlusFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hold += 50;
                mMoneySum.setText(""+hold+".00");
                Toast.makeText(getBaseContext(), hold+"", Toast.LENGTH_SHORT).show();
            }
        });

        mPlusTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hold += 10;
                mMoneySum.setText(""+hold+".00");
                Toast.makeText(getBaseContext(), hold+"", Toast.LENGTH_SHORT).show();
            }
        });

        mPlusFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hold += 5;
                mMoneySum.setText(""+hold+".00");
                Toast.makeText(getBaseContext(), hold+"", Toast.LENGTH_SHORT).show();
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hold = 0;
                mMoneySum.setText(""+hold+".00");
                Toast.makeText(getBaseContext(), hold+"", Toast.LENGTH_SHORT).show();
            }
        });

        mSubmitExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get name of event + cost and add it to ListView in TestActivity.
                Toast.makeText(getBaseContext(), "Expense recorded!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
