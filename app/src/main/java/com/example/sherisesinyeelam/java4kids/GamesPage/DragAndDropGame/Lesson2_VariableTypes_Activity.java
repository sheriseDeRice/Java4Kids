package com.example.sherisesinyeelam.java4kids.GamesPage.DragAndDropGame;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.GamesPage.GameProgressHandler;
import com.example.sherisesinyeelam.java4kids.LocalSharedPrefManager;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.RequestHandler;
import com.example.sherisesinyeelam.java4kids.UserProfilePage.UserProgressUpdateRequest;
import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;
import com.example.sherisesinyeelam.java4kids.UserProfilePage.UserInfoUpdateRequest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Lesson2_VariableTypes_Activity extends AppCompatActivity {

    Dialog dialog;
    ProgressDialog progressDialog;
    TextView question, score_and_answered;
    LinearLayout drop_target1, drop_target2, drop_target3, drop_target4, drop_target5;
    Button button1, button2, button3, button4, button5; // the place where to drop the piece.
    Button piece1, piece2, piece3, piece4, piece5; // the piece that to be dragged.

    Lesson2_content lesson2 = new Lesson2_content();

    int countPlaced = 0;
    int score = 0;
    int totalNumOfQuestion = 2;
    int currentQuestionNumber = 1;
    int answeredCorrectly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(this);
        lessonDescription_popUpOpen(true);
        progressDialog = new ProgressDialog(this);
        setLayout();
        setQuestions();
    }

    public void setLayout(){
        setContentView(R.layout.activity_the_drag_and_drop);
        autoChangeBackground();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.d_game_toolbar);
        setSupportActionBar(mToolbar);
        // the following are sample, followed the tutorial.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // question box
        question = (TextView) findViewById(R.id.d_question);
        score_and_answered = (TextView) findViewById(R.id.d_current_question_number);
        score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
        // target area
        drop_target1 = (LinearLayout) findViewById(R.id.drag_n_drop_target1);
        drop_target2 = (LinearLayout) findViewById(R.id.drag_n_drop_target2);
        drop_target3 = (LinearLayout) findViewById(R.id.drag_n_drop_target3);
        drop_target4 = (LinearLayout) findViewById(R.id.drag_n_drop_target4);
        drop_target5 = (LinearLayout) findViewById(R.id.drag_n_drop_target5);
        // place to drop
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        // piece to drag
        piece1 = (Button) findViewById(R.id.drag_piece1);
        piece2 = (Button) findViewById(R.id.drag_piece2);
        piece3 = (Button) findViewById(R.id.drag_piece3);
        piece4 = (Button) findViewById(R.id.drag_piece4);
        piece5 = (Button) findViewById(R.id.drag_piece5);
        // to go to next question
        //goNext = (Button) findViewById(R.id.d_next_question);

        drop_target1.setOnDragListener(dragListener);
        drop_target2.setOnDragListener(dragListener);
        drop_target3.setOnDragListener(dragListener);
        drop_target4.setOnDragListener(dragListener);
        drop_target5.setOnDragListener(dragListener);
        piece1.setOnLongClickListener(longClickListener);
        piece2.setOnLongClickListener(longClickListener);
        piece3.setOnLongClickListener(longClickListener);
        piece4.setOnLongClickListener(longClickListener);
        piece5.setOnLongClickListener(longClickListener);
    }

    public void setQuestions(){
        if(lesson2.questions().get(currentQuestionNumber) != null) {
            question.setText(Html.fromHtml(lesson2.questions().get(currentQuestionNumber)));
            setDropBox();
            setOptions();
        }
    }

    public void setDropBox(){
        button1.setText(lesson2.setDropArea().get(currentQuestionNumber)[0]);
        button2.setText(lesson2.setDropArea().get(currentQuestionNumber)[1]);
        button3.setText(lesson2.setDropArea().get(currentQuestionNumber)[2]);
        button4.setText(lesson2.setDropArea().get(currentQuestionNumber)[3]);
        button5.setText(lesson2.setDropArea().get(currentQuestionNumber)[4]);

    }

    public void setOptions(){
        piece1.setText(lesson2.setDragOptions().get(currentQuestionNumber)[0]);
        piece2.setText(lesson2.setDragOptions().get(currentQuestionNumber)[1]);
        piece3.setText(lesson2.setDragOptions().get(currentQuestionNumber)[2]);
        piece4.setText(lesson2.setDragOptions().get(currentQuestionNumber)[3]);
        piece5.setText(lesson2.setDragOptions().get(currentQuestionNumber)[4]);

    }

    //todo: think of a way to make this simpler
    // check if the correct piece is been dragged and dropped to a correct box.
    public boolean checkAnswer(View view, View v){
        if(currentQuestionNumber == 1){
            if(view.getId() == R.id.drag_piece1 && v.getId() == R.id.drag_n_drop_target3){
                // replace the blank to the correct answer.
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button3.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else if(view.getId() == R.id.drag_piece2 && v.getId() == R.id.drag_n_drop_target4) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button4.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else if(view.getId() == R.id.drag_piece3 && v.getId() == R.id.drag_n_drop_target5) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button5.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else if(view.getId() == R.id.drag_piece4 && v.getId() == R.id.drag_n_drop_target1) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button1.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else if(view.getId() == R.id.drag_piece5 && v.getId() == R.id.drag_n_drop_target2) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button2.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else {
                score -= 2;
            }
        } else if(currentQuestionNumber == 2){
            if(view.getId() == R.id.drag_piece1 && v.getId() == R.id.drag_n_drop_target4){
                // replace the blank to the correct answer.
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button4.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else if(view.getId() == R.id.drag_piece2 && v.getId() == R.id.drag_n_drop_target3) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button3.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else if(view.getId() == R.id.drag_piece3 && v.getId() == R.id.drag_n_drop_target2) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button2.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else if(view.getId() == R.id.drag_piece4 && v.getId() == R.id.drag_n_drop_target5) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button5.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else if(view.getId() == R.id.drag_piece5 && v.getId() == R.id.drag_n_drop_target1) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button1.setVisibility(View.GONE);
                newPiece.addView(view);
                score += 30;
                score_and_answered.setText("score: " + score + "   question: "+ currentQuestionNumber + "/" + totalNumOfQuestion);
                return true;
            } else {
                score -= 2;
            }
        }
        return false;
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData clipData = ClipData.newPlainText("","");
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(clipData, dragShadowBuilder, v, 0);
            return true;
        }
    };
    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
