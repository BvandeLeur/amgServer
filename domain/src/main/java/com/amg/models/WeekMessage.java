package com.amg.models;


public class WeekMessage {
   private Week thisweek;


    public WeekMessage(Week week){
        thisweek = week;
    }

    public Week getThisweek(){return thisweek;}
}
