package com.example.cory.feedthekitty;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;

public class CreditCardActivity extends AppCompatActivity {

    private CardInputWidget mCardInputWidget;
    private Button mSubmitButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);

        mSubmitButton = (Button) findViewById(R.id.button2);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card retrieved = mCardInputWidget.getCard();
                if (retrieved != null) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("Cardholder", retrieved.getName());
                    returnIntent.putExtra("ExpMonth", retrieved.getExpMonth());
                    returnIntent.putExtra("ExpYear", retrieved.getExpYear());
                    returnIntent.putExtra("Cardnumber", retrieved.getNumber());
                    Toast.makeText(getBaseContext(), "Card successfully read", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK,returnIntent);
                    finish();
                }
            }
        });
    }
}
