package com.example.nba;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//again we have another recycler view
public class SecondMain extends AppCompatActivity implements SearchView.OnQueryTextListener, myInterface {
    state state = new state();
    private int team_id;
    private int copy;
    private Player_Adapter rapter;
    private RecyclerView mrecyclerView;
    //private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //will be called initially
        //inflates the xml file
        getMenuInflater().inflate(R.menu.menu, menu);
        //We’re grabbing a reference to the item inside our menu using the ID "action_search"
        MenuItem searchItem = menu.findItem(R.id.action_search);
        //Returns the currently set action view for this menu item.
        SearchView searchView = (SearchView) searchItem.getActionView();
        //search code will be specified in this activity ("SecondMain")
        searchView.setOnQueryTextListener(this);
        return true;
    }

    //The "onCreate" activity class takes care of creating a window for you in which you can place your UI with setContentView(View).
    // onCreate(Bundle) is where you initialize your activity. Most importantly, here you will usually call setContentView(int)
    // with a layout resource defining your UI, and using findViewById(int) to retrieve the widgets in that UI that you need to
    // interact with programmatically.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //originally 'protected'
        team_id = getIntent().getIntExtra("id",0);

        Log.v("cs100", "" + team_id);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        //instantiates the parameters
        mrecyclerView = findViewById(R.id.mrecycler_view);
        //why wont player adapter initialize?
        //maybe because of manifest file
        //maybe because of adapter class
//Try: https://stackoverflow.com/questions/26245139/how-to-create-recyclerview-with-multiple-view-type
        rapter = new Player_Adapter(SecondMain.this, team_id);



        mrecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //specifies an adapter
        state.setstate(false);
//if(state.getstate() == true){mrecyclerView.setLayoutManager(layoutManager);}  //removing this line results in display of NBA teams initially }
        mrecyclerView.setAdapter(rapter);
    }

    //onQueryTextChange and onQueryTextSubmit are pre-defined methods, nicely enough! We can implement both to take into account changing our search query.
    @Override
    public boolean onQueryTextChange(String newText) {
        //initially called
        Log.v("QueryTextChange","" + state.getstate());
        if(state.getstate() == false){ rapter.getFilter().filter(newText);} //mergeadapter does not have its own filter
        else{rapter.getFilter();}
        return false;
    }
    @Override
    public boolean onQueryTextSubmit(String newText) {
        if(state.getstate() == false){rapter.getFilter().filter(newText); }
        else{rapter.getFilter().filter(newText);}
        return false;
    }
}