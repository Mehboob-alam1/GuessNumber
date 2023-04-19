package com.mehboob.guessnumber.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.mehboob.guessnumber.ListOfNumbers;
import com.mehboob.guessnumber.NumberHandler;
import com.mehboob.guessnumber.R;
import com.mehboob.guessnumber.SessionManager;
import com.mehboob.guessnumber.adapters.TextAdapter;

import java.util.ArrayList;
import java.util.Random;

public class GuessNumberActivity extends AppCompatActivity {
    private ArrayList<ModelNumbers> list;
    private TextAdapter adapter;
    private RecyclerView recyclerView;
    private AppCompatButton button;
    private int randomNumber;
    private static final String TAG = "GuessNumberActivity";
    private SessionManager sessionManager;
    private    AlertDialog alert,alert1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mai);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Guess");

        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.btnStart);
        sessionManager = new SessionManager(this);
        randomNumber = (int) (Math.random() * 37);

        Log.d(TAG, String.valueOf(randomNumber));

        list = new ArrayList<>();
        list.addAll(ListOfNumbers.getList());


        adapter = new TextAdapter(list, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setAdapter(adapter);

//        int number = Integer.parseInt(sessionManager.fetchNumber());
        Log.d(TAG, "Random Number"+String.valueOf(randomNumber));
        Log.d(TAG, "Class Value"+ NumberHandler.user_selected);
        button.setOnClickListener(view -> {



            if (NumberHandler.user_selected==randomNumber){
                showCongrats();
            }else{
               showSorry();
            }
        });


    }
    public void showCongrats() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final View customLayout = LayoutInflater.from(this).inflate(R.layout.congrats_dialog, null);

        alertDialog.setView(customLayout);

        // send data from the AlertDialog to the Activity


        AppCompatButton button = customLayout.findViewById(R.id.btnGotIt);

        button.setOnClickListener(view -> {

            alert.cancel();
            finish();


//showRateApp();

        });

        alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();



    }

    public void showSorry() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final View customLayout = LayoutInflater.from(this).inflate(R.layout.sorry_dialog, null);

        alertDialog.setView(customLayout);

        // send data from the AlertDialog to the Activity


        AppCompatButton button = customLayout.findViewById(R.id.btnTryAgain);

        button.setOnClickListener(view -> {

            alert1.cancel();



//showRateApp();

        });

        alert1 = alertDialog.create();
        alert1.setCanceledOnTouchOutside(false);
        alert1.show();



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //do whatever
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}