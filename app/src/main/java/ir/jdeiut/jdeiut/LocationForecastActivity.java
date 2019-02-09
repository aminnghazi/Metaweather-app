package ir.jdeiut.jdeiut;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ir.jdeiut.jdeiut.model.caller.LocationForecastCaller;
import ir.jdeiut.jdeiut.model.caller.OnLocationForecastReceived;
import ir.jdeiut.jdeiut.model.entities.LocationForecastEntity;

public class LocationForecastActivity extends AppCompatActivity implements OnLocationForecastReceived {
TextView state_name;
TextView air_pressure;
TextView humidity;
TextView predictablity;
TextView minTemp;
TextView maxTemp;
TextView the_temp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_forecast);
        state_name = findViewById(R.id.state_name);
        air_pressure = findViewById(R.id.air_pressure);
        humidity = findViewById(R.id.humidity);
        predictablity=findViewById(R.id.predictablity);
        minTemp = findViewById(R.id.min_temp);
        maxTemp = findViewById(R.id.max_temp);
        the_temp = findViewById(R.id.the_temp);

        int data = getIntent().getExtras().getInt("woeid",0);
        if (data != 0){
            LocationForecastCaller caller = new LocationForecastCaller();
            caller.setOnLocationForecastReceived(this);
            caller.execute((data));
        }
    }

    @Override
    public void onlocationForecastReceived(LocationForecastEntity[] forecastEntity) {
    state_name.setText("Weather State : "+forecastEntity[0].getWeather_state_name());
    humidity.setText("Humidity = " + forecastEntity[0].getHumidity() + " %");
    predictablity.setText("Predictablity = " + forecastEntity[0].getPredictability()+"%");
    air_pressure.setText("Air pressure = " +forecastEntity[0].getAir_pressure() +" mbar");
    minTemp.setText("Min temperature : " + forecastEntity[0].getMin_temp()+ " (centigrade)");
    maxTemp.setText("Max temperature : " + forecastEntity[0].getMax_temp() + " (centigrade)");
    the_temp.setText("Average temerature : " + forecastEntity[0].getThe_temp() + "(centigrade)");
    }

}
