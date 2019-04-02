package com.example.sherisesinyeelam.java4kids.GamesPage.ChoosingGame;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.LocalSharedPrefManager;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.RequestHandler;
import com.example.sherisesinyeelam.java4kids.ProgressPage.UserLessonProgressUpdate;
import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;
import com.example.sherisesinyeelam.java4kids.UserProfilePage.UserInfoUpdate;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class TheChosingGameActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    Dialog dialog;
    ProgressDialog progressDialog;
    TextView theQuestion;
    Button option1, option2, option3, option4, option5, option6;
    LinearLayout linearLayout1_btn, linearLayout2_btn;
    TextView score_and_answered;

    Lesson1_Inherirtance lesson1 = new Lesson1_Inherirtance();

    int current_question = 1;
    int number_of_questions = 5;
    int userChoice = 0;
    int answeredCorrectly = 0;
    int score = 0;
    boolean exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_chosing_game);
        autoChangeBackground();
        mToolbar = (Toolbar) findViewById(R.id.c_game_toolbar);
        setSupportActionBar(mToolbar);

        exit = true;
        dialog = new Dialog(this);
        lessonDescription_popUpOpen(true);
        progressDialog = new ProgressDialog(this);

        theQuestion = (TextView) findViewById(R.id.question_in_choosing_game);
        linearLayout1_btn = (LinearLayout) findViewById(R.id.button_top_row);
        option1 = (Button) findViewById(R.id.c_option1);
        option2 = (Button) findViewById(R.id.c_option2);
        option3 = (Button) findViewById(R.id.c_option3);
        linearLayout2_btn = (LinearLayout) findViewById(R.id.button_bottom_row);
        option4 = (Button) findViewById(R.id.c_option4);
        option5 = (Button) findViewById(R.id.c_option5);
        option6 = (Button) findViewById(R.id.c_option6);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        option6.setOnClickListener(this);
        score_and_answered = (TextView) findViewById(R.id.c_current_question_number);

        setTheQuestionToShow();
    }

    public void setTheQuestionToShow(){

        if(lesson1.questions().get(current_question) != null) {
            theQuestion.setText(Html.fromHtml(lesson1.questions().get(current_question)));
            correspondingOptions();
        }

        // todo: level 2 question e.g "Question 3: \n Read the following code, which is its correct output?";
    }

    public void correspondingOptions(){

        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);
        option5.setVisibility(View.VISIBLE);
        option6.setVisibility(View.VISIBLE);

        if(lesson1.options().get(current_question).length == 6) {
            option1.setText(lesson1.options().get(current_question)[0]);
            option2.setText(lesson1.options().get(current_question)[1]);
            option3.setText(lesson1.options().get(current_question)[2]);
            option4.setText(lesson1.options().get(current_question)[3]);
            option5.setText(lesson1.options().get(current_question)[4]);
            option6.setText(lesson1.options().get(current_question)[5]);
        } else if (lesson1.options().get(current_question).length == 2){
            option1.setText(lesson1.options().get(current_question)[0]);
            option2.setVisibility(View.INVISIBLE);
            option3.setText(lesson1.options().get(current_question)[1]);
            option4.setVisibility(View.INVISIBLE);
            option5.setVisibility(View.INVISIBLE);
            option6.setVisibility(View.INVISIBLE);
        }
    }

    // if correct, answered = true, if wrong, .add(questionNum) back to the arraylist.
    public void checkAnswer(){
        if(userChoice == lesson1.answers().get(current_question)){
            answeredCorrectly++;
            score+=100;
        }
        score_and_answered.setText("score: " + score + "   question: "+ (current_question+1) + "/5");
        current_question++;
    }

    // checking which button does the user clicked.
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.c_option1:
                userChoice = 1;
                break;
            case R.id.c_option2:
                userChoice = 2;
                break;
            case R.id.c_option3:
                userChoice = 3;
                break;
            case R.id.c_option4:
                userChoice = 4;
                break;
            case R.id.c_option5:
                userChoice = 5;
                break;
            case R.id.c_option6:
                userChoice = 6;
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
        // go to next question after clicking one answer button.
        checkAnswer();
        if(current_question > number_of_questions){
            Toast.makeText(TheChosingGameActivity.this, "Level 1 complete!", Toast.LENGTH_SHORT).show();
            // show new popup, informing the stage clear and score and number of wrong and correct and store details.
            stageSummary_popUpOpen();
        } else {
            // go to next question after clicking one answer button.
//            checkAnswer();
            setTheQuestionToShow();
        }
    }

    // pop up display about the lesson and show game instruction.
    public void lessonDescription_popUpOpen(final boolean exit){
        dialog.setContentView(R.layout.custompopup_lesson_description);

        TextView closePopUp = (TextView) dialog.findViewById(R.id.close_popup);
        Button okay_btn = (Button) dialog.findViewById(R.id.okay_button);
        ImageView lesson_Icon = (ImageView) dialog.findViewById(R.id.lesson_icon);
        TextView lessonTopic = (TextView) dialog.findViewById(R.id.lesson_topic);
        TextView lessonDescription = (TextView) dialog.findViewById(R.id.lesson_description);

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(exit == true){
                    onBackPressed();
                }
            }
        });

        lesson_Icon.setImageResource(lesson1.lesson_icon());
        // lesson_topic
        lessonTopic.setText(lesson1.lesson_name());
        // lesson_description
        String description = lesson1.lesson_description() + "<br/><br/><br/>" +
                "<u><b>How to play the game:</b></u><br/>" +
                "Read the question and chose the correct answer by clicking the right answer.";
        lessonDescription.setText(Html.fromHtml(description));

        okay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // pop up displays the score gained in quiz number of correct and wrong answers that have been made.
    public void stageSummary_popUpOpen(){
        dialog.setContentView(R.layout.custompopup_quiz_summary);

        TextView closePopUp = (TextView) dialog.findViewById(R.id.close_popup);
        Button done_btn = (Button) dialog.findViewById(R.id.done_button);
        ImageView stageClear_image = (ImageView) dialog.findViewById(R.id.stage_clear_image);
        TextView lessonTopic = (TextView) dialog.findViewById(R.id.lesson_topic);
        TextView stageSummary = (TextView) dialog.findViewById(R.id.stage_summary);

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onBackPressed();
            }

        });
        stageClear_image.setImageResource(lesson1.lesson_icon());
        // lesson_topic, lesson_summary
        lessonTopic.setText(lesson1.lesson_name());
        String summary = "<b>Answered correctly</b>: " + answeredCorrectly +
                         "<br/><br/><b>Answered wrong</b>: " + (number_of_questions - answeredCorrectly) +
                         "<br/><br/><b>Score</b>: " + score;
        stageSummary.setText(Html.fromHtml(summary));

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String l1Completeness = "Not Started";
                if(answeredCorrectly == number_of_questions){
                    l1Completeness = "Completed";
                } else if (answeredCorrectly > 0){
                    l1Completeness = "In progress";
                }
                if(SharedPrefManager.getInstance(TheChosingGameActivity.this).checkLoginOrNot()){
                    onScoreUpdateDB(v);
                    onProgressUpdate(l1Completeness);
                } else {
                    // do local update.
                    int currentScore = LocalSharedPrefManager.getInstance(TheChosingGameActivity.this).getUserTotalScore();
                    LocalSharedPrefManager.getInstance(TheChosingGameActivity.this)
                            .userScoreUpdate(currentScore+score);
                    LocalSharedPrefManager.getInstance(TheChosingGameActivity.this)
                            .lesson1ProgressUpdate(l1Completeness);
                    Toast.makeText(TheChosingGameActivity.this, "Total Score updated", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // creating in game dropdown menu for the game, allowing to recap lesson description and exit game.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ingame_dropdown_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // in game menu: exit game or recap lesson
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.lesson_recap:
                lessonDescription_popUpOpen(false);
                break;
            case R.id.exit_game:
                // so popup asking are you sure
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setMessage("You sure you want to leave this quiz? \nIf do so, current progress will be lost.");
                // setting negative "Cancel" button
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // setting positive "yes" button
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // update the new score to the database when completed the quiz.
    public void onScoreUpdateDB(View v){
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
                        if(SharedPrefManager.getInstance(TheChosingGameActivity.this).checkLoginOrNot()){
                            //if user already logged in.
                            SharedPrefManager.getInstance(TheChosingGameActivity.this)
                                    .userScoreUpdate(jsonResponse.getInt("totalScore"));
                        }
                        onBackPressed();
                    } else {
                        Toast.makeText(TheChosingGameActivity.this, jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(TheChosingGameActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
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

        int useriD = SharedPrefManager.getInstance(this).getUserID();
        int current_level = SharedPrefManager.getInstance(this).getUserLevel();
        int currentScore = SharedPrefManager.getInstance(this).getUserTotalScore();
        int userIcon = SharedPrefManager.getInstance(this).getUserIcon();
        UserInfoUpdate userInfoUpdate = new UserInfoUpdate(useriD, current_level,currentScore+score, userIcon, responseListener, errorListener);
        RequestHandler.getInstance(TheChosingGameActivity.this).addToRequestQueue(userInfoUpdate);
    }

    // update lesson progress to the database and local preferences: not started, in progress, completed
    public void onProgressUpdate(String l1Completeness){
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
                        if(SharedPrefManager.getInstance(TheChosingGameActivity.this).checkLoginOrNot()){
                            //if user already logged in.
                            SharedPrefManager.getInstance(TheChosingGameActivity.this)
                                    .lesson1ProgressUpdate(jsonResponse.getString("lesson1"));
                            SharedPrefManager.getInstance(TheChosingGameActivity.this)
                                    .lesson2ProgressUpdate(jsonResponse.getString("lesson2"));
                        }
                    } else {
                        Toast.makeText(TheChosingGameActivity.this, jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(TheChosingGameActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
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

        int useriD = SharedPrefManager.getInstance(TheChosingGameActivity.this).getUserID();
        String l2Complete = SharedPrefManager.getInstance(TheChosingGameActivity.this).getLesson2Complete();
        UserLessonProgressUpdate updateUserProgress = new UserLessonProgressUpdate(useriD, l1Completeness,l2Complete, responseListener, errorListener);
        RequestHandler.getInstance(TheChosingGameActivity.this).addToRequestQueue(updateUserProgress);
    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final LinearLayout layout = (LinearLayout) findViewById(R.id.chosing_game_default_background);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        TheChosingGameActivity.super.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar c = Calendar.getInstance();
                                int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
                                if(timeOfDay >= 7 && timeOfDay < 17){
                                    layout.setBackgroundResource(R.color.LightSkyBlue);
                                }
                                else{
                                    layout.setBackgroundResource(R.color.PaleGoldenrod);
                                }
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {}
            }
        };
        t.start();

    }
}

// Aws Rh (2017). Custom Pop Up Window with Android Studio + source code [online]. available at https://awsrh.blogspot.com/2017/10/custom-pop-up-window-with-android-studio.html [accessed 30/01/2019]
