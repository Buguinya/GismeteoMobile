package com.example.user.gismeteomobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private Spinner spinner;
    private Button add;



    private final String KEY_API = "a972b8ecfa534cb7b0e155928181811";
    private List<Weather> forecasts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.choose_place);
        add = findViewById(R.id.add);






        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new SpacesItemDecoration(50));
        recyclerView.setLayoutManager(linearLayoutManager);
        weatherAdapter = new WeatherAdapter();
        recyclerView.setAdapter(weatherAdapter);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (spinner.getSelectedItem().toString()){
                    case("Izhevsk") :
                        loadWeather( "Izhevsk");
                        break;
                    case("Moscow") :
                        loadWeather( "Moscow");
                        break;
                    case("Saint Petersburg") :
                        loadWeather("Saint Petersburg");
                        break;
                    case("Kazan") :
                        loadWeather( "Kazan");
                        break;
                    case("Sochi") :
                        loadWeather( "Sochi");
                        break;
                }
            }
        });
    }

    public void loadWeather(String place) {
        App.getGismeteoApi().getWeather(place, KEY_API, "JSON", "1").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "onResponse");
                    Weather weather = response.body();
                    forecasts.add(weather);
                    weatherAdapter.addItem(forecasts);

                } else {
                    Log.d("MainActivity", "response isn't successful");
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d("MainActivity", "onFailure: " + t);

            }
        });
    }

}
