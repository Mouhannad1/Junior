package com.example.mouhannad.drawer;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by eyadalama on 2/19/15.
 */
public class MyApplication extends Application {

    public ArrayList<Actors> globalActors = null;

    public MyApplication() {
        globalActors = new ArrayList<Actors>();
    }
}