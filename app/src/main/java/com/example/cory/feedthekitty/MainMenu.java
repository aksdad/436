package com.example.cory.feedthekitty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button createEvent = (Button) findViewById(R.id.create_event_button);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, TestActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button ongoingEvents = (Button) findViewById(R.id.ongoing_event_button);
        ongoingEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, OngoingEvents.class);
                startActivity(intent);
                finish();
            }
        });

        Button pastEvents = (Button) findViewById(R.id.past_event_button);
        pastEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, PastEvents.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
