package com.example.sherisesinyeelam.java4kids.mainpages.gamespage.draganddropgame;

import com.example.sherisesinyeelam.java4kids.R;

import android.app.Dialog;
import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TheDragAndDropGameActivity extends AppCompatActivity {

    Dialog dialog;

    //TextView textPiece1, textPiece2, textPiece3, dropingArea;

    TextView question;
    LinearLayout drop_target1, drop_target2, drop_target3, drop_target4, drop_target5;
    Button button1, button2, button3, button4, button5; // the place where to drop the piece.
    Button piece1, piece2, piece3, piece4, piece5; // the piece that to be dragged.
    Button goNext;

    int i = 0;
    int score = 0;
    int completementPercentage = 100;
    int totalNumOfQuestion = 5;
    int currentQuestionNumber = 1;

    boolean allQuestionDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_drag_and_drop);
        autoChangeBackground();

        dialog = new Dialog(this);

        lessonDescription_popUpOpen();

        //todo, apply what I've done and learnt from the tutorial to the actual quiz.

        // the following are sample, followed the tutorial.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // new version
        // question box
        question = (TextView) findViewById(R.id.d_question);

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
        goNext = (Button) findViewById(R.id.d_next_question);

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

        setQuestions(currentQuestionNumber);

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQuestionNumber != totalNumOfQuestion){
                    setQuestions(currentQuestionNumber++);
                    allQuestionDone = false;
                } else {
                    allQuestionDone = true;
                }
            }
        });

        if (allQuestionDone == true) {
            Toast.makeText(TheDragAndDropGameActivity.this, "Task Complete !", Toast.LENGTH_SHORT).show();
            stageSummary_popUpOpen();
        }

        // old version
//        textPiece1 = (TextView) findViewById(R.id.drag_piece1);
//        textPiece2 = (TextView) findViewById(R.id.drag_piece2);
//        textPiece3 = (TextView) findViewById(R.id.drag_piece3);
//
//        dropingArea = (TextView) findViewById(R.id.target_drop_area);
//
//        textPiece1.setOnLongClickListener(longClickListener);
//        textPiece2.setOnLongClickListener(longClickListener);
//        textPiece3.setOnLongClickListener(longClickListener);
//
//        dropingArea.setOnDragListener(dragListener);

    }

    public void setQuestions(int questionNum){
        if (questionNum == 1){
            question.setText("Place the correct value to the correct type below.");
            setDropBox(questionNum);
            setOptions(questionNum);
        }
        // todo, given piece of codes, place it back together to form a workable code which prints "".

        else{
            Toast.makeText(TheDragAndDropGameActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void setDropBox(int questionNum){
        if(questionNum == 1){
            button1.setText("String");
            button2.setText("int");
            button3.setText("float");
            button4.setText("char");
            button5.setText("boolean");
        }

    }

    public void setOptions(int questionNum){
        if(questionNum == 1){
            piece1.setText("0.5");
            piece2.setText("\'c\'");
            piece3.setText("true");
            piece4.setText("\"happy day\"");
            piece5.setText("100");
        }

    }

    // check if the correct piece is been dragged and dropped to a correct box.
    public boolean checkAnswer(int questionNum, View view, View v){

        if(questionNum == 1){
            if(view.getId() == R.id.drag_piece1 && v.getId() == R.id.drag_n_drop_target3){
                // replace the blank to the correct answer.
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button3.setVisibility(View.GONE);
                newPiece.addView(view);
                return true;

            } else if(view.getId() == R.id.drag_piece2 && v.getId() == R.id.drag_n_drop_target4) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button4.setVisibility(View.GONE);
                newPiece.addView(view);
                return true;

            } else if(view.getId() == R.id.drag_piece3 && v.getId() == R.id.drag_n_drop_target5) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button5.setVisibility(View.GONE);
                newPiece.addView(view);
                return true;

            } else if(view.getId() == R.id.drag_piece4 && v.getId() == R.id.drag_n_drop_target1) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button1.setVisibility(View.GONE);
                newPiece.addView(view);
                return true;

            } else if(view.getId() == R.id.drag_piece5 && v.getId() == R.id.drag_n_drop_target2) {
                LinearLayout oldTarget = (LinearLayout) view.getParent();
                oldTarget.removeView(view);
                LinearLayout newPiece = (LinearLayout) v;
                button2.setVisibility(View.GONE);
                newPiece.addView(view);
                return true;
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
//                        // - do MediaPlayer mediaPlayer = MediaPlayer.create(TheDragAndDropGameActivity.this, R.raw.wrongSound);
//                        // - mediaPlayer.start();
//                    }

                    // new version
                    if(checkAnswer(1, view, v) == true) {
                        Toast.makeText(TheDragAndDropGameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                        score += 100;
                        i++;

                    } else {
                        Toast.makeText(TheDragAndDropGameActivity.this, "Wrong Answer, try again", Toast.LENGTH_SHORT).show();
                        completementPercentage -= 20;
                    }
                    break;
            }

            // counting if all options have been placed.
            if (i == 5) {
                Toast.makeText(TheDragAndDropGameActivity.this, "Question Done! Press Next :)", Toast.LENGTH_SHORT).show();
                i = 0;
            }

            return true;
        }
    };

    // todo, also apply this on the drop down menu inside the game - lesson reminder.
    public void lessonDescription_popUpOpen(){
        dialog.setContentView(R.layout.custompopup_lesson_description); // todo

        TextView closePopUp = (TextView) dialog.findViewById(R.id.close_popup);
        Button okay_btn = (Button) dialog.findViewById(R.id.okay_button);
        TextView lessonTopic = (TextView) dialog.findViewById(R.id.lesson_topic);
        TextView lessonDescription = (TextView) dialog.findViewById(R.id.lesson_description);

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // todo, set textview - lesson description here.
        // lesson_topic
        // lesson_description

        okay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void stageSummary_popUpOpen(){
        dialog.setContentView(R.layout.custompopup_quiz_summary); // todo

        TextView closePopUp = (TextView) dialog.findViewById(R.id.close_popup);
        Button done_btn = (Button) dialog.findViewById(R.id.done_button);
        ImageView stageClear_image = (ImageView) dialog.findViewById(R.id.stage_clear_image);
        TextView lessonTopic = (TextView) dialog.findViewById(R.id.lesson_topic);
        TextView stageSummary = (TextView) dialog.findViewById(R.id.stage_summary);

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onBackPressed(); //todo, close popup and return back to the game page.
            }

        });

        // todo, set textview - lesson description here.
        // lesson_topic, lesson_summary
        stageSummary.setText("Overall task complement percentage: " + completementPercentage + "% \nScore: " + score);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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

                        TheDragAndDropGameActivity.super.runOnUiThread(new Runnable() {
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

// Obaidullah Masood (2016). Android Studio Tutorial | Drag and drop 1 | Layout Design [online]. available at https://www.youtube.com/watch?v=ZuPh3WPbytY&list=PLUopjVceujVTRJA4b-uhjTXbmSxTbs5lW&index=1 [assessed 24/01/2019]