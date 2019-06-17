package com.rr.app.todo;

import android.content.Context;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Task_Provider {
    FileOutputStream outputStream;
    Context context;
    File file;
    String filename = "store0.json";

    static ArrayList<Task_object> task;
    static ArrayList<Today_object> today;
    public Task_Provider(Context c){
        context = c;
        task = getTasks();
        today = getToday();

    }

    void save_task(Task_object t){
        JSONArray j = getJSONArray();
        j.put(t.toJSON());
        write(j);
    }
    void save_tasks(ArrayList<Task_object> list){
        JSONArray j = new JSONArray();
        for(int i = 0;i<list.size();i++){
            j.put(list.get(i).toJSON());
        }
        write(j);
    }


    JSONArray getJSONArray(){
        String ret = "[]";
        InputStream inputStream = null;

        try {
            inputStream = context.openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }else{
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
                outputStreamWriter.write("[}");
                outputStreamWriter.close();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return new JSONArray(ret);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    void write(JSONArray j){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(j.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Today_object> getToday(){

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        ArrayList<Task_object> list = getTasks();
        ArrayList<Today_object> result = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            Task_object t = list.get(i);
            if((t.year == today.year) && (t.month == today.month) && (t.day == today.monthDay)) {
                result.add(new Today_object(i, t));
                Log.d("today index:   ",""+i);
            }
        }
        return result;
    }

    ArrayList<Task_object> getTasks(){
        JSONArray j = getJSONArray();
        ArrayList<Task_object> list = new ArrayList<>();
        for (int i = 0;i < j.length();i++){
            try {
                list.add(new Task_object((JSONObject) j.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
