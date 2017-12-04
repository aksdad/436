package com.example.cory.feedthekitty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContributeToExpense extends AppCompatActivity {


    TextView mTitleName, mPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute_to_expense);

        mTitleName = findViewById(R.id.contribute_header);
        mTitleName.setText(getIntent().getStringExtra("expense_name") );
    }

    @Override
    protected void onStart(){
        super.onStart();


    }
}
