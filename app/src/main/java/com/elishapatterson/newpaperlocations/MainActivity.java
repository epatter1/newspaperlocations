package com.elishapatterson.newpaperlocations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.elishapatterson.newpaperlocations.Adapter.RecyclerAdapter;
import com.elishapatterson.newpaperlocations.JsonParser.Parser;
import com.elishapatterson.newpaperlocations.model.NewspaperLocationModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<NewspaperLocationModel> newspaperLocationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});


        // Parse the assets/newspaper_locations.json file
        Parser parser = new Parser(getApplicationContext(), "newspaper_locations.json");
        newspaperLocationModelArrayList = parser.getNewspaperLocationModelList();
        Log.i("NewspaperMainActivity", "Number of Models: " + newspaperLocationModelArrayList.size());

        // Start RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Specify the adapter
        mAdapter = new RecyclerAdapter(newspaperLocationModelArrayList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        ((RecyclerAdapter) mAdapter).setOnItemClickListener(new RecyclerAdapter.NewspaperCardViewClickListener() {

            @Override
            public void onItemClick(int postion, View v) {
                Log.i("Newspaper", "Clicked on Item " + postion);

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("latitude", newspaperLocationModelArrayList.get(postion).getLatitude());
                intent.putExtra("longitude", newspaperLocationModelArrayList.get(postion).getLongitude());
                intent.putExtra("details", newspaperLocationModelArrayList.get(postion).getDetails());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
