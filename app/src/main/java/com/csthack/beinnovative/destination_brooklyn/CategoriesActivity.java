package com.csthack.beinnovative.destination_brooklyn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.csthack.beinnovative.destination_brooklyn.POIdata;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CategoriesActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        OnClickListener{

    Spinner timeFilter, buildingType;
    String timePeriodSelected, buildingTypeSelected;
    Button btn_filterResults, btn_Popular;
    Boolean isPopularOn = false, blackPressed = false;
    pointInterestClass[] myObjects;
    LinearLayout detailsList;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        timeFilter =(Spinner) findViewById(R.id.spinner_TimeFilter);
        timeFilter.setSelection(0);
        buildingType =(Spinner) findViewById(R.id.spinner_BuildingType);
        buildingType.setSelection(0);
        btn_filterResults = (Button) findViewById(R.id.btn_FilterResults);
        btn_Popular = (Button) findViewById(R.id.btn_Popular);
        detailsList = (LinearLayout) findViewById(R.id.details_list);
        btn_Popular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (blackPressed == true){
                    btn_Popular.setBackground(getResources().getDrawable(R.drawable.button_boarder));
                    btn_Popular.setTextColor(Color.parseColor("#4B4E50"));
                    isPopularOn = false;
                    blackPressed = false;
                } else {
                    btn_Popular.setBackgroundColor(Color.parseColor("#CD9B43"));
                    btn_Popular.setTextColor(Color.parseColor("#FFFFFF"));
                    blackPressed = true;
                    isPopularOn = true;
                }
            }
        });
        btn_filterResults.setOnClickListener(this);
        /**
         * Creates an array of objects using the POIdata class and pointInterestClass
         */

        POIdata start = new POIdata();
        myObjects = start.getPOIobjects();
        getPOIobjects();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    public void onClick(View v) {
        timePeriodSelected = timeFilter.getSelectedItem().toString();
        buildingTypeSelected = buildingType.getSelectedItem().toString();
        Intent launchMain = new Intent(this, MainActivity.class);
        launchMain.putExtra("TimePeriod", timePeriodSelected);
        launchMain.putExtra("buildingType", buildingTypeSelected);
        launchMain.putExtra("Popular", isPopularOn);
        startActivity(launchMain);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(40.6912, -73.9809);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.6912, -73.9809), 14.0f));
    }

    public pointInterestClass[] getPOIobjects() {

        return myObjects;
    }

    /**
     * Adding menu bar
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent launchActivity = new Intent();
        switch (item.getItemId()){
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
                Toast.makeText(getApplicationContext(),"Allow user to search a specific address/ subject", Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(launchActivity);
        return super.onOptionsItemSelected(item);
    }


}

 class MyAdapter extends ArrayAdapter<String> {

    public MyAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
    }


    public View getDropDownView(int position, View convertView,ViewGroup parent, Context context) {
        return getCustomView(position, convertView, parent,context);
    }

    public View getView(int position, View convertView, ViewGroup parent, Context context) {
        return getCustomView(position, convertView, parent,context);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent, Context context) {
        View row;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.spinner_row, parent, false);
        if (position == 0) {
            TextView label = (TextView) row.findViewById(R.id.textSpinner);
            label.setText("Time Period");

            ImageView icon = (ImageView) row.findViewById(R.id.image);
            icon.setImageResource(R.drawable.custom_info_bubble);
        }
        return row;
    }
}


