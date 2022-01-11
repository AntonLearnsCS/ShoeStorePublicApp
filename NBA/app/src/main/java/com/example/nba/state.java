package com.example.nba;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

public class state extends AppCompatActivity {
    private int team_id;
    private boolean state;

    public boolean getstate() {
        return state;
    }

    public void setstate(boolean state) {
        this.state = state;
    }

}
