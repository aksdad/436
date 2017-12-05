package com.example.cory.feedthekitty;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cory.feedthekitty.models.Event;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.wallet.CardInfo;
import com.google.android.gms.wallet.PaymentData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.stripe.android.model.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {

    /************************************************
     *
     *  THIS IS CREATE EVENT I MESSED UP THE NAME
     */


    EditText mEventName;
    Button mAddExpense, mRemoveExpense, mSubmitEvent;
    RadioButton mPrivateEvent, mPublicEvent;
    ListView mExpenseList;
    ArrayList<String> mListItems = new ArrayList<String>();
    ArrayAdapter<String> mAdapter;
    DatabaseReference mDatabase;
    HashMap<String, Integer> expenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("Create an Event");
        // get views
        mEventName = (EditText) findViewById(R.id.event_name);
        mAddExpense = (Button) findViewById(R.id.add_expense);
        mRemoveExpense = (Button) findViewById(R.id.remove_expenses);
        mSubmitEvent = (Button) findViewById(R.id.submit);
        mPrivateEvent = (RadioButton) findViewById(R.id.private_event);
        mPublicEvent = (RadioButton) findViewById(R.id.public_event);
        mExpenseList = (ListView) findViewById(R.id.listView);
        mAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mListItems);
        mExpenseList.setAdapter(mAdapter);

        expenses = new HashMap<String, Integer>();

        //set listeners
        mAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewExpense();
                //Toast.makeText(getBaseContext(), "ADD EXPENSE", Toast.LENGTH_SHORT).show();
            }
        });
        mRemoveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeExpense();
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
                else if ((RadioButton)findViewById(selected) == mPublicEvent){
                    Toast.makeText(getBaseContext(), mPublicEvent.getText(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Neither button clicked.", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getBaseContext(), mEventName.getText(), Toast.LENGTH_SHORT).show();
                processEvent();
            }
        });
        mExpenseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getBaseContext(), "WORKS", Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar((Toolbar) findViewById(R.id.tool_bar));
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void processEvent() {
        String key = mDatabase.child("events").push().getKey();
        String uid = FirebaseAuth.getInstance().getUid();
        String owner_name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        boolean visibility = false;
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        int selected = rg.getCheckedRadioButtonId();

        if ((RadioButton)findViewById(selected) == mPrivateEvent){
            Toast.makeText(getBaseContext(), mPrivateEvent.getText(), Toast.LENGTH_SHORT).show();
            visibility = true;
        }



        Event event = new Event(key, "", ((EditText) findViewById(R.id.event_name)).getText().toString(), uid, visibility, null, expenses, owner_name);
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/events/" + key, event);
        childUpdates.put("/user-events/"+ uid + "/" + key, event);

        mDatabase.updateChildren(childUpdates);

        TestActivity.this.finish();
        Toast.makeText(getBaseContext(), "Event recorded into database.", Toast.LENGTH_SHORT).show();
    }

    private void addNewExpense(){
        Intent intent = new Intent(this.getApplicationContext(), AddExpense.class);
        startActivityForResult(intent, 666);
    }

    private void removeExpense(){

        Intent intent = new Intent(this.getApplicationContext(), RemoveExpense.class);
        intent.putExtra("list", mListItems);
        startActivityForResult(intent, 777);
    }

    @Override
    public void onBackPressed(){
        Log.d("TestActivity", "We going to the main menu");
//        Intent mIntent = new Intent(TestActivity.this, MainMenu.class);
//        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(mIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777){
            if (resultCode == RESULT_OK){
                ArrayList<String> hold = (ArrayList<String>) data.getSerializableExtra("removeList");
                for (String item : hold){
                    mListItems.remove(item);
                }
                mAdapter.notifyDataSetChanged();
                //Toast.makeText(this.getApplicationContext(), mListItems.size()+"", Toast.LENGTH_SHORT).show();
            }
        }
        else if (RESULT_OK == resultCode){
            if (requestCode == 0) {
                PaymentData paymentData = PaymentData.getFromIntent(data);
                CardInfo info = paymentData.getCardInfo();
                UserAddress address = paymentData.getShippingAddress();
                String rawToken = paymentData.getPaymentMethodToken().getToken();
                Token stripeToken = Token.fromString(rawToken);
            }

            if (data == null){
                Toast.makeText(getBaseContext(), "return intent null", Toast.LENGTH_SHORT).show();
            }
            else {

                //get expense info from intent
                String expenseName = data.getStringExtra("name");
                expenses.put(expenseName, Integer.parseInt(data.getStringExtra("price")));
                Toast.makeText(getBaseContext(), "return intent not null", Toast.LENGTH_SHORT).show();
                mListItems.add(data.getStringExtra("name") + ":" +"\t$"+data.getStringExtra("price"));
                mAdapter.notifyDataSetChanged();
            }
        }
    }

//    public static class DatePickerFragment extends DialogFragment
//            implements DatePickerDialog.OnDateSetListener {
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the current date as the default date in the picker
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//
//            // Create a new instance of DatePickerDialog and return it
//            return new DatePickerDialog(getActivity(), this, year, month, day);
//        }
//
//        public void onDateSet(DatePicker view, int year, int month, int day) {
//            // Do something with the date chosen by the user
//        }
//    }
}
