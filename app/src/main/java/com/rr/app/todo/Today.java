package com.rr.app.todo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Today extends AppCompatActivity {

    RelativeLayout layout;
    AnimationDrawable anim;
    RecyclerView rv;

    static RecyclerView.Adapter rv_adapter;
    RecyclerView.LayoutManager rv_manager;
    Task_Provider tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);


        layout = findViewById(R.id.layout_today);
        anim = (AnimationDrawable) layout.getBackground();
        anim.setEnterFadeDuration(5000);
        anim.setExitFadeDuration(3000);
        anim.start();

        tp = new Task_Provider(this);
        rv = findViewById(R.id.RecyclerViewToday);
        rv_manager = new LinearLayoutManager(this);
        rv_adapter = new RV_adapter_today(Task_Provider.today);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(rv_manager);
        rv.setAdapter(rv_adapter);
    }
}
