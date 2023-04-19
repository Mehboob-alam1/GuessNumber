package com.mehboob.guessnumber;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {


    private SharedPreferences sharedPreferences;
    private static final String TEXT = "TEXT";
    private static final String NUMBER_CLICKED = "NUMBER_CLICKED";
    private static final String IS_SECOND = "IS_SECOND";
    private static final String IS_RATED = "IS_RATED";



    public SessionManager(Context context) {

        sharedPreferences = context.getSharedPreferences(TEXT, Context.MODE_PRIVATE);
        sharedPreferences = context.getSharedPreferences(NUMBER_CLICKED, Context.MODE_PRIVATE);
        sharedPreferences = context.getSharedPreferences(IS_SECOND, MODE_PRIVATE);
        sharedPreferences = context.getSharedPreferences(IS_RATED, MODE_PRIVATE);

    }
    public void saveIsRated(boolean isRated) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_RATED, isRated);
        editor.apply();
    }

    public boolean fetchIsRated() {

        return sharedPreferences.getBoolean(IS_RATED, false);
    }

    public void saveIsSecond(boolean isSecond) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_SECOND, isSecond);
        editor.apply();
    }


    public boolean fetchIsSecond() {
        return sharedPreferences.getBoolean(IS_SECOND, false);
    }

    public void saveNumber(String number) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NUMBER_CLICKED, number);
        editor.apply();
    }

    public String fetchNumber() {

        return sharedPreferences.getString(NUMBER_CLICKED, null);
    }

    public void saveText(String text) {
        SharedPreferences.Editor editor =
                sharedPreferences.edit();

        editor.putString(TEXT, text);
        editor.apply();
    }

    public String fetchText() {

        return sharedPreferences.getString(TEXT, null);
    }
}
