package com.android.example.spanish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);

        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View v) {

                // Create a new intent to open the {@link NumbersActivity}
                Intent openNumberActivity = new Intent(MainActivity.this, NumberActivity.class);

                // Start the new activity
                startActivity(openNumberActivity);
            }
        });

        TextView family = (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openFamilyMemberActivity = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(openFamilyMemberActivity);
            }
        });

        TextView colors = (TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openColorActivity = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(openColorActivity);
            }
        });

        TextView phrases = (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openPhrasesActivity = new Intent(MainActivity.this, PhrasesActivity.class);

                startActivity(openPhrasesActivity);
            }
        });
    }
}