package com.thedevgeeks.launcher;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isPackageExisted("org.xbmc.kodi")) {
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("org.xbmc.kodi");
            startActivity(LaunchIntent);
            finish();
        }
        check();
    }

    public void check() {
        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                if (isPackageExisted("org.xbmc.kodi")) {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("org.xbmc.kodi");
                    startActivity(LaunchIntent);
                    finish();
                } else {
                    check();
                }
            }
        }.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isPackageExisted("org.xbmc.kodi")) {
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("org.xbmc.kodi");
            startActivity(LaunchIntent);
            finish();
        }
        check();
    }

    public boolean isPackageExisted(String targetPackage) {
        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }
}
