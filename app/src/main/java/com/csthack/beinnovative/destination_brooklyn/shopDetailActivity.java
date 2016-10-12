package com.csthack.beinnovative.destination_brooklyn;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class shopDetailActivity extends AppCompatActivity {
    private ArrayList<shopClass> shopObjects;
    String namePassed;
    TextView title, url, description, website;
    LinearLayout image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        Bundle extras = getIntent().getExtras();
        namePassed = getIntent().getExtras().getString("shopname");
        //System.out.println("namePassed: "+namePassed);
        ShopData start = new ShopData();
        shopObjects = start.getShopData();
        int item = getObjectCliked();
        title = (TextView) findViewById(R.id.title);
        url = (TextView) findViewById(R.id.url);
        description = (TextView) findViewById(R.id.description);
        website = (TextView) findViewById(R.id.website);
        image = (LinearLayout) findViewById(R.id.bkgImage);

        title.setText(shopObjects.get(item).getName());
        url.setText("Shop Details");
        description.setText(shopObjects.get(item).getDescription());
        website.setText(shopObjects.get(item).getWebsite());
        image.setBackground(shopObjects.get(item).image);
    }

    public int getObjectCliked(){
        int a = 0;
        for (int i = 0; i<shopObjects.size(); i++){
            if(shopObjects.get(i).getName().equalsIgnoreCase(namePassed)){
                a = i;
            }
        }
        System.out.println("a:"+a);
        System.out.println(shopObjects.get(a).getName());
        return a;
    }

    /**
     * Adding menu bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
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

