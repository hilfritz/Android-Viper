package com.hilfritz.android.viper.ui.home.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.data.eventbus.BackButtonEvent;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        BackButtonEvent.fireBackButtonPressedEvent();
    }
}
