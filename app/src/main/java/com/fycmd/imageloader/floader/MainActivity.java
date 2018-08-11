package com.fycmd.imageloader.floader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fycmd.imageloader.floaderlib.FLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FLoader.getInstance().init(getApplication());
        FLoader.getInstance().load("");
    }
}
