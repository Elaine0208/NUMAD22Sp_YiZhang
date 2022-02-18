package edu.neu.madcourse.numad22sp_yizhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonClicky;
    private Button buttonAboutMe;
    private Button buttonLinkController;
    private Button buttonLocator;

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

        buttonLinkController = (Button) findViewById(R.id.buttonLinkController);
        buttonLinkController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLinkController();
            }
        });

        buttonLocator = (Button) findViewById(R.id.buttonLocator);
        buttonLocator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocator();
            }
        });

    }



    private void openLocator() {
        Intent intent = new Intent(this, LocatorActivity.class);
        startActivity(intent);
    }

    public void openClickyClicky() {
        Intent intent = new Intent(this, ActivityClicky.class);
        startActivity(intent);
    }

    public void openAboutMe() {
        Intent intent = new Intent(this, ActivityAboutMe.class);
        startActivity(intent);
    }

    public void openLinkController() {
        Intent intent = new Intent(this, ActivityLinkController.class);
        startActivity(intent);
    }
}

