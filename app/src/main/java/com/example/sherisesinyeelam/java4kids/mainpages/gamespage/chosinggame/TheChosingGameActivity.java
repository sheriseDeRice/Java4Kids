package com.example.sherisesinyeelam.java4kids.mainpages.gamespage.chosinggame;

import com.example.sherisesinyeelam.java4kids.R;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class TheChosingGameActivity extends AppCompatActivity implements View.OnClickListener {

    Dialog dialog;

    ArrayList<Integer> random_number = new ArrayList<>();
    int index = 0;

    TextView theQuestion, code_fragement;
    Button option1, option2, option3, option4, option5, option6;
    LinearLayout linearLayout1_btn, linearLayout2_btn;
    TextView score_and_answered;

    int theCorrectAnswer = -1; // should be either 1-6, will change according to the question.
    int userChoice = -1;
    int answeredCorrectly = 0;
    boolean[] answered = {false, false, false, false, false};
    int questionRemain = 5;
    int answerClicked = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_chosing_game);
        autoChangeBackground();

        dialog = new Dialog(this);

        lessonDescription_popUpOpen();

        theQuestion = (TextView) findViewById(R.id.question_in_choosing_game);
        code_fragement = (TextView) findViewById(R.id.question_fragment);

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

        // creating a random number generator between 1-5, for random showing the questions.
        for (int i = 0; i < 5; i++){
            int num = i + 1;
            random_number.add(num);
        }
        Collections.shuffle(random_number);

        int question_no = setTheQuestionToShow(random_number.get(index));
        //random_number.remove(index);
        checkAnswer(question_no);

    }

    public int setTheQuestionToShow(int question_no){
        index++;
        if(question_no == 1 && answered[0] == false){
            theQuestion.setText("Question 1: \n Which of the options is its Subclass?");
            code_fragement.setVisibility(View.INVISIBLE);
            correspondingOptions(question_no);
            theCorrectAnswer = 3;

        } else if (question_no == 2 && answered[1] == false) {
            theQuestion.setText("Question 2: \n Which of the options is its/their Superclass?");
            code_fragement.setVisibility(View.INVISIBLE);
            correspondingOptions(question_no);
            theCorrectAnswer = 6;

        } else if (question_no == 3 && answered[2] == false) {
            theQuestion.setText("Question 3: \n Read the following code, which is its correct output?");
            code_fragement.setVisibility(View.VISIBLE);
            correspondingOptions(question_no);
            theCorrectAnswer = 4;

        } else if (question_no == 4 && answered[3] == false) {
            theQuestion.setText("Question 4: \n ...");
            code_fragement.setVisibility(View.VISIBLE);
            correspondingOptions(question_no);
            theCorrectAnswer = 2;

        } else if (question_no == 5 && answered[4] == false) {
            theQuestion.setText("Question 5: \n ...");
            code_fragement.setVisibility(View.INVISIBLE);
            correspondingOptions(question_no);
            theCorrectAnswer = 5;

        }
        return question_no;
    }

    public void correspondingOptions(int questionNum){
        if(questionNum == 1){
            // todo, set button text
            option1.setText("A");
            option2.setText("B");
            option3.setText("C");
            option4.setText("D");
            option5.setText("E");
            option6.setText("F");

        } else if (questionNum == 2) {
            option1.setText("1");
            option2.setText("2");
            option3.setText("3");
            option4.setText("4");
            option5.setText("5");
            option6.setText("6");

        } else if (questionNum == 3) {
            option1.setText("a");
            option2.setText("b");
            option3.setText("c");
            option4.setText("d");
            option5.setText("e");
            option6.setText("f");

        } else if (questionNum == 4) {
            option1.setText("a");
            option2.setText("i");
            option3.setText("u");
            option4.setText("e");
            option5.setText("o");
            option6.setText("-");

        } else if (questionNum == 5) {

        }
    }

    // if correct, answered = true, if wrong, .add(questionNum) back to the arraylist.
    public void checkAnswer(int questionNum){
        if(questionNum == 1){
            if (userChoice == theCorrectAnswer) {
                answered[0] = true;
                questionRemain--;
                answeredCorrectly++;
                score += 100;
            }
            else {
                // todo, add the question back to the queue if answered wrong.
//                index--;
//                random_number.add(questionNum);
//                Collections.shuffle(random_number);
            }
            score_and_answered.setText("score: " + score + "   question: "+ answeredCorrectly + "/5");

        } else if (questionNum == 2) {
            if (userChoice == theCorrectAnswer) {
                answered[1] = true;
                questionRemain--;
                answeredCorrectly++;
                score += 100;
            }
            else {

            }
            score_and_answered.setText("score: " + score + "   question: "+ answeredCorrectly + "/5");

        } else if (questionNum == 3) {
            if (userChoice == theCorrectAnswer) {
                answered[2] = true;
                questionRemain--;
                answeredCorrectly++;
                score += 100;
            }
            else {

            }
            score_and_answered.setText("score: " + score + "   question: "+ answeredCorrectly + "/5");


        } else if (questionNum == 4) {
            if (userChoice == theCorrectAnswer) {
                answered[3] = true;
                questionRemain--;
                answeredCorrectly++;
                score += 100;
            }
            else {

            }
            score_and_answered.setText("score: " + score + "   question: "+ answeredCorrectly + "/5");

        } else if (questionNum == 5) {
            if (userChoice == theCorrectAnswer) {
                answered[4] = true;
                questionRemain--;
                answeredCorrectly++;
                score += 100;
            }
            else {

            }
            score_and_answered.setText("score: " + score + "   question: "+ answeredCorrectly + "/5");

        }
        // erase later
        questionRemain--;
    }

    // checking which button does the user clicked.
    @Override
    public void onClick(View v) {
        answerClicked++;
        switch (v.getId()) {
            case R.id.c_option1:
                userChoice =  1;
                //v.setSelected(true);
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
        if (index < random_number.size() && index >= 0) {
            int question_no = setTheQuestionToShow(random_number.get(index));
            //random_number.remove(index);
            correspondingOptions(question_no);
            checkAnswer(question_no);
        }
        if(questionRemain == 0 && answerClicked == 5){
            Toast.makeText(TheChosingGameActivity.this, "Level 1 complete!", Toast.LENGTH_SHORT).show();
            // todo, show new popup, informing the stage clear and score and number of wrong and correct and store details.
            stageSummary_popUpOpen();
        }
    }

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
        stageSummary.setText("Answered correctly: " + answeredCorrectly + "\nAnswered wrong: " + (questionRemain-answeredCorrectly) + "\nScore: " + score);

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
