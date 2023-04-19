package com.mehboob.guessnumber.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mehboob.guessnumber.Provide;
import com.mehboob.guessnumber.R;
import com.mehboob.guessnumber.SessionManager;

public class ChromeCustomTabsActivity extends AppCompatActivity {
    private LinearLayout googleButton, twitterButton;
    private CustomTabsIntent.Builder customIntent;
    private SessionManager sessionManager;
    private Provide provide;
    private static final String TAG = "ChromeCustomTabsActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome_custom_tabs);
        sessionManager = new SessionManager(this);
        provide=new Provide(this,this);

        getSupportActionBar().setTitle("Chrome Tabs");
        if (sessionManager.fetchIsSecond()) {

            if (sessionManager.fetchIsRated()) {
                Log.d(TAG, "Rated already");
            } else {
                provide.showAlertDialog();
            }
        } else {
            Log.d(TAG, "NO dialog to show");
            sessionManager.saveIsSecond(true);
        }
        googleButton = findViewById(R.id.btnGoogle);
        twitterButton = findViewById(R.id.btnTwitter);

        googleButton.setOnClickListener(view -> {
            String url = "http://www.google.com";
            CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();

            // below line is setting toolbar color
            // for our custom chrome tab.
            customIntent.setToolbarColor(ContextCompat.getColor(ChromeCustomTabsActivity.this, R.color.purple_200));

            // we are calling below method after
            // setting our toolbar color.
            openCustomTab(ChromeCustomTabsActivity.this, customIntent.build(), Uri.parse(url));
        });
        twitterButton.setOnClickListener(view -> {
            String url = "http://www.twitter.com";
            CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();

            // below line is setting toolbar color
            // for our custom chrome tab.
            customIntent.setToolbarColor(ContextCompat.getColor(ChromeCustomTabsActivity.this, R.color.purple_200));

            // we are calling below method after
            // setting our toolbar color.
            openCustomTab(ChromeCustomTabsActivity.this, customIntent.build(), Uri.parse(url));
        });
    }

    public void openCustomTab(Activity activity, CustomTabsIntent customTabsIntent, Uri uri) {
        // package name is the default package
        // for our custom chrome tab
        String packageName = "com.android.chrome";
        if (packageName != null) {

            // we are checking if the package name is not null
            // if package name is not null then we are calling
            // that custom chrome tab with intent by passing its
            // package name.
            customTabsIntent.intent.setPackage(packageName);


            // in that custom tab intent we are passing
            // our url which we have to browse.
            customTabsIntent.launchUrl(activity, uri);

        } else {
            // if the custom tabs fails to load then we are simply
            // redirecting our user to users device default browser.
            activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }

}