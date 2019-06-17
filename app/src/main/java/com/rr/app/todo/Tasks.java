package com.rr.app.todo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {
    RelativeLayout layout;
    AnimationDrawable anim;
    static Task_Provider tp;
    TextView t;
    RecyclerView rv;
    static RecyclerView.Adapter rv_adapter;
    RecyclerView.LayoutManager rv_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);


        rv = findViewById(R.id.RecyclerView);
        tp = new Task_Provider(this);
        rv_manager = new LinearLayoutManager(this);
        rv_adapter = new RV_adapter_tasks(Task_Provider.task);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(rv_manager);
        rv.setAdapter(rv_adapter);

        layout = findViewById(R.id.layout_task);
        anim = (AnimationDrawable) layout.getBackground();
        anim.setEnterFadeDuration(5000);
        anim.setExitFadeDuration(3000);
        anim.start();

    }
}
