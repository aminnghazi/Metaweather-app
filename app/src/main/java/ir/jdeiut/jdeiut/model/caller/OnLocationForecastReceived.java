package ir.jdeiut.jdeiut.model.caller;

import ir.jdeiut.jdeiut.model.entities.LocationForecastEntity;

public interface OnLocationForecastReceived {
    void onlocationForecastReceived(LocationForecastEntity[] forecastEntity);

}
