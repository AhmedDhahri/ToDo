package com.rr.app.todo;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Add extends AppCompatActivity {

    RelativeLayout layout;
    AnimationDrawable anim;
    Task_Provider tp;
    EditText h1;
    EditText h2;
    EditText m1;
    EditText m2;
    EditText name;
    int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tp = new Task_Provider(this);

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        year = today.year;
        month = today.month;
        day = today.monthDay;

        h1 = findViewById(R.id.hourf);
        m1 = findViewById(R.id.minf);
        h2 = findViewById(R.id.hourt);
        m2 = findViewById(R.id.mint);
        name = findViewById(R.id.name);


        layout = findViewById(R.id.layout_add);
        anim = (AnimationDrawable) layout.getBackground();
        anim.setEnterFadeDuration(5000);
        anim.setExitFadeDuration(3000);
        anim.start();


        final CalendarView c = findViewById(R.id.cld);
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                year = y;
                month = m;
                day = d;
            }
        });
    }

    public void add(View view) {
        new wait().execute();
        tp.save_task(new Task_object(year,month,day,Integer.parseInt(h1.getText().toString())
                ,Integer.parseInt(m1.getText().toString()),Integer.parseInt(h2.getText().toString())
                ,Integer.parseInt(m2.getText().toString()),name.getText().toString()));


    }
    class wait extends AsyncTask<Void,Void,Void>{
        RelativeLayout l;
        wait(){
            l = findViewById(R.id.wait);
        }

        protected void onPreExecute(){
            l.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void v) {
            l.setVisibility(View.INVISIBLE);
        }
    }
}
