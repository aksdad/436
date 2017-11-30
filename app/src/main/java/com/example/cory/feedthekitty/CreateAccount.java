package com.example.cory.feedthekitty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    EditText mDisplayName, mEmailAddress, mPassword;
    Button mSubmitButton;

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mDisplayName = (EditText) findViewById(R.id.display_name_text_view);
        mEmailAddress = (EditText) findViewById(R.id.enter_email_field);
        mPassword = (EditText) findViewById(R.id.enter_password_field);

        mSubmitButton = (Button) findViewById(R.id.submit_create_account);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reset errors.
            }
        });
    }
}
