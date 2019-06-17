package com.rr.app.todo;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        anim = (AnimationDrawable) layout.getBackground();
        anim.setEnterFadeDuration(5000);
        anim.setExitFadeDuration(3000);
        anim.start();
    }

    public void today(View view) {
        Intent i = new Intent(this,Today.class);
        new launcher(view,i).execute();
    }

    public void task(View view) {
        Intent i = new Intent(this,Tasks.class);
        new launcher(view,i).execute();
    }

    public void add(View view) {
        Intent i = new Intent(this,Add.class);
        new launcher(view,i).execute();
    }
    class launcher extends AsyncTask<Void,Void,Void>{
        View v;
        Intent i;
        int anim_duration = 1000;
        AlphaAnimation anim;
        launcher(View v, Intent i){
            this.v = v;
            this.i = i;
        }

        @Override
        protected void onPreExecute(){

            anim = new AlphaAnimation(1.0f,0.1f);
            anim.setDuration(anim_duration);
            v.startAnimation(anim);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(anim_duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            startActivity(i);

        }
    }
}
