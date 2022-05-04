package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Create objects
    private Button login_button;
    private EditText username_box;
    private EditText password_box;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the black title bar
        getSupportActionBar().hide();

        // set the activity with a UI layout (coded in the xml file)
        setContentView(R.layout.activity_main);

        // Assign object to the corresponding existing object defined in xml files
        login_button = findViewById(R.id.login_button);
        username_box = findViewById(R.id.user_name_box); // It gets the whole EditText object but not just the text inside
        password_box = findViewById(R.id.password_box);

//        // Assign method to the object when clicked - DIRECT JUMP WITHOUT CHECKING CONDITION
//        login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create an intent
//                Intent intent = null;
//                // Set the original page we are staying at and the page we want to jump to
//                intent = new Intent(MainActivity.this, HomePage.class);
//                // Call startActivity with this intent to process this jump
//                startActivity(intent);
//            }
//        });

        // Assign method to the object when clicked - JUMP WITH CONDITION MATCHING
        login_button.setOnClickListener(this);

    }

    public void onClick(View view){
        // Get username and password entered by the user

        // Get the text inside the EditText object, and transfer it to String type
        String username = username_box.getText().toString();
        String password = password_box.getText().toString();
        Intent intent = null;

        // Set toast message for later use
        String login_success = "Login Successful!";
        String login_fail = "User ID or Password is incorrect!";

        // Jump to the next page if username and password match with the correct one
        if (username.equals("test") && password.equals("1234")){
            // Show toast to show the user that this is a successfully login

            // Toast appears at the bottom of the screen
            Toast.makeText(getApplicationContext(), login_success, Toast.LENGTH_SHORT).show();


            // Set the original page we are staying at and the page we want to jump to
            intent = new Intent(MainActivity.this, HomePage.class);
            // Call startActivity with this intent to process this jump
            startActivity(intent);
        }
        else{
            // Otherwise pop up a toast to notify the user for this failure

//            // Toast appears at the bottom of the screen
//            Toast.makeText(getApplicationContext(), login_fail, Toast.LENGTH_SHORT).show();

            // Toast appears at the center of the screen - DOESN'T WORK FOR DEVICES RUNNING API30 OR HIGHER!!!
            Toast toastCenter = Toast.makeText(getApplicationContext(), login_fail, Toast.LENGTH_SHORT); // Same as the method above but without calling show()
            toastCenter.setGravity(Gravity.CENTER, 0, 0);
            toastCenter.show();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}