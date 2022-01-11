package com.example.nba;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//https://stackoverflow.com/questions/28413977/use-same-recycler-adapter-for-inflating-different-activities
public class Compare_Stats extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private RequestQueue requestQueue;
    private TextView field_goal_pct;
    private TextView free_throw_pct;
    private TextView three_point_pct;
    private TextView playerID;
    private TextView person_name;
    private String player_name;
    private int player_id;
    Spinner spinner;
    String text;
    ArrayAdapter<CharSequence> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats); //allows 'findViewById' to set R.id
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        player_id = getIntent().getIntExtra("id",0);
        player_name = getIntent().getStringExtra("fullname");
        person_name = findViewById(R.id.FullName);
        playerID = findViewById(R.id.PlayerID);
        field_goal_pct = findViewById(R.id.field_goal_pct);
        free_throw_pct = findViewById(R.id.free_throw_pct);
        three_point_pct = findViewById(R.id.three_point_pct);
        spinner = findViewById(R.id.spinner2);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.year_array, android.R.layout.simple_spinner_item);
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // will use a pre-defined adapter class; gives layout of control
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //gives layout of choices on dropdown
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    //in the future perhaps pre-load the data so we don't have to load the players everytime we open the team? Saved preferences?
    public void LoadPlayers(String year){

field_goal_pct.setText("");
        //issue is with player_id, not the variable. Perhaps the variabl id does not exist?
        Log.v("year","year: " + year);
        String url = "https://www.balldontlie.io/api/v1/season_averages?season=" + year + "&player_ids[]=236";
        //Some of the player id do not have data, perhaps because these are older players whose data may not be on file
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("data"); //assuming "statistics" is the output
                    int len = results.length();
                    Log.v("len", "len: " + len);
                    for(int i = 0; i < len; i++) {
                        JSONObject data = results.getJSONObject(i); //note curly braces represent start of JSON object while brackets represent
                        //the start of a JSON array

                        //Q: Why does the 'field_goal' need to be a String in order for it be displayed on 'setText'?
                        // Maybe the variable used must be a string, can use: setText(String.valueOf(int)) ?
                        String field_goal = "field goal ratio " + data.getDouble("fg_pct");
                        Log.v("field","field " + field_goal);
                        field_goal_pct.setText(field_goal);

                        String free_goal = "Free throw ratio " + data.getDouble("ft_pct");
                        free_throw_pct.setText(free_goal);

                        String three_goal = "three pointer ratio: " + data.getDouble("fg3_pct");
                        three_point_pct.setText(three_goal);
                    }
                    person_name.setText(player_name);
                    playerID.setText(String.valueOf(player_id));
                } catch (JSONException e) {
                    Log.e("cs50", "Json error", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("cs50", "NBA list error", error);
            }
        });

        requestQueue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
     text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        LoadPlayers(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
text = "2018";
LoadPlayers(text);
    }
}