package com.example.mwismer.lab1;

import android.app.Activity;
import android.os.Bundle;


public class Lab1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MyFragment())
                    .commit();
        }
    }
}
