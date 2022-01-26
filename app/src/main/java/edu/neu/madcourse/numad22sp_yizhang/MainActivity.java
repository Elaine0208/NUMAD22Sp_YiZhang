package edu.neu.madcourse.numad22sp_yizhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ShowMsg(View view){
        Toast.makeText(getApplicationContext(), "Yi Zhang: zhang.yi15@northeastern.edu", Toast.LENGTH_LONG).show();
    }
}