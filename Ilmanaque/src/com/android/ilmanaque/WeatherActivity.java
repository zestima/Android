package com.android.ilmanaque;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.graphics.Color;



public class WeatherActivity extends Activity {
	TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
	Typeface weatherFont;
	private static final int MY_PERMISSION_ACCES_COURSE_LOCATION = 11;
	double longitude,latitude;
	GPSTracker gps;
	RelativeLayout relativeLayout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_layout);
		gps = new GPSTracker(this);

		if(gps.canGetLocation()){

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();
		}
		else{
			gps.showSettingsAlert();
		}


		weatherFont = Typeface.createFromAsset(getAssets() , "font/weathericons-regular-webfont.ttf");
		relativeLayout = (RelativeLayout)findViewById(R.id.relative_layout);
		cityField = (TextView)findViewById(R.id.city_field);
		updatedField = (TextView)findViewById(R.id.updated_field);
		detailsField = (TextView)findViewById(R.id.details_field);
		currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
		humidity_field = (TextView)findViewById(R.id.humidity_field);
		pressure_field = (TextView)findViewById(R.id.pressure_field);
		weatherIcon = (TextView)findViewById(R.id.weather_icon);
		weatherIcon.setTypeface(weatherFont);


		Functions.placeIdTask asyncTask =new Functions.placeIdTask(new Functions.AsyncResponse() {
			public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

				float temp;
				float humi;
				float pressure;
				String weathertemp;

				cityField.setText(weather_city);
				updatedField.setText(weather_updatedOn);
				detailsField.setText(weather_description);
				currentTemperatureField.setText(weather_temperature);
				weathertemp = weather_temperature.replace('°',' ');
				temp = Float.parseFloat(weathertemp.replace(',','.'));
				if(temp > 30){
					relativeLayout.setBackgroundColor(Color.RED);
				}
				if(temp > 12 && temp < 30){
					relativeLayout.setBackgroundColor(Color.GREEN);
				}
				if(temp < 12){
					relativeLayout.setBackgroundColor(Color.GRAY);
				}

				humidity_field.setText("Humidity: "+weather_humidity);
				pressure_field.setText("Pressure: "+weather_pressure);
				weatherIcon.setText(Html.fromHtml(weather_iconText));

			}
		});
        asyncTask.execute(String.valueOf(latitude), String.valueOf(longitude)); //  asyncTask.execute("Latitude", "Longitude")



    }

    @Override
    protected void onResume() {
    	super.onResume();

    	gps = new GPSTracker(this);

    	if(gps.canGetLocation()){

    		latitude = gps.getLatitude();
    		longitude = gps.getLongitude();
    	}
    	else{
    		gps.showSettingsAlert();
    	}

    	weatherFont = Typeface.createFromAsset(getAssets() , "font/weathericons-regular-webfont.ttf");
    	relativeLayout = (RelativeLayout)findViewById(R.id.relative_layout);
    	cityField = (TextView)findViewById(R.id.city_field);
    	updatedField = (TextView)findViewById(R.id.updated_field);
    	detailsField = (TextView)findViewById(R.id.details_field);
    	currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
    	humidity_field = (TextView)findViewById(R.id.humidity_field);
    	pressure_field = (TextView)findViewById(R.id.pressure_field);
    	weatherIcon = (TextView)findViewById(R.id.weather_icon);
    	weatherIcon.setTypeface(weatherFont);

    	Functions.placeIdTask asyncTask =new Functions.placeIdTask(new Functions.AsyncResponse() {
    		public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

    			float temp;
    			float humi;
    			float pressure;
    			String weathertemp;

    			cityField.setText(weather_city);
    			updatedField.setText(weather_updatedOn);
    			detailsField.setText(weather_description);
    			currentTemperatureField.setText(weather_temperature);
    			weathertemp = weather_temperature.replace('°',' ');
    			temp = Float.parseFloat(weathertemp.replace(',','.'));
    			if(temp > 30){
    				relativeLayout.setBackgroundColor(Color.RED);
    			}
    			if(temp > 12 && temp < 30){
    				relativeLayout.setBackgroundColor(Color.GREEN);
    			}
    			if(temp < 12){
    				relativeLayout.setBackgroundColor(Color.GRAY);
    			}

    			humidity_field.setText("Humidity: "+weather_humidity);
    			pressure_field.setText("Pressure: "+weather_pressure);
    			weatherIcon.setText(Html.fromHtml(weather_iconText));

    		}
    	});
        asyncTask.execute(String.valueOf(latitude), String.valueOf(longitude)); //  asyncTask.execute("Latitude", "Longitude")
    }


}

