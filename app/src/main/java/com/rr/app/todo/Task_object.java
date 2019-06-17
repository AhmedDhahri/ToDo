package com.rr.app.todo;


import org.json.JSONException;
import org.json.JSONObject;

public class Task_object {
    int year = 1970;
    int month = 1;
    int day = 1;
    int hourf = 0;
    int minf = 0;
    int hourt = 0;
    int mint = 0;
    String name;

    public Task_object(int y, int m, int d, int h1, int m1, int h2, int m2, String n){
        year = y;
        month = m;
        day = d;
        hourf = h1;
        minf = m1;
        hourt = h2;
        mint = m2;
        name = n;
    }
    public Task_object(JSONObject j){
        try {
            year = j.getInt("year");
            month = j.getInt("month");
            day = j.getInt("day");
            hourt = j.getInt("hourt");
            mint = j.getInt("mint");
            hourf = j.getInt("hourf");
            minf = j.getInt("minf");
            name = j.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSON(){
        JSONObject j = new JSONObject();
        try {
            j.put("year",year);
            j.put("month",month);
            j.put("day",day);
            j.put("hourt",hourt);
            j.put("mint",mint);
            j.put("hourf",hourf);
            j.put("minf",minf);
            j.put("name",name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }

}
