package com.csthack.beinnovative.destination_brooklyn;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements
        OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;
    private double lat, lon;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);

        /**
         * Sets default map view to current location
         */
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude;
        double latitude;
        if (location  == null) {
            longitude = -73.9897337;
            latitude = 40.69653;
        } else {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 14.0f));

        /**
         * Creates an array of objects using the POIdata class and pointInterestClass
         */

        POIdata start = new POIdata();
        pointInterestClass[] myObjects = start.getPOIobjects();

        Bundle intent = getIntent().getExtras();
        String buildingSelected ="";
        String timeSelected ="";
        if (intent != null) {
            buildingSelected = getIntent().getExtras().getString("buildingType");
            timeSelected =  getIntent().getExtras().getString("TimePeriod");
            System.out.println(buildingSelected+timeSelected);
            showFilters(myObjects, buildingSelected, timeSelected);
        } else {
            System.out.println("Doing else");
            buildingSelected = "Building Type";
            timeSelected = "Time Period";
            for (int i = 0; i < myObjects.length; i++) {
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

                lat = myObjects[i].getLatitude();
                lon = myObjects[i].getLongitude();

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lon))
                        .title(myObjects[i].getName())
                        .snippet(myObjects[i].getDescription()));
            }
        }
        /**
         * The following if statement gets the results of the filter and only
         * places a marker on the values that meet the filter conditions
         */

//        if (buildingSelected.equalsIgnoreCase("building type")) {
//            for (int i = 0; i < myObjects.length; i++) {
//
//                if (myObjects[i].getTimePeriod().equalsIgnoreCase(timeSelected)) {
//
//                    mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
//
//                    lat = myObjects[i].getLatitude();
//                    lon = myObjects[i].getLongitude();
//
//                    Marker marker = mMap.addMarker(new MarkerOptions()
//                            .position(new LatLng(lat, lon))
//                            .title(myObjects[i].getName())
//                            .snippet(myObjects[i].getDescription()));
//                }
//            }
//        } else if (timeSelected.equalsIgnoreCase("time period")) {
//            for (int i = 0; i < myObjects.length; i++) {
//
//                if (myObjects[i].getBuildingType().equalsIgnoreCase(buildingSelected)) {
//
//                    mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
//
//                    lat = myObjects[i].getLatitude();
//                    lon = myObjects[i].getLongitude();
//
//                    Marker marker = mMap.addMarker(new MarkerOptions()
//                            .position(new LatLng(lat, lon))
//                            .title(myObjects[i].getName())
//                            .snippet(myObjects[i].getDescription()));
//                }
//            }
//        } else {
//        }

        enableMyLocation();
    }

    private void showFilters(pointInterestClass[] myObjects, String buildingSelected, String timeSelected) {
        for (int i = 0; i < myObjects.length; i++) {

            if (myObjects[i].getBuildingType().equalsIgnoreCase(buildingSelected)
                    && myObjects[i].getTimePeriod().equalsIgnoreCase(timeSelected)) {

                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

                lat = myObjects[i].getLatitude();
                lon = myObjects[i].getLongitude();

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lon))
                        .title(myObjects[i].getName())
                        .snippet(myObjects[i].getDescription()));
            }
        }
    }

    private void showAll(pointInterestClass[] myObjects) {
        for (int i = 0; i < myObjects.length; i++) {
            mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

            lat = myObjects[i].getLatitude();
            lon = myObjects[i].getLongitude();

            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lon))
                    .title(myObjects[i].getName())
                    .snippet(myObjects[i].getDescription()));

        }
    }

    private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View mWindow;

        private final View mContents;

        CustomInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
            mContents = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            render(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            render(marker, mContents);
            return mContents;
        }

        private void render(Marker marker, View view) {
            int badge;

            String title = marker.getTitle();
            TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (title != null) {
                // Spannable string allows us to edit the formatting of the text.
                SpannableString titleText = new SpannableString(title);
                titleText.setSpan(new ForegroundColorSpan(Color.BLACK), 0, titleText.length(), 0);
                titleUi.setText(titleText);
            } else {
                titleUi.setText("");
            }

            String snippet = marker.getSnippet();
            TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
            if (snippet != null && snippet.length() > 12) {
                SpannableString snippetText = new SpannableString(snippet);
                snippetText.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 10, 0);
                snippetText.setSpan(new ForegroundColorSpan(Color.BLACK), 12, snippet.length(), 0);
                snippetUi.setText(snippetText);
            } else {
                snippetUi.setText("");
            }
        }


    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        //Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    /**
     * Adding menu bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent launchActivity = new Intent();
        switch (item.getItemId()) {
            case R.id.filter_id:
                launchActivity = new Intent(this, CategoriesActivity.class);
                break;

            case R.id.store_id:
                launchActivity = new Intent(this, ShopActivity.class);
                break;


            case R.id.centre_id:
                launchActivity = new Intent(this, MainActivity.class);
                launchActivity.putExtra("buildingType", "");
                launchActivity.putExtra("TimePeriod", "");
                break;

            case R.id.search_id:
                Toast.makeText(getApplicationContext(), "Allow user to search a specific address/ subject", Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(launchActivity);
        return super.onOptionsItemSelected(item);
    }


}