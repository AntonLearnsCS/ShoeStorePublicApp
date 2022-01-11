package com.example.nba;
//creating a datatype called "TEAMS"
public class TEAMS {
    private String TeamName;
    private Integer id;
    //private String url;

    TEAMS(String TeamName, Integer id) { //initializes the String "TeamName" by setting the input String equal to the private String "TeamName"
        this.TeamName = TeamName;
        this.id = id;
        //this.url = url;
    }

    public String getName() {
        return TeamName;
    }

    public Integer getId() {
        return id;
    }
}

