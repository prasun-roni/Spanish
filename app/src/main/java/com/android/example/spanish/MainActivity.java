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

        // Find the View that shows the family category
        TextView family = (TextView) findViewById(R.id.family);

        // Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the family category is clicked on.
            @Override
            public void onClick(View v) {

                // Create a new intent to open the {@link FamilyActivity}
                Intent openFamilyMemberActivity = new Intent(MainActivity.this, FamilyActivity.class);

                // Start the new activity
                startActivity(openFamilyMemberActivity);
            }
        });

        // Find the View that shows the colors category
        TextView colors = (TextView) findViewById(R.id.colors);

        // Set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View v) {

                // Create a new intent to open the {@link ColorsActivity}
                Intent openColorActivity = new Intent(MainActivity.this, ColorActivity.class);

                // Start the new activity
                startActivity(openColorActivity);
            }
        });

        // Find the View that shows the phrases category
        TextView phrases = (TextView) findViewById(R.id.phrases);

        // Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View v) {

                // Create a new intent to open the {@link PhrasesActivity}
                Intent openPhrasesActivity = new Intent(MainActivity.this, PhrasesActivity.class);

                // Start the new activity
                startActivity(openPhrasesActivity);
            }
        });
    }
}