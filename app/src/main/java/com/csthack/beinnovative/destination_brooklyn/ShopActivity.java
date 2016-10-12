package com.csthack.beinnovative.destination_brooklyn;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    ListView shopList;
    CustomListAdapter adapter;
    private ArrayList<shopClass> shopObjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shopList = (ListView) findViewById(R.id.listShop);
        ShopData start = new ShopData();

        shopObjects = start.getShopData();
        setBackground();
        adapter = new CustomListAdapter(this, shopObjects, R.layout.list_row);
//        for (int i = 0; i<2; i++){
//            System.out.println(shopObjects.get(i).toString());
//            adapter.add(shopObjects.get(i));
//        }
        shopList.setAdapter(adapter);
        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Object listItem = shopList.getItemAtPosition(position);
                System.out.println("listItem: "+listItem);
                shopClass passOn = shopObjects.get(position);
                System.out.println("passON: "+passOn);
                String shopname = passOn.getName();
                System.out.println("shopname: "+shopname);
                Intent launchMain = new Intent(ShopActivity.this, DetailShopActivity.class);
                launchMain.putExtra("shopname", shopname);
                startActivity(launchMain);
            }
        });
    }


    public void setBackground () {
        for(int i =0; i<shopObjects.size(); i++) {
            if (shopObjects.get(i).getImageURL().equalsIgnoreCase("bridge coffee")) {
                shopObjects.get(i).image = getResources().getDrawable(R.drawable.bridgecoffee);
                shopObjects.get(i).setImage(getResources().getDrawable(R.drawable.bridgecoffee));
            } else if (shopObjects.get(i).getImageURL().equalsIgnoreCase("tutt heights")) {
                shopObjects.get(i).image = getResources().getDrawable(R.drawable.tuttheights);
                shopObjects.get(i).setImage(getResources().getDrawable(R.drawable.tuttheights));
            } else if (shopObjects.get(i).getImageURL().equalsIgnoreCase("Rocco jezebel")) {
                shopObjects.get(i).image = getResources().getDrawable(R.drawable.occojezebel);
                shopObjects.get(i).setImage(getResources().getDrawable(R.drawable.occojezebel));
            }else if(shopObjects.get(i).getImageURL().equalsIgnoreCase("Vineapple cafe")) {
                shopObjects.get(i).image = getResources().getDrawable(R.drawable.vineapplecafe);
                shopObjects.get(i).setImage(getResources().getDrawable(R.drawable.vineapplecafe));
            }else if(shopObjects.get(i).getImageURL().equalsIgnoreCase("Rocco tacos")) {
                shopObjects.get(i).image = getResources().getDrawable(R.drawable.roccotacos);
                shopObjects.get(i).setImage(getResources().getDrawable(R.drawable.roccotacos));
            }
        }
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
