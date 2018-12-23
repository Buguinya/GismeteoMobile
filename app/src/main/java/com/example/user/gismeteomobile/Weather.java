package com.example.user.gismeteomobile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;


public class    Weather {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("current_condition")
        private ArrayList<HashMap<String, Object>> currentCondition;

        public ArrayList<HashMap<String, Object>> getCurrentCondition() {
            return currentCondition;
        }

        @SerializedName("request")
        private ArrayList<HashMap<String, String>> request;

        public ArrayList<HashMap<String, String>> getRequest() {
            return request;
        }

        @SerializedName("weather")
        private ArrayList<WeatherDesc> weather;

        public ArrayList<WeatherDesc>  getWeather() {
            return weather;
        }

        public class WeatherDesc {
            @SerializedName("date")
            private String date;

            public String getDate()  {
                return date;
            }


            @SerializedName("hourly")
            private ArrayList<PartsOfDay> dayParts;

            public ArrayList<PartsOfDay> getDayParts() {
                return dayParts;
            }

            public class PartsOfDay {
                @SerializedName("tempC")
                private String timeTempC;

                public String getTimeTempC() {
                    return timeTempC;
                }

                @SerializedName("weatherDesc")
                private ArrayList<HashMap<String, String>> weatherDesc;

                public ArrayList<HashMap<String, String>> getWeatherDesc() {
                    return weatherDesc;
                }
            }
        }
    }
}




