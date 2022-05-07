package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class WeatherActivity extends AppCompatActivity {

    private RadioGroup city_buttons;
    private CheckBox weather_page_good;
    private CheckBox weather_page_bad;
    private CheckBox camera_page_good;
    private CheckBox camera_page_bad;
    private CheckBox no_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the black title bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_weather);

        city_buttons = findViewById(R.id.city_buttons);

        weather_page_good = findViewById(R.id.feedback_weather_good);
        weather_page_bad = findViewById(R.id.feedback_weather_bad);
        camera_page_good = findViewById(R.id.feedback_camera_good);
        camera_page_bad = findViewById(R.id.feedback_camera_bad);
        no_answer = findViewById(R.id.feedback_no_answer);

        /**
         * Once any button belongs to this RadioGroup been checked
         * show a toast to tell the user which button has just been checked
         */
        city_buttons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) { // id = recently checked button's id
                RadioButton radio_button = radioGroup.findViewById(id);
                String loading_text = "Loading " + radio_button.getText() + "'s weather!";
                Toast.makeText(WeatherActivity.this, loading_text, Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Assign operation after checkbox been checked
         * do it one by one
         */
        weather_page_good.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
                String feedback_text = chosen_status + compoundButton.getText();
                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
            }
        });
        weather_page_bad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
                String feedback_text = chosen_status + compoundButton.getText();
                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
            }
        });
        camera_page_good.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
                String feedback_text = chosen_status + compoundButton.getText();
                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
            }
        });
        camera_page_bad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
                String feedback_text = chosen_status + compoundButton.getText();
                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
            }
        });
        no_answer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
                String feedback_text = chosen_status + compoundButton.getText();
                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
            }
        });

    }

}