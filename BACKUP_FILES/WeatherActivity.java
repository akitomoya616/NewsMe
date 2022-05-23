package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    // For button-related
//    private RadioGroup city_buttons;
//    private CheckBox weather_page_good;
//    private CheckBox weather_page_bad;
//    private CheckBox camera_page_good;
//    private CheckBox camera_page_bad;
//    private CheckBox no_answer;

    // For weather-related
    private RelativeLayout homeRL;
    private ProgressBar loading_icon;
    private TextView city_name, temperature, weather_condition;
    private RecyclerView weatherRV;
    private EditText city_input;
    private ImageView search_icon, temperature_icon;

    private ArrayList<WeatherModel> weather_model_arraylist;
    private WeatherAdapter weather_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the black title bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_weather);

        // For button-related
//        city_buttons = findViewById(R.id.city_buttons);
//
//        weather_page_good = findViewById(R.id.feedback_weather_good);
//        weather_page_bad = findViewById(R.id.feedback_weather_bad);
//        camera_page_good = findViewById(R.id.feedback_camera_good);
//        camera_page_bad = findViewById(R.id.feedback_camera_bad);
//        no_answer = findViewById(R.id.feedback_no_answer);

        // For weather-related
        homeRL = findViewById(R.id.weather_home);
        loading_icon = findViewById(R.id.weather_loading);

        city_name = findViewById(R.id.weather_city_name);
        temperature = findViewById(R.id.temperature);
        weather_condition = findViewById(R.id.weather_condition);

        weatherRV = findViewById(R.id.idRvWeather);

        city_input = findViewById(R.id.city_name_box);

        search_icon = findViewById(R.id.weather_search_icon);
        temperature_icon = findViewById(R.id.temperature_icon);

        weather_model_arraylist = new ArrayList<>();
        weather_adapter = new WeatherAdapter(this, weather_model_arraylist);
        weatherRV.setAdapter(weather_adapter);




        /**
         * Below was used for radio buttons
         */

        /**
         * Once any button belongs to this RadioGroup been checked
         * show a toast to tell the user which button has just been checked
         */
//        city_buttons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int id) { // id = recently checked button's id
//                RadioButton radio_button = radioGroup.findViewById(id);
//                String loading_text = "Loading " + radio_button.getText() + "'s weather!";
//                Toast.makeText(WeatherActivity.this, loading_text, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        /**
//         * Assign operation after checkbox been checked
//         * do it one by one
//         */
//        weather_page_good.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
//                String feedback_text = chosen_status + compoundButton.getText();
//                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
//            }
//        });
//        weather_page_bad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
//                String feedback_text = chosen_status + compoundButton.getText();
//                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
//            }
//        });
//        camera_page_good.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
//                String feedback_text = chosen_status + compoundButton.getText();
//                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
//            }
//        });
//        camera_page_bad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
//                String feedback_text = chosen_status + compoundButton.getText();
//                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
//            }
//        });
//        no_answer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                String chosen_status = isChecked ? "Chosen: " : "Unchosen: "; // A quick if/else statement
//                String feedback_text = chosen_status + compoundButton.getText();
//                Toast.makeText(WeatherActivity.this, feedback_text, Toast.LENGTH_SHORT).show();
//            }
//        });

    }

}