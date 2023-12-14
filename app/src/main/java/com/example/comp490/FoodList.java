/*
Used portions of it for the code
Author: priyankapakhale
https://github.com/priyankapakhale/GoogleMapsNearbyPlacesDemo
*/
//package com.example.comp490;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//import android.content.Intent;
//import android.os.Bundle;
////import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//
//import java.util.ArrayList;
//
//public class FoodList extends AppCompatActivity {
//    private Button button;
//
//    public static ArrayList<String> food;
//    int min = 0;
//    int max = 7;
//
//    //private static final String TAG = "activity_food_list";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        //int[] foodArray = new int[3];
//        //foodArray[0]=1;
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_food_list);
//
//
//        //HOW TO DISPLAY LIST OF STRINGS: https://stackoverflow.com/questions/20750118/displaying-list-of-strings-in-android/20750202
//        String[] test = new String[]{"Burger", "Sushi", "Ramen", "Mexican", "Indian", "Persian", "Ugandan", "Health Potion"};
//        final ArrayList<String> list = new ArrayList<String>();
//        for (int i = 0; i < test.length; ++i) {
//            list.add(test[i]);
//        }
//
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        ListView foodlistview = (ListView) findViewById(R.id.foodlistview);
//        foodlistview.setAdapter(adapter);
//        //END OF DISPLAY LIST OF THINGS
//        //idk wtf
//
//        Button button = (Button) findViewById(R.id.back);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openList();
//            }
//        });
//        Button button2 = (Button) findViewById(R.id.randomize);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String output;
//                int index = (int) (Math.random() * test.length);
//                System.out.println(test[index]);
//                double random_num = Math.floor(Math.random()*(max-min+1)+min);
//                /*for (int i = 0; i < test.length; i++) {
//                    int index = (int) (Math.random() * test.length);
//                    //test[index];
//                    output = test[index];
//                }*/
//            }
//            //ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//            //ListView foodlistview = (ListView) findViewById(R.id.foodlistview);
//            //foodlistview.setAdapter(adapter);
//        });
//    }
//
//        //Don't touch this it's fucking broken
//        /*
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.activity_food_list, null);
//
//        // create the popup window
//        int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
//        int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window tolken
//        //View view = null;
//        popupWindow.showAtLocation(this.findViewById(R.id.foodlistview), Gravity.CENTER, 0, 0);
//
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//    }*/
//
//    public void openList(){
//        Intent intent = new Intent(this, List.class);
//        startActivity(intent);
//    }
//}
package com.example.comp490;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Random;

public class FoodList extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }


    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if (currentLocationmMarker != null) {
            currentLocationmMarker.remove();

        }
        Log.d("lat = ", "" + latitude);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationmMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(11));

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }
    }

    public void onClick(View v) {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch (v.getId()) {
            case R.id.B_mexican:
                mMap.clear();
                String url = getUrl(latitude, longitude, "mexican");
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby Mexican", Toast.LENGTH_SHORT).show();
                break;


            case R.id.B_burger:
                mMap.clear();
                url = getUrl(latitude, longitude, "burger");
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby Burger", Toast.LENGTH_SHORT).show();
                break;

            case R.id.B_sushi:
                mMap.clear();
                url = getUrl(latitude, longitude, "sushi");
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby Sushi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.B_ramen:
                mMap.clear();
                url = getUrl(latitude, longitude, "ramen");
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby Ramen", Toast.LENGTH_SHORT).show();
                break;

            case R.id.B_indian:
                mMap.clear();
                url = getUrl(latitude, longitude, "indian");
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby Indian", Toast.LENGTH_SHORT).show();
                break;


            case R.id.B_persian:
                mMap.clear();
                url = getUrl(latitude, longitude, "persian");
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby Persian", Toast.LENGTH_SHORT).show();
                break;

            case R.id.B_chinese:
                mMap.clear();
                url = getUrl(latitude, longitude, "chinese");
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby Chinese", Toast.LENGTH_SHORT).show();
                break;

            case R.id.B_italian:
                mMap.clear();
                url = getUrl(latitude, longitude, "italian");
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby Italian", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B_randomize:
                mMap.clear();
                String[] listRandom = new String[]{"Burger", "Sushi", "Ramen", "Mexican", "Indian", "Persian", "Chinese", "Italian"};
                Random r = new Random();
                int randomNumber = r.nextInt(listRandom.length);
                String randomizedDecision = listRandom[randomNumber];

                url = getUrl(latitude, longitude, randomizedDecision);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(FoodList.this, "Showing Nearby " + randomizedDecision, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + latitude + "," + longitude);
        googlePlaceUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlaceUrl.append("&name=" + nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + "AIzaSyAy-5-eOr1F8cYAHAQWKFqIU5tdpToGDv4");

        Log.d("MapsActivity", "url = " + googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, REQUEST_LOCATION_CODE);
            }
            return false;

        } else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
}
