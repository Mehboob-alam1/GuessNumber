package com.mehboob.guessnumber.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mehboob.guessnumber.R;
import com.mehboob.guessnumber.SessionManager;
import com.mehboob.guessnumber.recievers.CheckInternetConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private String text;
    private static final String TAG = "MainActivity";
//    private AppCompatButton button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sessionManager = new SessionManager(this);
      //  button = findViewById(R.id.btnGetStarted);
        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                if (sessionManager.fetchText()==null){
                    Toast.makeText(MainActivity.this, "Try checking your internet and try again", Toast.LENGTH_SHORT).show();
                    CheckInternetConnection.showNoInternetDialog(MainActivity.this);
                }
                if (sessionManager.fetchText().equals("aaa") || text.equals("aaa")){
                    startActivity(new Intent(MainActivity.this,GuessNumberActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this,ChromeCustomTabsActivity.class));
                }


            }


        }, 5 * 1000); // wait for 5 seconds
        if (CheckInternetConnection.isOnline(MainActivity.this)) {

            if (sessionManager.fetchText() == null)
                callApi();
            else {
//                button.setVisibility(View.VISIBLE);
                Log.d(TAG, "Text Added to session manager");
            }
        } else {
            CheckInternetConnection.showNoInternetDialog(this);
            Log.d(TAG, "No internet Connection");
        }







    }

    private void callApi() {
        String myUrl = "https://dl.dropboxusercontent.com/s/3qc1b3ustw5omss/test2.txt?dl=0";
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {
                    try {
                        //Create a JSON object containing information from the API.
                        JSONObject myJsonObject = new JSONObject(response);
                        text = myJsonObject.getString("abcde");
                        sessionManager.saveText(text);
//                        button.setVisibility(View.VISIBLE);
                        Log.d(TAG, text);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> {
                    Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
//                    button.setVisibility(View.INVISIBLE);
                    Log.d(TAG, volleyError.getMessage());
                }


        );


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }


}