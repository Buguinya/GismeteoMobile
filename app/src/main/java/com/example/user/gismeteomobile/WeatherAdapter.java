 package com.example.user.gismeteomobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {


    private static List<Weather> forecasts = Collections.emptyList();


    public static List<Weather> getForecasts() {
        return forecasts;
    }

    private static Context context;




    public void addItem(List<Weather> forecasts) {
        this.forecasts = forecasts;
        notifyItemInserted(forecasts.size());
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(forecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView currentlyTemp;
        TextView place;
        TextView date;
        TextView weatherDesc;
        TextView feelTemp;
        TextView humidity;
        TextView wind;
        TextView pressure;
        TextView tempNight;
        TextView tempMorning;
        TextView tempAfternoon;
        TextView tempEvening;
        Button removeBtn;
        ImageView visualCurrent;
        ImageView visualNight;
        ImageView visualMorning;
        ImageView visualAfternoon;
        ImageView visualEvening;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            visualAfternoon = itemView.findViewById(R.id.weather_visual_afternoon);
            visualCurrent = itemView.findViewById(R.id.weather_visual_currently);
            visualEvening = itemView.findViewById(R.id.weather_visual_evening);
            visualMorning = itemView.findViewById(R.id.weather_visual_morning);
            visualNight = itemView.findViewById(R.id.weather_visual_night);
            removeBtn = itemView.findViewById(R.id.remove);
            context = itemView.getContext();
            currentlyTemp = itemView.findViewById(R.id.temp_currently);
            place = itemView.findViewById(R.id.name_of_place);
            date = itemView.findViewById(R.id.date);
            weatherDesc = itemView.findViewById(R.id.weather_description);
            feelTemp = itemView.findViewById(R.id.feels_like_temp);
            humidity = itemView.findViewById(R.id.humidity);
            wind = itemView.findViewById(R.id.wind);
            pressure = itemView.findViewById(R.id.pressure);
            tempNight = itemView.findViewById(R.id.temp_night);
            tempAfternoon = itemView.findViewById(R.id.temp_afternoon);
            tempEvening = itemView.findViewById(R.id.temp_evening);
            tempMorning = itemView.findViewById(R.id.temp_morning);


            removeBtn.setOnClickListener(this);
        }


        @SuppressLint({"SetTextI18n", "StringFormatMatches"})
        public void bind(Weather forecast) {
            place.setText(forecast.getData().getRequest().get(0).get("query"));


            currentlyTemp.setText(context.getString(R.string.grad_C, forecast.getData().getCurrentCondition().get(0).get("temp_C").toString()));


            date.setText(forecast.getData().getWeather().get(0).getDate());


            ArrayList<LinkedTreeMap<String, String>> weatherDescriptions = ((ArrayList<LinkedTreeMap<String, String>>)forecast.getData().getCurrentCondition().get(0).get("weatherDesc"));
            weatherDesc.setText(weatherDescriptions.get(0).get("value"));


            feelTemp.setText(context.getString(R.string.grad_C, forecast.getData().getCurrentCondition().get(0).get("FeelsLikeC").toString()));


            wind.setText(context.getString(R.string.km_per_hour, forecast.getData().getCurrentCondition().get(0).get("windspeedKmph").toString()));


            pressure.setText(context.getString(R.string.mm_rt_st, forecast.getData().getCurrentCondition().get(0).get("pressure").toString()));


            humidity.setText(context.getString(R.string.percent, forecast.getData().getCurrentCondition().get(0).get("humidity").toString()));


            tempNight.setText(context.getString(R.string.grad_C, forecast.getData().getWeather().get(0).getDayParts().get(0).getTimeTempC()));


            tempMorning.setText(context.getString(R.string.grad_C, forecast.getData().getWeather().get(0).getDayParts().get(2).getTimeTempC()));


            tempAfternoon.setText(context.getString(R.string.grad_C, forecast.getData().getWeather().get(0).getDayParts().get(4).getTimeTempC()));


            tempEvening.setText(context.getString(R.string.grad_C, forecast.getData().getWeather().get(0).getDayParts().get(6).getTimeTempC()));


            switch (forecast.getData().getWeather().get(0).getDayParts().get(0).getWeatherDesc().get(0).get("value")){
                case("Mist") : visualNight.setImageResource(R.drawable.wsymbol_0006_mist);
                    break;
                case("Partly cloudy") : visualNight.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Clear") : visualNight.setImageResource(R.drawable.wsymbol_0008_clear_sky_night);
                    break;
                case("Light Snow, Mist") : visualNight.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Sunny") : visualNight.setImageResource(R.drawable.wsymbol_0001_sunny);
                    break;
                case("Cloudy") : visualNight.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Overcast") : visualNight.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Moderate snow") : visualNight.setImageResource(R.drawable.wsymbol_0036_cloudy_with_heavy_snow_night);
                    break;
                case("Light snow") : visualNight.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Freezing fog") : visualNight.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Fog") : visualNight.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Light drizzle") : visualNight.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                default : visualNight.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
            }


            switch (forecast.getData().getWeather().get(0).getDayParts().get(2).getWeatherDesc().get(0).get("value")){
                case("Mist") : visualMorning.setImageResource(R.drawable.wsymbol_0006_mist);
                    break;
                case("Partly cloudy") : visualMorning.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Clear") : visualMorning.setImageResource(R.drawable.wsymbol_0008_clear_sky_night);
                    break;
                case("Light Snow, Mist") : visualMorning.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Sunny") : visualMorning.setImageResource(R.drawable.wsymbol_0001_sunny);
                    break;
                case("Cloudy") : visualMorning.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Overcast") : visualMorning.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Moderate snow") : visualMorning.setImageResource(R.drawable.wsymbol_0036_cloudy_with_heavy_snow_night);
                    break;
                case("Light snow") : visualMorning.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Freezing fog") : visualMorning.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Fog") : visualMorning.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Light drizzle") : visualMorning.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                default : visualMorning.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
            }


            switch (forecast.getData().getWeather().get(0).getDayParts().get(4).getWeatherDesc().get(0).get("value")){
                case("Mist") : visualAfternoon.setImageResource(R.drawable.wsymbol_0006_mist);
                    break;
                case("Partly cloudy") : visualAfternoon.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Clear") : visualAfternoon.setImageResource(R.drawable.wsymbol_0008_clear_sky_night);
                    break;
                case("Light Snow, Mist") : visualAfternoon.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Sunny") : visualAfternoon.setImageResource(R.drawable.wsymbol_0001_sunny);
                    break;
                case("Cloudy") : visualAfternoon.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Overcast") : visualAfternoon.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Moderate snow") : visualAfternoon.setImageResource(R.drawable.wsymbol_0036_cloudy_with_heavy_snow_night);
                    break;
                case("Light snow") : visualAfternoon.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Freezing fog") : visualAfternoon.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Fog") : visualAfternoon.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Light drizzle") : visualAfternoon.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                default : visualAfternoon.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
            }

            switch (forecast.getData().getWeather().get(0).getDayParts().get(6).getWeatherDesc().get(0).get("value")){
                case("Mist") : visualEvening.setImageResource(R.drawable.wsymbol_0006_mist);
                    break;
                case("Partly cloudy") : visualEvening.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Clear") : visualEvening.setImageResource(R.drawable.wsymbol_0008_clear_sky_night);
                    break;
                case("Light Snow, Mist") : visualEvening.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Sunny") : visualEvening.setImageResource(R.drawable.wsymbol_0001_sunny);
                    break;
                case("Cloudy") : visualEvening.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Overcast") : visualEvening.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Moderate snow") : visualEvening.setImageResource(R.drawable.wsymbol_0036_cloudy_with_heavy_snow_night);
                    break;
                case("Fog") : visualEvening.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Light drizzle") : visualEvening.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                default : visualEvening.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
            }



            switch (weatherDescriptions.get(0).get("value")){
                case("Mist") : visualCurrent.setImageResource(R.drawable.wsymbol_0006_mist);
                    break;
                case("Partly cloudy") : visualCurrent.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Clear") : visualCurrent.setImageResource(R.drawable.wsymbol_0008_clear_sky_night);
                    break;
                case("Light Snow, Mist") : visualCurrent.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Sunny") : visualCurrent.setImageResource(R.drawable.wsymbol_0001_sunny);
                    break;
                case("Cloudy") : visualCurrent.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Overcast") : visualCurrent.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
                case("Moderate snow") : visualCurrent.setImageResource(R.drawable.wsymbol_0036_cloudy_with_heavy_snow_night);
                    break;
                case("Light snow") : visualCurrent.setImageResource(R.drawable.wsymbol_0011_light_snow_showers);
                    break;
                case("Freezing fog") : visualCurrent.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Patchy rain possible") : visualCurrent.setImageResource(R.drawable.wsymbol_0025_light_rain_showers_night);
                    break;
                case("Fog") : visualCurrent.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;
                case("Light drizzle") : visualCurrent.setImageResource(R.drawable.wsymbol_0007_fog);
                    break;

                default : visualCurrent.setImageResource(R.drawable.wsymbol_0004_black_low_cloud);
                    break;
            }

        }


        @Override
        public void onClick(View v) {
            if (v.equals(removeBtn)) {
                removeAt(getPosition());
            }
        }

        private void removeAt(int position) {
            forecasts.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, forecasts.size());
        }
    }
}

