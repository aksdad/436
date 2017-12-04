package com.example.cory.feedthekitty;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.wallet.CardInfo;
import com.google.android.gms.wallet.PaymentData;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import java.util.ArrayList;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
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

    //todo create backstack to retrieve info from expense form
    private void addNewExpense(){
        Intent intent = new Intent(this.getApplicationContext(), AddExpense.class);

//        TaskStackBuilder tsb = TaskStackBuilder.create(this);
//        tsb.addParentStack(AddExpense.class);
//        tsb.addNextIntent(intent);
//        PendingIntent thingy = tsb.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentIntent(thingy);
        startActivityForResult(intent, 666);

        //finish();
    }

    @Override
    public void onBackPressed(){
        Log.d("TestActivity", "We going to the main menu");
        Intent mIntent = new Intent(TestActivity.this, MainMenu.class);
//        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode){
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
                Toast.makeText(getBaseContext(), "return intent not null", Toast.LENGTH_SHORT).show();
                mListItems.add(data.getStringExtra("name") + ":" +"\t$"+data.getStringExtra("price"));
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
