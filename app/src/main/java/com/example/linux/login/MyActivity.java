package com.example.linux.login;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by linux on 11/28/16.
 */

public class MyActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey();
    }
public  void printHashKey(){

    try {
        PackageInfo info = getPackageManager().getPackageInfo(
                "com.example.linux.login",
                PackageManager.GET_SIGNATURES);
        for (Signature signature : info.signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            Log.d("linux", Base64.encodeToString(md.digest(), Base64.DEFAULT));
        }
    } catch (PackageManager.NameNotFoundException e) {

    } catch (NoSuchAlgorithmException e) {

    }
}

}
