package com.example.sherisesinyeelam.java4kids.GamesPage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.RequestHandler;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;
import com.example.sherisesinyeelam.java4kids.UserProfilePage.UserInfoUpdateRequest;
import com.example.sherisesinyeelam.java4kids.UserProfilePage.UserProgressUpdateRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class GameProgressHandler {

    Context context;
    ProgressDialog progressDialog;

    public GameProgressHandler(Context context, ProgressDialog progressDialog){
        this.context = context;
        this.progressDialog = progressDialog;

    }
    // update the new score to the database when completed the quiz.
    public void onScoreUpdateDB(View v, int score){
        progressDialog.setMessage("updating your score...");
        progressDialog.show();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                    JSONObject jsonResponse = new JSONObject(response);

                    if(!jsonResponse.getBoolean("error")){
                        // if error is false mean user successfully update score
                        if(SharedPrefManager.getInstance(context).checkLoginOrNot()){
                            //if user already logged in.
                            SharedPrefManager.getInstance(context)
                                    .userScoreUpdate(jsonResponse.getInt("totalScore"));
                        }
                    } else {
                        Toast.makeText(context, jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        };
        // cancel the progress dialog if it runs overtime.
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);

        int useriD = SharedPrefManager.getInstance(context).getUserID();
        int current_level = SharedPrefManager.getInstance(context).getUserLevel();
        int currentScore = SharedPrefManager.getInstance(context).getUserTotalScore();
        int userIcon = SharedPrefManager.getInstance(context).getUserIcon();
        UserInfoUpdateRequest userInfoUpdateRequest = new UserInfoUpdateRequest(useriD, current_level,currentScore+score, userIcon, responseListener, errorListener);
        RequestHandler.getInstance(context).addToRequestQueue(userInfoUpdateRequest);
    }

    // update lesson progress to the database and local preferences: not started, in progress, completed
    public void onProgressUpdate(String l1Completeness, String l2Completeness){
        progressDialog.setMessage("updating your progress...");
        progressDialog.show();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    if(!jsonResponse.getBoolean("error")){
                        // if error is false mean user successfully update score
                        if(SharedPrefManager.getInstance(context).checkLoginOrNot()){
                            //if user already logged in.
                            SharedPrefManager.getInstance(context)
                                    .lesson1ProgressUpdate(jsonResponse.getString("lesson1"));
                            SharedPrefManager.getInstance(context)
                                    .lesson2ProgressUpdate(jsonResponse.getString("lesson2"));
                        }
                    } else {
                        Toast.makeText(context, jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        };
        // cancel the progress dialog if it runs overtime.
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);

        int useriD = SharedPrefManager.getInstance(context).getUserID();
        UserProgressUpdateRequest updateUserProgress = new UserProgressUpdateRequest(useriD, l1Completeness,l2Completeness, responseListener, errorListener);
        RequestHandler.getInstance(context).addToRequestQueue(updateUserProgress);
    }
}
