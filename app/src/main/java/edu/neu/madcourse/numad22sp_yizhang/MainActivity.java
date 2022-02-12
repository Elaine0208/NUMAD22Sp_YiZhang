package edu.neu.madcourse.numad22sp_yizhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonClicky;
    private Button buttonAboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClicky = (Button) findViewById(R.id.button2);
        buttonClicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openClickyClicky();
            }
        });

        buttonAboutMe = (Button) findViewById(R.id.button);
        buttonAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutMe();
            }
        });

    }

    public void openClickyClicky() {
        Intent intent = new Intent(this, ActivityClicky.class);
        startActivity(intent);
    }

    public void openAboutMe() {
        Intent intent = new Intent(this, ActivityAboutMe.class);
        startActivity(intent);
    }
}