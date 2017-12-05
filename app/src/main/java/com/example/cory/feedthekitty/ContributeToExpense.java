package com.example.cory.feedthekitty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ContributeToExpense extends AppCompatActivity {


    TextView mTitleName, mPrice;
    TextView seekBarValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute_to_expense);

        mTitleName = findViewById(R.id.contribute_header);
        mTitleName.setText(getIntent().getStringExtra("expense_name") );

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(0);
        seekBar.incrementProgressBy(1);
        seekBar.setMax( Integer.parseInt(getIntent().getStringExtra("expense_price")) );
        seekBarValue = (TextView)findViewById(R.id.contribution_counter);
        seekBarValue.setText("0");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress - 1;
                progress = progress + 1;
                seekBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed(){
        ContributeToExpense.this.finish();
    }
    @Override
    protected void onStart(){
        super.onStart();


    }
}
