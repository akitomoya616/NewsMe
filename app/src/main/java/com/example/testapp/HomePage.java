package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {

    private ImageView user_picture;
    private SlideMenu slide_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the black title bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_home_page);

        // Get user picture (in layout_main.xml) and the slide menu (in activity_home_page.xml)
        user_picture = findViewById(R.id.user_picture);
        slide_menu = findViewById(R.id.slide_menu);

        // Make load slide action possible after clicking on user picture
        user_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slide_menu.switchMenu(); // Allow the slide menu to be on/off
            }
        });

    }
}