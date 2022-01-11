package com.example.nba;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class NBA_Adapter extends RecyclerView.Adapter<NBA_Adapter.NBAViewHolder> implements Filterable {

public static class NBAViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView textView;
        //private com.example.nba.state state;
        state state = new state();

        NBAViewHolder(View view) { //constructer

            super(view);
            Log.v("cs50", "NBAViewHolder Constructer");

            //connects Java file to the XML resource files
            containerView = view.findViewById(R.id.nba_row);
            textView = view.findViewById(R.id.nba_row_text_view);
            //setOnClickListener accepts an instance of a class as an argument
            containerView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) { //on clicking the TEAMS will transfer the url to the "TEAMSActivity" class
                    TEAMS current = (TEAMS) containerView.getTag();

                    //an intent is a "glue" between activities, connecting them. Here the intent is transferring the
                    //url that contains the properties of the TEAMS to the class "TEAMSActivity".
                    //can possibly create a new adapter class
                    //state.setstate(true);
                     state.setstate(true);

                    Log.v("cs10","" + state.getstate());
                    Intent intent = new Intent(v.getContext(), SecondMain.class);
                    intent.putExtra("id", current.getId());
                    //The startActivity(Intent) method is used to start a new activity, which will be placed at the top of the activity stack.
                    // It takes a single argument, an Intent, which describes the activity to be executed
                    //Q: how come I didn't need to define the data type of the argument being passed into the intent?
                    v.getContext().startActivity(intent);
                }
            });
        }

    }


    List<TEAMS> teams = new ArrayList<>(); //array list of all teams given by full name
    List<TEAMS> filtered = new ArrayList<>();
    //List<Players> PlayersList = new ArrayList<>();

    //List<TEAMS> filteredTeams = new ArrayList<>();
    private RequestQueue requestQueue;

    //constructer for NBA_Adapter class
    public NBA_Adapter(Context context) {
        Log.d("Adapter2", "This is my message");

        //allows volley to make a request on behalf of my application
        requestQueue = Volley.newRequestQueue(context);
            loadTeam();
    }


    //list of teams in main hardcode,
//https://www.youtube.com/watch?v=CTvzoVtKoJ8
    public void loadTeam() {
        Log.v("cs50", "loading team");

        String url = "https://www.balldontlie.io/api/v1/teams";
        //teams.add(new TEAMS("Charlotte",99));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) { //the entire result is one JSONObject, within that JSONObject is an array
                try { //so far only returns one team, need to return limit = 50
                    JSONArray results = response.getJSONArray("data"); //assuming "statistics" is the output
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);
                        String name = result.getString("full_name");
                        Integer team_id = result.getInt("id");

                        teams.add(new TEAMS(name.substring(0, 1).toUpperCase() + name.substring(1), team_id
                                ) //adding the url to the TEAMS variable class
                        );
                    }

                    notifyDataSetChanged(); //method of RecyclerView.adapter
                } catch (JSONException e) {
                    Log.e("cs50", "Json error", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("cs50", "NBA list error", error);
            }
        }); //note that this is the end of the JasonObjectRequest; The two methods are the "onResponse" and "onErrorResponse" methods
        //state = false;
        //We’ll add our request to the queue, so we can actually load data.
        //Requests do the parsing of raw responses and Volley takes care of dispatching the parsed response back to the main thread for delivery.
        //To use Volley, you must add the android.permission.INTERNET permission to your app's manifest. Without this, your app won't be able to connect to the network.
        requestQueue.add(request);
    }

    private class NBAFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<TEAMS> filteredTeams = new ArrayList<>();

            // implement your search here!
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                filteredTeams.addAll(teams);
            }
            else {
                for (TEAMS name : teams) {
                    if (name.getName().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        filteredTeams.add(name);
                        //results.values = filteredTeams; // you need to create this variable!
                        //results.count = filteredTeams.size();
                    }
                }
            }
            results.values = filteredTeams; // you need to create this variable!
            results.count = filteredTeams.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filtered = (List<TEAMS>) results.values;

            notifyDataSetChanged();
        }

    }

    @Override //will get called initially
    public Filter getFilter() {
        return new NBAFilter(); //meaning to create a new object of the class with an argument you have passed on
    }

    //methods of RecyclerView.adapter are "onCreateViewHolder"
    @NonNull
    @Override
    public NBAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //seperates between list of teams and list of players
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nba_row, parent, false);
        //view.setOnClickListener(mOnClickListener);

        return new NBAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NBAViewHolder holder, int position) {
        //System.out.println("inside onBindViewHolder");
        TEAMS current;
        if(filtered.size() == 0){
        current = teams.get(position);
        //will display the name on the textView
        holder.textView.setText(current.getName());
        //setTag will tag each view so that we can use the tag to get the view later as shown at the top (onClickListener)
        holder.containerView.setTag(current);
    }
        else{
            current = filtered.get(position);
            holder.textView.setText(current.getName());
            holder.containerView.setTag(current);
        }
    }
//Returns the total number of items in the data set held by the adapter.
    @Override
    public int getItemCount() { //the problem is that the filtered method is called initially so there is no seperation between T and F
        Log.v("cs50", "team:  " + filtered.size());
        //problem is filtered.size is zero
        if(filtered.size() == 0) {
            return teams.size();
        }
        else{
            return filtered.size();
        }

    }

}
