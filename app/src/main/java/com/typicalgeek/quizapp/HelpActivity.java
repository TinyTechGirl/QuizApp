package com.typicalgeek.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Email feedback to the developer
                Toast.makeText(HelpActivity.this, "Send Feedback", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));
                String [] s = {"plenjo15@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL, s);
                i.putExtra(Intent.EXTRA_SUBJECT, "Quiz App Feedback");
                i.putExtra(Intent.EXTRA_TEXT, "Hello Developer. \nI have tried your app and this is what I think. \n");

                // Creating a chooser
                Intent chooser = Intent.createChooser(i, "Send Feedback");
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
