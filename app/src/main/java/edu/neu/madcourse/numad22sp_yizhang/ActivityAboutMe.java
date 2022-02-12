package edu.neu.madcourse.numad22sp_yizhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityAboutMe extends AppCompatActivity {
    private TextView aboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        aboutMe = (TextView) findViewById(R.id.aboutMe);
    }
}