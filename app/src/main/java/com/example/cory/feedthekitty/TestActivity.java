package com.example.cory.feedthekitty;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    /************************************************
     *
     *  THIS IS CREATE EVENT I MESSED UP THE NAME
     */


    EditText mEventName;
    Button mAddExpense, mRemoveExpense, mSubmitEvent;
    RadioButton mPrivateEvent, mPublicEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // get views
        mEventName = (EditText) findViewById(R.id.event_name);
        mAddExpense = (Button) findViewById(R.id.add_expense);
        mRemoveExpense = (Button) findViewById(R.id.remove_expenses);
        mSubmitEvent = (Button) findViewById(R.id.submit);
        mPrivateEvent = (RadioButton) findViewById(R.id.private_event);
        mPublicEvent = (RadioButton) findViewById(R.id.public_event);

        //set listeners
        mAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "ADD EXPENSE", Toast.LENGTH_SHORT).show();
            }
        });
        mRemoveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Remove EXPENSE", Toast.LENGTH_SHORT).show();
            }
        });
        mSubmitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
                int selected = rg.getCheckedRadioButtonId();

                if ((RadioButton)findViewById(selected) == mPrivateEvent){
                    Toast.makeText(getBaseContext(), mPrivateEvent.getText(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(), mPublicEvent.getText(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getBaseContext(), mEventName.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
