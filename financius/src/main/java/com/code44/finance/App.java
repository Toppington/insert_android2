package com.code44.finance;

import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

import dagger.ObjectGraph;
import hugo.weaving.DebugLog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import sdk.insert.io.*;

public class App extends Application {
    private ObjectGraph objectGraph;

    public static App with(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override public void onCreate() {

        Insert.initSDK(
                this,
                "92da87638f3232464241110cabbb01b7cebe4c0ac13b2733808726f285637b7e8bfd14be1857edf7a19cefdbbf4f53bd5616c9f1d4f6aef52caf5591c70245582f516b95947e796c62238641367a564c.cd13fa1fc8c372706cc8dd50d5380a4a.2d9baff8d20cadceb651584b86d4c8d5accf64cd25211367cf0db185cd37e176",
                "sthtest",
                null);

        super.onCreate();
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectAll()
//                .penaltyLog()
//                .penaltyDialog()
//                .build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectAll()
//                .penaltyDeath()
//                .penaltyLog()
//                .build());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                        .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
        buildObjectGraphAndInject();
        JodaTimeAndroid.init(this);
    }

    @DebugLog public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);
    }

    public void inject(Object o) {
        objectGraph.inject(o);
    }
}
