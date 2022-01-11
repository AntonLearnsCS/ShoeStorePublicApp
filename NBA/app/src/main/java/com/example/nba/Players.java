package com.example.nba;

public class Players {
    //first name, last name, height, team_name
    private String FullName;
    //private int height;
    private String team_name;
    private  int player_id;
    //private String url;

    Players(String FullName, String team_name, int player_id) { //initializes the String "TeamName" by setting the input String equal to the private String "TeamName"
        this.FullName = FullName;
        //this.height = height;
        this.team_name = team_name;
        this.player_id = player_id;
        //this.url = url;
    }

    public String FullName() {
        return FullName;
    }
    //public Integer getHeight(){ return height; }
    public String getTeam_name(){ return team_name;}
    public int getPlayer_id(){ return player_id; }

}
