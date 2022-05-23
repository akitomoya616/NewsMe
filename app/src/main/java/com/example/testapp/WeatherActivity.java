package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private TextView city_name, temperature_value, weather_condition;
    private RecyclerView weatherRV;
    private EditText city_input;
    private ImageView background, search_icon, temperature_icon;

    private ArrayList<WeatherModel> weather_model_arraylist;
    private WeatherAdapter weather_adapter;

    private LocationManager locationManager;
    private int PERMISSION_CODE = 1;

    private String cityName;

    private String bestProvider;
    private Criteria criteria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the black title bar
        getSupportActionBar().hide();


        // Make application full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_weather);


        // For weather-related
        homeRL = findViewById(R.id.weather_home);
        loading_icon = findViewById(R.id.weather_loading);

        city_name = findViewById(R.id.weather_city_name); // city name displayed at the top of the page
        temperature_value = findViewById(R.id.temperature);
        weather_condition = findViewById(R.id.weather_condition);

        weatherRV = findViewById(R.id.idRvWeather);

        city_input = findViewById(R.id.city_name_box); // Edit text city name entered by user

        background = findViewById(R.id.weather_background);
        search_icon = findViewById(R.id.weather_search_icon);
        temperature_icon = findViewById(R.id.temperature_icon);

        weather_model_arraylist = new ArrayList<>();
        weather_adapter = new WeatherAdapter(this, weather_model_arraylist);
        weatherRV.setAdapter(weather_adapter);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(WeatherActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_CODE);

        }

//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//        if(location == null){
//            Toast.makeText(WeatherActivity.this, "No location!", Toast.LENGTH_SHORT).show();
//            criteria = new Criteria();
//            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
//            locationManager.requestLocationUpdates(bestProvider, 1000, 0, (LocationListener) this);
//        }

        Location location = getLastKnownLocation();

        if(location == null){
            Toast.makeText(WeatherActivity.this, "No location!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(WeatherActivity.this, "Found location", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Longitude is: " + location.getLongitude());
            Log.d("TAG", "Latitude is: " + location.getLatitude());
        }


//        cityName = get_city_name(location.getLongitude(), location.getLatitude()); // city name returned by api
//        getWeatherInfo(cityName);
        cityName = "London";
        getWeatherInfo(cityName);


        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = city_input.getText().toString();
                if (city.isEmpty()) {
                    Toast.makeText(WeatherActivity.this, String.valueOf(location.getLongitude()), Toast.LENGTH_SHORT).show();
                } else {
                    city_name.setText(city);
                    getWeatherInfo(city);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please Provide Permissions", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private Location getLastKnownLocation() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private String get_city_name(double longitude, double latitude){
        String city_name = "Not Found";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(latitude, longitude, 10);

            for (Address adr : addresses){
                if (adr != null){
                    String city = adr.getLocality();
                    System.out.println("city name: " + city);
                    if(city != null && !city.equals("")){
                        city_name = city;
                    }
                    else{
                        Log.d("TAG", "CITY NOT FOUND");
                        Toast.makeText(this, "User City Not Found!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return city_name;
    }

    // Get weather info based on city name using API
    // The following url was directly copied from Call section in https://www.weatherapi.com/api-explorer.aspx#forecast
    // But by replacing the city name Boulder with var city_name
    private void getWeatherInfo(String city_name){
        String url = "https://api.weatherapi.com/v1/forecast.json?key=4f355d97cfb44cc4a99195003222205&q="
                + city_name + "&days=1&aqi=yes&alerts=yes";
        Log.d("TAG", "url is: " + url);
        this.city_name.setText(city_name);

        Toast.makeText(WeatherActivity.this, city_name, Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue = Volley.newRequestQueue(WeatherActivity.this);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                // Once receive JSON data using api, remove the loading screen and show the weather homepage
                loading_icon.setVisibility(View.GONE);
                homeRL.setVisibility(View.VISIBLE);
                weather_model_arraylist.clear();

                try {
                    // Get the temperature from JSON file
                    // on topic "current" (line 12 in Postman),
                    // with type value recorded in "temp_c" (line 15)
                    String temperature = response.getJSONObject("current").getString("temp_c");
                    temperature_value.setText(temperature+"°C");
                    int isDay = response.getJSONObject("current").getInt("is_day");
                    String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                    String conditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon");
                    Picasso.get().load("http:".concat(conditionIcon)).into(temperature_icon);
                    weather_condition.setText(condition);
                    if(isDay == 1){
                        // if morning
                        Picasso.get().load("https://simplifaster.com/wp-content/uploads/2017/05/Sunshine-Vitamin-D.jpg").into(background);
                    }
                    else{
                        Picasso.get().load("http://meetingmediagroup.com/data/meetingmediagroup.com/upload/cms/attributeinstance/10/3027/file.o.jpg").into(background);
                    }

                    JSONObject forcastObj = response.getJSONObject("forecast");
                    JSONObject forcast0 = forcastObj.getJSONArray("forcastday").getJSONObject(0);
                    JSONArray hourArray = forcast0.getJSONArray("hour");

                    for(int i=0; i<hourArray.length(); i++){
                        JSONObject hourObj = hourArray.getJSONObject(i);
                        String time = hourObj.getString("time");
                        String temp = hourObj.getString("temp_c");
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        String wind = hourObj.getString("wind_kph");

                        weather_model_arraylist.add(new WeatherModel(time, temp, img, wind));

                    }

                    weather_adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            // Otherwise just warn the user
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(WeatherActivity.this, "Please enter valid city name", Toast.LENGTH_SHORT).show();
            }
        });



        requestQueue.add(jsonObjectRequest);

    }

}