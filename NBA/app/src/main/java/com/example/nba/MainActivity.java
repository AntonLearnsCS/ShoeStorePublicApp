package com.example.nba;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.MergeAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.Locale;

import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter;

//AppCompatActivity is a base class for activities that wish to use some of the newer platform features on older Android devices.
//SearchView.OnQueryTextListener for callbacks for changes to the query text. Methods are "onQueryTextChange" and "onQueryTextSubmit" see bottom
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
state state = new state();

    private NBA_Adapter adapter;
    private RecyclerView recyclerView;


    //The options menu is the primary collection of menu items for an activity. It's where you should place actions that have a global
    // impact on the app, such as "search," "Compose email," and "Settings."
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //will be called initially
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    //The "onCreate" activity class takes care of creating a window for you in which you can place your UI with setContentView(View).
    // onCreate(Bundle) is where you initialize your activity. Most importantly, here you will usually call setContentView(int)
    // with a layout resource defining your UI, and using findViewById(int) to retrieve the widgets in that UI that you need to
    // interact with programmatically.


    @Override
    public void onCreate(Bundle savedInstanceState) {//originally 'protected'

        //mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        super.onCreate(savedInstanceState);

        //allows recyclerView to exist in the layout
        //allows 'findViewById' to set R.id, only implement in classes extending AppCompatActivity
        setContentView(R.layout.activity_main);
        //instantiates the parameters
        recyclerView = findViewById(R.id.recycler_view);
        //mrecyclerView = findViewById(R.id.recycler_view1);

        //RecyclerViewMergeAdapter mergeAdapter = new RecyclerViewMergeAdapter();

//Try: https://stackoverflow.com/questions/26245139/how-to-create-recyclerview-with-multiple-view-type
        //rapter = new Player_Adapter(getApplicationContext());
        adapter = new NBA_Adapter(getApplicationContext());
        //layoutManager = new LinearLayoutManager(this);

        //MergeAdapter mergeAdapter = new MergeAdapter();
        //mrecyclerView.setAdapter(rapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        //specifies an adapter
        state.setstate(false);
//if(state.getstate() == true){mrecyclerView.setLayoutManager(layoutManager);}  //removing this line results in display of NBA teams initially }

//Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView.
recyclerView.setAdapter(adapter);

    }

    //onQueryTextChange and onQueryTextSubmit are pre-defined methods, nicely enough! We can implement both to take into account changing our search query.
    @Override
    public boolean onQueryTextChange(String newText) {
        //initially called
        Log.v("QueryTextChange","" + state.getstate());
        if(state.getstate() == false){ adapter.getFilter().filter(newText);} //mergeadapter does not have its own filter
        else{adapter.getFilter();}
            return false;
    }
    @Override
    public boolean onQueryTextSubmit(String newText) {
        if(state.getstate() == false){adapter.getFilter().filter(newText); }
        else{adapter.getFilter().filter(newText);}
            return false;
    }
}