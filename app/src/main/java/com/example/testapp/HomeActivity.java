package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageView user_picture;
    private SlideMenuActivity slide_menu;
    private Button weather_button;
    private Button camera_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the black title bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_home);

        // Get user picture (in layout_main.xml) and the slide menu (in activity_home_page.xml)
        user_picture = findViewById(R.id.user_picture);
        slide_menu = findViewById(R.id.slide_menu);

        // Get all buttons in home page
        weather_button = findViewById(R.id.home_weather_button);
        camera_button = findViewById(R.id.home_camera_button);

        /**
        One way to set click event for single button
         */
        // Make load slide action possible after clicking on user picture
        user_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slide_menu.switchMenu(); // Allow the slide menu to be on/off
            }
        });

        /**
         Another way to set click event for all buttons at once!
         */
        // Here's another cleaner way to set on click listener to multiple buttons at once:
        setListener(); // Call setListener here to set all other buttons click event at once!

    }

    // Helper method that just set all on click listener events at once
    private void setListener(){
        // Initialize a on click event that handles all kinds of event intents at once
        // This object will be defined in the next method
        OnClick on_click = new OnClick();

        // Set each button with corresponding OnClickListener
        // Each listener will be defined in the next method
        weather_button.setOnClickListener(on_click);
        camera_button.setOnClickListener(on_click);
    }

    // Helper method for creating all kinds of activity intent based on button id in one space

    /**
     * If you write the follow line to create a method and IDE turns red,
     * just double click on OnClick, press alt + enter, choose "implement this method" and click on OK!
     */
    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = null;
            // Set intent based on button id
            switch (view.getId()){
                case R.id.home_weather_button:
                    // Set intent to jump to weather page
                    intent = new Intent(HomeActivity.this, WeatherActivity.class);
                    break;
                case R.id.home_camera_button:
                    // Set intent to jump to camera page
                    intent = new Intent(HomeActivity.this, CameraActivity.class);
                    break;
            }
            // Run this intent
            startActivity(intent);
        }
    }
}