//                    if(view.getId() == R.id.drag_piece1){
//                        dropingArea.setText("Text Piece 1 is dropped.");
//                    } else if(view.getId() == R.id.drag_piece2){
//                        dropingArea.setText("Text Piece 2 is dropped.");
//                    } else if(view.getId() == R.id.drag_piece3){
//                        dropingArea.setText("Text Piece 3 is dropped.");
//                    }
//                    view.animate().x(dropingArea.getX())
//                                  .y(dropingArea.getY())
//                                  .setDuration(700)
//                                  .start();

                    // if only allow one view to be dropped
//                    if(view.getId() == R.id.drag_piece1){
//                        view.animate().x(dropingArea.getX())
//                                      .y(dropingArea.getY())
//                                      .setDuration(700)
//                                      .start();
//                    } else {
//                        dropingArea.setText("Wrong Answer!");
//                        // can also add sound:
//                        // - create "raw" directory in res, then paste the mp3.
//                        // - do MediaPlayer mediaPlayer = MediaPlayer.create(Lesson2_VariableTypes_Activity.this, R.raw.wrongSound);
//                        // - mediaPlayer.start();
//                    }

                    // new version
                    if(checkAnswer( view, v) == true) {
                        Toast.makeText(Lesson2_VariableTypes_Activity.this, "Correct", Toast.LENGTH_SHORT).show();
                        countPlaced++;
                    } else {
                        Toast.makeText(Lesson2_VariableTypes_Activity.this, "Wrong Answer, try again", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            // counting if all options have been placed.
            if (countPlaced == 5) {
                Toast.makeText(Lesson2_VariableTypes_Activity.this, "Well Done! Next question :)", Toast.LENGTH_SHORT).show();
                countPlaced = 0;
                answeredCorrectly++;
                currentQuestionNumber++;
                if(currentQuestionNumber<=totalNumOfQuestion){
                    setLayout();
                    setQuestions();
                } else {
                    Toast.makeText(Lesson2_VariableTypes_Activity.this, "Stage clear!!", Toast.LENGTH_SHORT).show();
                    stageSummary_popUpOpen();
                }
            }
            return true;
        }
    };

    public void lessonDescription_popUpOpen(final boolean exit){
        dialog.setContentView(R.layout.custompopup_lesson_description_landscape);

        TextView closePopUp = (TextView) dialog.findViewById(R.id.close_popup);
        Button okay_btn = (Button) dialog.findViewById(R.id.okay_button);
        TextView lessonTopic = (TextView) dialog.findViewById(R.id.lesson_topic);
        TextView lessonDescription = (TextView) dialog.findViewById(R.id.lesson_description);
        ImageView lesson_icon = (ImageView) dialog.findViewById(R.id.lesson_icon);

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(exit == true){
                    onBackPressed();
                }
            }
        });
        // set lesson icon
        lesson_icon.setImageResource(lesson2.lesson_icon());
        // lesson_topic
        lessonTopic.setText(lesson2.lesson_name());
        // lesson_description
        String description = lesson2.lesson_description() + "<br/><br/><br/>" +
                "<u><b>How to play the game:</b></u><br/>" +
                "1. Read the question.<br/>" +
                "2. To drag the answer, press the answer and hold for 2 seconds" +
                " then you can drag it to the correct area and release.";
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

    public void stageSummary_popUpOpen(){
        dialog.setContentView(R.layout.custompopup_quiz_summary_landscape);

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
        stageClear_image.setImageResource(lesson2.lesson_icon());
        lessonTopic.setText(lesson2.lesson_name());
        // lesson_topic, lesson_summary
        String summary = "<b>Answered correctly</b>: " + answeredCorrectly +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Answered wrong</b>: " + (totalNumOfQuestion - answeredCorrectly) +
                "<br/><br/><b>Score</b>: " + score;
        stageSummary.setText(Html.fromHtml(summary));

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String l2Completeness = "Not Started";
                if(answeredCorrectly == totalNumOfQuestion){
                    l2Completeness = "Completed";
                } else if (answeredCorrectly > 0){
                    l2Completeness = "In progress";
                }
                if(SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this).checkLoginOrNot()){
//                    onScoreUpdateDB(v);
//                    onProgressUpdate(l2Completeness);
                    GameProgressHandler gameProgressHandler = new GameProgressHandler(Lesson2_VariableTypes_Activity.this, progressDialog);
                    gameProgressHandler.onScoreUpdateDB(v, score);
                    String l1Completeness = SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this).getLesson1Complete();
                    gameProgressHandler.onProgressUpdate(l1Completeness,l2Completeness);
                    Toast.makeText(Lesson2_VariableTypes_Activity.this, "Total Score updated", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    // do local update.
                    int currentScore = LocalSharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this).getUserTotalScore();
                    LocalSharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this)
                            .userScoreUpdate(currentScore+score);
                    LocalSharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this)
                            .lesson2ProgressUpdate(l2Completeness);
                    Toast.makeText(Lesson2_VariableTypes_Activity.this, "Total Score updated", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final LinearLayout layout = (LinearLayout) findViewById(R.id.drag_and_drop_default_background);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Lesson2_VariableTypes_Activity.super.runOnUiThread(new Runnable() {
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

    // creating in game dropdown menu for the game, allowing to recap lesson description and exit game.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ingame_dropdown_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // in game drop down menu
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
                        if(SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this).checkLoginOrNot()){
                            //if user already logged in.
                            SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this)
                                    .userScoreUpdate(jsonResponse.getInt("totalScore"));
                        }
                        onBackPressed();
                    } else {
                        Toast.makeText(Lesson2_VariableTypes_Activity.this, jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(Lesson2_VariableTypes_Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
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
        pdCanceller.postDelayed(progressRunnable, 10000);

        int useriD = SharedPrefManager.getInstance(this).getUserID();
        int current_level = SharedPrefManager.getInstance(this).getUserLevel();
        int currentScore = SharedPrefManager.getInstance(this).getUserTotalScore();
        int userIcon = SharedPrefManager.getInstance(this).getUserIcon();
        UserInfoUpdateRequest userInfoUpdateRequest = new UserInfoUpdateRequest(useriD, current_level, currentScore+score, userIcon, responseListener, errorListener);
        RequestHandler.getInstance(Lesson2_VariableTypes_Activity.this).addToRequestQueue(userInfoUpdateRequest);
    }

    // update user lesson progress.
    public void onProgressUpdate(String l2Complete){
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
                        if(SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this).checkLoginOrNot()){
                            //if user already logged in.
                            SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this)
                                    .lesson1ProgressUpdate(jsonResponse.getString("lesson1"));
                            SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this)
                                    .lesson2ProgressUpdate(jsonResponse.getString("lesson2"));
                        }
                    } else {
                        Toast.makeText(Lesson2_VariableTypes_Activity.this, jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(Lesson2_VariableTypes_Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
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

        int useriD = SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this).getUserID();
        String l1Complete = SharedPrefManager.getInstance(Lesson2_VariableTypes_Activity.this).getLesson1Complete();
        UserProgressUpdateRequest updateUserProgress = new UserProgressUpdateRequest(useriD, l1Complete,l2Complete, responseListener, errorListener);
        RequestHandler.getInstance(Lesson2_VariableTypes_Activity.this).addToRequestQueue(updateUserProgress);
    }

}

// Obaidullah Masood (2016). Android Studio Tutorial | Drag and drop 1 | Layout Design [online]. available at https://www.youtube.com/watch?v=ZuPh3WPbytY&list=PLUopjVceujVTRJA4b-uhjTXbmSxTbs5lW&index=1 [assessed 24/01/2019]