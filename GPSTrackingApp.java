package com.example.gpstrackingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView latLong, addressText, distanceText, recent1, recent2, recent3, favorite;
    Chronometer stopwatch;
    double lat, lon;
    double distance = 0;
    double originalLat = 0;
    double originalLon = 0;
    LocationManager locationManager;
    int count = 0;
    int max = 0;
    ArrayList<String> addressList;
    ArrayList<Integer> timeList;
    LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latLong = findViewById(R.id.textView1);
        addressText = findViewById(R.id.textView2);
        distanceText = findViewById(R.id.textView3);
        recent1 = findViewById(R.id.textView4);
        recent2 = findViewById(R.id.textView5);
        recent3 = findViewById(R.id.textView6);
        favorite = findViewById(R.id.textView7);
        stopwatch = findViewById(R.id.stopwatch);
        addressList = new ArrayList<>();
        timeList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        if(savedInstanceState != null) {
            Log.d("Check: ", "In OnCreate Saved Instance");
            addressList = savedInstanceState.getStringArrayList("addresslist");
            timeList = savedInstanceState.getIntegerArrayList("timelist");
            distance = savedInstanceState.getDouble("distance");
            lat = savedInstanceState.getDouble("latitude");
            lon = savedInstanceState.getDouble("longitude");
            originalLat = savedInstanceState.getDouble("originalLatitude");
            originalLon = savedInstanceState.getDouble("originalLongitude");
            count = savedInstanceState.getInt("count");
            latLong.setText("Current Position: (" + lat + "," + lon + ")");
            max = savedInstanceState.getInt("max");
            addressText.setText(savedInstanceState.getString("currentAddress"));
            distanceText.setText("Distance Traveled:" + distance / 1000 + " km");
            favorite.setText(savedInstanceState.getString("favorite"));
            recent1.setText(savedInstanceState.getString("recent1"));
            recent2.setText(savedInstanceState.getString("recent2"));
            recent3.setText(savedInstanceState.getString("recent3"));
            Log.d("Check: ", "Timer" + savedInstanceState.getLong("stopwatchBase"));
            stopwatch.setBase(savedInstanceState.getLong("stopwatchBase"));
            stopwatch.start();
        }

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("Check:", "In Location Change");
                if (lat != location.getLatitude() || lon != location.getLongitude()){
                    Log.d("Check:", "In LocChange If Statement");
                    count++;
                    Log.d("Check:", count+"");
                    if(count>0){
                        originalLat = lat;
                        originalLon = lon; }
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    latLong.setText("Current Position: (" + lat + "," + lon + ")");
                    stopwatch.start();
                    Log.d("Check:", "LatLongTextSet");
                    Log.d("Check:", lat + "," + lon);
                    distance += distance(lat, lon, originalLat, originalLon);
                    try {
                        long elapsedMillis = SystemClock.elapsedRealtime() - stopwatch.getBase();
                        timeList.add(0, (int) elapsedMillis);
                        Log.d("Check:", timeList.toString());
                        if(addressList.size() == 0){
                            recent1.setText("No address yet");
                            recent2.setText("No address yet");
                            recent3.setText("No address yet");
                            favorite.setText("No address yet");
                            Log.d("Check: ", "Size is 0");
                        }
                        else if(addressList.size() == 1){
                            recent1.setText("Last address visited: " + addressList.get(0) + " , " + "Time spent: " + timeList.get(0)/1000 + " s");
                            recent2.setText("No address yet");
                            recent3.setText("No address yet");
                            Log.d("Check: ", "Size is 1");
                            if(timeList.get(0) > max){
                                max = timeList.get(0);
                                favorite.setText("Favorite Location: " + addressList.get(0) + ", Time spent: " + max/1000 + " s");
                            }
                        }
                        else if (addressList.size() == 2){
                            recent1.setText("Last address visited: " + addressList.get(0) + " , " +  "Time spent: " + timeList.get(0)/1000 + " s");
                            recent2.setText("Second to last address visited: " + addressList.get(1) + " , " +  "Time spent: " + timeList.get(1)/1000 + " s");
                            recent3.setText("No address yet");
                            Log.d("Check: ", "Size is 2");
                            if(timeList.get(0) > max){
                                max = timeList.get(0);
                                favorite.setText("Favorite Location: " + addressList.get(0) + ", Time spent: " + max/1000 + " s");
                            }
                        }
                        else{
                            recent1.setText("Last address: " + addressList.get(0) + " , " +  "Time spent: " + timeList.get(0)/1000 + " s");
                            recent2.setText("Second to last address visited: " + addressList.get(1) + " , " +  "Time spent: " + timeList.get(1)/1000 + " s");
                            recent3.setText("Third to last address visited: " + addressList.get(2) + " , " +  "Time spent: " + timeList.get(2)/1000 + " s");
                            Log.d("Check: ", "Size is >2");
                            if(timeList.get(0) > max){
                                max = timeList.get(0);
                                favorite.setText("Favorite Location: " + addressList.get(0) + ", Time spent: " + max/1000.0 + " s");
                            }
                        }
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.US);
                        List<Address> address = geocoder.getFromLocation(lat, lon, 1);
                        addressText.setText("Current Address: " + address.get(0).getAddressLine(0));
                        String s = address.get(0).getAddressLine(0);
                        distanceText.setText("Distance Traveled: " + distance / 1000 + " km");
                        addressList.add(0, s);
                        Log.d("Check: ", addressList.toString());
                        stopwatch.setBase(SystemClock.elapsedRealtime());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);

    }



    public double distance(double lat, double lon, double originalLat, double originalLon){
        if(originalLat == 0 && originalLon == 0){
            return 0;
        }
        final int R = 6371;
        double latDistance = Math.toRadians(lat - originalLat);
        double lonDistance = Math.toRadians(lon - originalLon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(originalLat)) * Math.cos(Math.toRadians(lat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c * 1000;
        return d;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    while(true) {
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);
                            break;
                        }
                    }

                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("addresslist", addressList);
        outState.putIntegerArrayList("timelist", timeList);
        outState.putDouble("distance", distance);
        outState.putDouble("latitude", lat);
        outState.putDouble("longitude", lon);
        outState.putDouble("originalLatitude", originalLat);
        outState.putDouble("originalLongitude", originalLon);
        outState.putInt("count", count);
        outState.putLong("stopwatchBase", stopwatch.getBase());
        outState.putInt("max", max);
        outState.putString("currentAddress", addressText.getText().toString());
        outState.putString("favorite", favorite.getText().toString());
        outState.putString("recent1", recent1.getText().toString());
        outState.putString("recent2", recent2.getText().toString());
        outState.putString("recent3", recent3.getText().toString());
        locationManager.removeUpdates(locationListener);
    }
}