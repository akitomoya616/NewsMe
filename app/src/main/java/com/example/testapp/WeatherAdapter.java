package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherModel> weather_model_arraylist;

    public WeatherAdapter(Context context, ArrayList<WeatherModel> weather_model_arraylist) {
        this.context = context;
        this.weather_model_arraylist = weather_model_arraylist;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // call weather_item.xml here to use the widget designed in it
        View view = LayoutInflater.from(context).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        // Get the model that contains all the weather information using the API on Postman
        // the array list stores the forecasted weather in the next few hours
        WeatherModel model = weather_model_arraylist.get(position);
        // model.getIcon returns the icon representing the current weather in the user area
        // e.g. //cdn.weatherapi.com/weather/64x64/day/122.png
        // therefore we need to add http in the front to load it
        Picasso.get().load("https:".concat(model.getIcon())).into(holder.condition_icon);
        // Do the same thing for temperature and wind speed
        holder.temperature.setText(model.getTemperature()+"°C");
        holder.wind_speed.setText(model.getWind_speed()+"KM/H");
        // Since the time we get from the model will be in the format like 2022-05-22 13:55
        // We need to reformat it manually to fit into our app
        SimpleDateFormat input_from_model = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output_from_model = new SimpleDateFormat("hh:mm aa");
        try{
            Date t = input_from_model.parse(model.getTime());
            holder.time.setText(output_from_model.format(t));
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weather_model_arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Create 3 textview and 1 imageview based on info in weather_item.xml
        private TextView wind_speed, temperature, time;
        private ImageView condition_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Assign these values with objects defined in weather_item.xml
            wind_speed = itemView.findViewById(R.id.weather_wind_speed);
            temperature = itemView.findViewById(R.id.weather_temperature);
            time = itemView.findViewById(R.id.weather_time);
            condition_icon = itemView.findViewById(R.id.weather_condition_icon);
        }
    }
}
