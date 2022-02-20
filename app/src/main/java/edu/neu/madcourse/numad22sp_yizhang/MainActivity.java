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

import com.google.android.material.snackbar.Snackbar;

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
        if (ActivityCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this, LocatorActivity.class);
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1]
                == PackageManager.PERMISSION_GRANTED)){
            Intent intent = new Intent(this, LocatorActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
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

