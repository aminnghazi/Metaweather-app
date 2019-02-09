package ir.jdeiut.jdeiut.model.entities;

public class LocationForecastEntity {
private String weather_state_name;
private float air_pressure;
private float humidity;
private int predictability;
private float min_temp;
private float max_temp;
private float the_temp;

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public float getAir_pressure() {
        return air_pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public int getPredictability() {
        return predictability;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public float getThe_temp() {
        return the_temp;
    }
}
