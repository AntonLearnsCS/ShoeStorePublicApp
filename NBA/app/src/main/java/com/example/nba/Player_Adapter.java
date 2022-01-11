package com.example.nba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nba.Compare_Stats;
import com.example.nba.Players;
import com.example.nba.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class Player_Adapter extends RecyclerView.Adapter<Player_Adapter.PlayerViewHolder> implements Filterable,myInterface{
    private int team_id;
    private int test;
    private myInterface listener;
    private int copy;


//problem: need to pass intent to adapter class
// suggest using global method and accessing it from NBA_Adapter class.
    public class PlayerViewHolder extends RecyclerView.ViewHolder {


        public LinearLayout containerView;
        public TextView textView;

        PlayerViewHolder(View view) {

            super(view);

            containerView = view.findViewById(R.id.Player_List_row);
            textView = view.findViewById(R.id.Player_List_row_text_view);

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //on clicking the Players will transfer the url to the "PlayersActivity" class
                    Players current = (Players) containerView.getTag();
                    //an intent is a "glue" between activities, connecting them. He`re the intent is transferring the
                    //url that contains the properties of the Players to the class "PlayersActivity".
                    //can possibly create a new adapter class
                    Intent intent = new Intent(v.getContext(), Compare_Stats.class);
                    //we get the "fullName"
                    Log.v("check_id", "id: " + current.getPlayer_id());
                    intent.putExtra("id", current.getPlayer_id());
                    intent.putExtra("fullname",current.FullName());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public boolean state;
    //List<Players> PlayersList = new ArrayList<>(); //array list of all PlayersList given by full name
    List<Players> filtered = new ArrayList<>();
    List<Players> PlayersList = new ArrayList<>();

    private RequestQueue requestQueue;

    //takes in the "id" from the intent of the NBA_Adapter class and places it into test?
    public Player_Adapter(Context context, int test) {
        requestQueue = Volley.newRequestQueue(context);
        this.test = test;
        LoadPlayers();
    }

    public void LoadPlayers() {
        Log.v("cs100", "team id: hello  " + test);
        for (int i = 1; i <= 9; i++){
        //Log.v("cs100","loading");
            //problem gets players from previous seasons; only want season=2019 seasons[]=2018&seasons[]=2017
            //can try searching from the stats endpoint and load player names through there so that you can start using the season parameter, which may not
            //be available in the players endpoint
        String url = "https://www.balldontlie.io/api/v1/players?seasons[]=2018&seasons[]=2015&page=" + i; //"https://www.balldontlie.io/api/v1/season_averages";//"https://www.balldontlie.io/api/v1/stats";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try { //so far only returns one team, need to return limit = 50
                    JSONArray results = response.getJSONArray("data"); //assuming "statistics" is the output
                    Log.v("cs50", "Bigarray " + results); //there are objects being loaded,
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);
                        //Not all players will have height_feet, height_inches, or weight_pounds.
                        JSONObject team = result.getJSONObject("team");
                        Log.v("cs100","team: " + team);
                        int id = team.getInt("id");
                        Log.v("cs501", "player: " + result.getString("first_name"));
//so it's only displaying one page, need to display multiple pages
                        if (id == test) {
                            String fullname = result.getString("first_name") + " " + result.getString("last_name");

                            PlayersList.add(new Players(fullname,
                                    team.getString("full_name"), result.getInt("id")));
                        }


                   }
                    notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e("cs50", "Json error", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("cs50", "Player list error", error);
            }
        });

        requestQueue.add(request);
        //Log.v("cs100", "list: " + PlayersList);
    }
    }
    @Override //will get called initially
    public Filter getFilter() {
        return new PlayerFilter(); //meaning to create a new object of the class with an argument you have passed on
    }

    private class PlayerFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            state = false;
            List<Players> filteredPlayers = new ArrayList<>();

            // implement your search here!
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                filteredPlayers.addAll(PlayersList);
                //results.values = filteredPlayers;
                //results.values = Players; //perhaps cannot access because Players is private
                //results.count = filteredPlayers.size();

            } else {
                //List<Players> filtered = new ArrayList<>();
                for (Players name : PlayersList) {
                    if (name.FullName().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        filteredPlayers.add(name);
                        //results.values = filteredPlayers; // you need to create this variable!
                        //results.count = filteredPlayers.size();
                    }
                }
            }
            results.values = filteredPlayers; // you need to create this variable!
            results.count = filteredPlayers.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filtered = (List<Players>) results.values;

            notifyDataSetChanged();
        }

    }




    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //seperates between list of PlayersList and list of players

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Players current;// = PlayersList.get(position); //used to be filtered.get(position)
//Log.v("cs100", "size" + filtered.size());
        if(filtered.size() == 0){
            current = PlayersList.get(position);
            holder.textView.setText(current.FullName()); //will display the name on the textView
            //setTag will tag each view so that we can use the tag to get the view later as shown at the top (onClickListener)
            holder.containerView.setTag(current);
            //nba_row_text_view
        }
        else{
            current = filtered.get(position);
            holder.textView.setText(current.FullName()); //will display the name on the textView
            //setTag will tag each view so that we can use the tag to get the view later as shown at the top (onClickListener)
            holder.containerView.setTag(current);
        }
    }

    @Override
    public int getItemCount() { //the problem is that the filtered method is called initially so there is no seperation between T and F
Log.v("cs100", "item: " + PlayersList.size());
        if(filtered.size() == 0) {
            return PlayersList.size();
        }
        else{
            return filtered.size();
        }
       // return PlayersList.size();
    }
}
