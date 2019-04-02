package com.example.sherisesinyeelam.java4kids.ProgressPage;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.LocalSharedPrefManager;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.RequestHandler;
import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ShowProgressActivity extends Fragment {

    View v;
    ProgressDialog progressDialog;
    TextView monBonus, tueBonus, wedBonus, thuBonus, friBonus, satBonus, sunBonus;

    TextView lesson1, lesson2, lesson3, lesson4, lesson5, lesson6, lesson7;
    TextView l1Progress, l2Progress, l3Progress, l4Progress, l5Progress, l6Progress, l7Progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_show_progress, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Progress");
        progressDialog = new ProgressDialog(getActivity());
        v = new View(getActivity());

        // lesson names
        lesson1 = (TextView) getView().findViewById(R.id.lesson1_title);
        lesson2 = (TextView) getView().findViewById(R.id.lesson2_title);
        lesson3 = (TextView) getView().findViewById(R.id.lesson3_title);
        lesson4 = (TextView) getView().findViewById(R.id.lesson4_title);
        lesson5 = (TextView) getView().findViewById(R.id.lesson5_title);
        lesson6 = (TextView) getView().findViewById(R.id.lesson6_title);
        lesson7 = (TextView) getView().findViewById(R.id.lesson7_title);

        lesson1.setText("Inheritance");
        lesson2.setText("Variable Types");
        lesson3.setText("");
        lesson4.setText("");
        lesson5.setText("");
        lesson6.setText("");
        lesson7.setText("");

        loginBonusTrack();
        lessonProgressTrack();
    }

    public void loginBonusTrack(){
        monBonus = (TextView) getView().findViewById(R.id.monday_bonus);
        tueBonus = (TextView) getView().findViewById(R.id.tuesday_bonus);
        wedBonus = (TextView) getView().findViewById(R.id.wednesday_bonus);
        thuBonus = (TextView) getView().findViewById(R.id.thursday_bonus);
        friBonus = (TextView) getView().findViewById(R.id.friday_bonus);
        satBonus = (TextView) getView().findViewById(R.id.saturday_bonus);
        sunBonus = (TextView) getView().findViewById(R.id.sunday_bonus);

        int bonus = 0;

        // today's date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String todayDate = year + "" + month + "" + day;

        // yesterday date
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE,-1);
        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        String yesterdayDate = year2 + "" + month2 + "" + day2;

        if(LocalSharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
            bonus = LocalSharedPrefManager.getInstance(getActivity()).getCurrentBonus();
            boolean rewarded = LocalSharedPrefManager.getInstance(getActivity()).getLoginTrend(todayDate);
            if(rewarded == false){
                // if today is rewarded and yesterday is rewarded, bonus increase
                if(LocalSharedPrefManager.getInstance(getActivity()).getLoginTrend(yesterdayDate)){
                    bonus = LocalSharedPrefManager.getInstance(getActivity()).getCurrentBonus()+50;
                } else {
                    bonus = 50;
                }
                LocalSharedPrefManager.getInstance(getActivity()).loginTrendUpdate(todayDate);
                LocalSharedPrefManager.getInstance(getActivity()).setBonus(bonus);
                int currentScore = LocalSharedPrefManager.getInstance(getActivity()).getUserTotalScore();
                LocalSharedPrefManager.getInstance(getActivity()).userScoreUpdate(currentScore+bonus);
            }

        } else if (SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
            bonus = SharedPrefManager.getInstance(getActivity()).getCurrentBonus();
            boolean rewarded = SharedPrefManager.getInstance(getActivity()).getLoginTrend(todayDate);
            if(rewarded == false){
                // if today is rewarded and yesterday is rewarded, bonus increase
                if(SharedPrefManager.getInstance(getActivity()).getLoginTrend(yesterdayDate)){
                    bonus = SharedPrefManager.getInstance(getActivity()).getCurrentBonus()+50;
                } else {
                    bonus = 50;
                }
                SharedPrefManager.getInstance(getActivity()).loginTrendUpdate(todayDate);
                SharedPrefManager.getInstance(getActivity()).setBonus(bonus);
                int currentScore = SharedPrefManager.getInstance(getActivity()).getUserTotalScore();
                SharedPrefManager.getInstance(getActivity()).userScoreUpdate(currentScore+bonus);
            }
        }

        // display a week bonus record
        if(LocalSharedPrefManager.getInstance(getActivity()).checkLoginOrNot()) {
            if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                int monB = LocalSharedPrefManager.getInstance(getActivity()).getMonB();
                //if(monB == 0){
                LocalSharedPrefManager.getInstance(getActivity()).setBonus(50);
                LocalSharedPrefManager.getInstance(getActivity()).setMonBonus(bonus);
                //LocalSharedPrefManager.getInstance(getActivity()).setMonBonus(0);
                LocalSharedPrefManager.getInstance(getActivity()).setTueBonus(0);
                LocalSharedPrefManager.getInstance(getActivity()).setWedBonus(0);
                LocalSharedPrefManager.getInstance(getActivity()).setThuBonus(0);
                LocalSharedPrefManager.getInstance(getActivity()).setFriBonus(0);
                LocalSharedPrefManager.getInstance(getActivity()).setSatBonus(0);
                LocalSharedPrefManager.getInstance(getActivity()).setSunBonus(0);
                //}
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
                int tueB = LocalSharedPrefManager.getInstance(getActivity()).getTueB();
                if (tueB == 0) {
                    LocalSharedPrefManager.getInstance(getActivity()).setTueBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
                int wedB = LocalSharedPrefManager.getInstance(getActivity()).getWedB();
                if (wedB == 0) {
                    LocalSharedPrefManager.getInstance(getActivity()).setWedBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                int thuB = LocalSharedPrefManager.getInstance(getActivity()).getThuB();
                if (thuB == 0) {
                    LocalSharedPrefManager.getInstance(getActivity()).setThuBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                int friB = LocalSharedPrefManager.getInstance(getActivity()).getFriB();
                if (friB == 0) {
                    LocalSharedPrefManager.getInstance(getActivity()).setFriBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                int satB = LocalSharedPrefManager.getInstance(getActivity()).getSatB();
                if (satB == 0) {
                    LocalSharedPrefManager.getInstance(getActivity()).setSatBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                int sunB = LocalSharedPrefManager.getInstance(getActivity()).getSunB();
                if (sunB == 0) {
                    LocalSharedPrefManager.getInstance(getActivity()).setSunBonus(bonus);
                }
                LocalSharedPrefManager.getInstance(getActivity()).setBonus(0);
            }
            monBonus.setText("\n+"+LocalSharedPrefManager.getInstance(getActivity()).getMonB()+"\n");
            tueBonus.setText("\n+"+LocalSharedPrefManager.getInstance(getActivity()).getTueB()+"\n");
            wedBonus.setText("\n+"+LocalSharedPrefManager.getInstance(getActivity()).getWedB()+"\n");
            thuBonus.setText("\n+"+LocalSharedPrefManager.getInstance(getActivity()).getThuB()+"\n");
            friBonus.setText("\n+"+LocalSharedPrefManager.getInstance(getActivity()).getFriB()+"\n");
            satBonus.setText("\n+"+LocalSharedPrefManager.getInstance(getActivity()).getSatB()+"\n");
            sunBonus.setText("\n+"+LocalSharedPrefManager.getInstance(getActivity()).getSunB()+"\n");
        } else if (SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
            // display a week bonus record
            if(calendar.get(Calendar.DAY_OF_WEEK) == 2){
                int monB = SharedPrefManager.getInstance(getActivity()).getMonB();
                //if(monB == 0){
                SharedPrefManager.getInstance(getActivity()).setMonBonus(bonus);
                //LocalSharedPrefManager.getInstance(getActivity()).setMonBonus(0);
                SharedPrefManager.getInstance(getActivity()).setTueBonus(0);
                SharedPrefManager.getInstance(getActivity()).setWedBonus(0);
                SharedPrefManager.getInstance(getActivity()).setThuBonus(0);
                SharedPrefManager.getInstance(getActivity()).setFriBonus(0);
                SharedPrefManager.getInstance(getActivity()).setSatBonus(0);
                SharedPrefManager.getInstance(getActivity()).setSunBonus(0);
                //}
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 3){
                int tueB = SharedPrefManager.getInstance(getActivity()).getTueB();
                if(tueB == 0){
                    SharedPrefManager.getInstance(getActivity()).setTueBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 4){
                int wedB = SharedPrefManager.getInstance(getActivity()).getWedB();
                if(wedB == 0){
                    SharedPrefManager.getInstance(getActivity()).setWedBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 5){
                int thuB = SharedPrefManager.getInstance(getActivity()).getThuB();
                if(thuB == 0){
                    SharedPrefManager.getInstance(getActivity()).setThuBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 6){
                int friB = SharedPrefManager.getInstance(getActivity()).getFriB();
                if(friB == 0){
                    SharedPrefManager.getInstance(getActivity()).setFriBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 7){
                int satB = SharedPrefManager.getInstance(getActivity()).getSatB();
                if(satB == 0){
                    SharedPrefManager.getInstance(getActivity()).setSatBonus(bonus);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == 1){
                int sunB = SharedPrefManager.getInstance(getActivity()).getSunB();
                if(sunB == 0){
                    SharedPrefManager.getInstance(getActivity()).setSunBonus(bonus);
                }
                SharedPrefManager.getInstance(getActivity()).setBonus(0);
            }
            monBonus.setText("\n+"+SharedPrefManager.getInstance(getActivity()).getMonB()+"\n");
            tueBonus.setText("\n+"+SharedPrefManager.getInstance(getActivity()).getTueB()+"\n");
            wedBonus.setText("\n+"+SharedPrefManager.getInstance(getActivity()).getWedB()+"\n");
            thuBonus.setText("\n+"+SharedPrefManager.getInstance(getActivity()).getThuB()+"\n");
            friBonus.setText("\n+"+SharedPrefManager.getInstance(getActivity()).getFriB()+"\n");
            satBonus.setText("\n+"+SharedPrefManager.getInstance(getActivity()).getSatB()+"\n");
            sunBonus.setText("\n+"+SharedPrefManager.getInstance(getActivity()).getSunB()+"\n");
        }
        // testing
        //Toast.makeText(getActivity(),LocalSharedPrefManager.getInstance(getActivity()).getSunB()+"", Toast.LENGTH_SHORT).show();

    }

    public void lessonProgressTrack(){
        // progress per lesson: complete, not started, in progress
        l1Progress = (TextView) getView().findViewById(R.id.lesson1_progress);
        l2Progress = (TextView) getView().findViewById(R.id.lesson2_progress);
        l3Progress = (TextView) getView().findViewById(R.id.lesson3_progress);
        l4Progress = (TextView) getView().findViewById(R.id.lesson4_progress);
        l5Progress = (TextView) getView().findViewById(R.id.lesson5_progress);
        l6Progress = (TextView) getView().findViewById(R.id.lesson6_progress);
        l7Progress = (TextView) getView().findViewById(R.id.lesson7_progress);

        String lesson1Completeness = "";
        String lesson2Completeness = "";
        String lesson3Completeness = "";
        String lesson4Completeness = "";
        String lesson5Completeness = "";
        if(SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
            lesson1Completeness = SharedPrefManager.getInstance(getActivity()).getLesson1Complete();
            lesson2Completeness = SharedPrefManager.getInstance(getActivity()).getLesson2Complete();

        } else {
            if(LocalSharedPrefManager.getInstance(getActivity()).checkLoginOrNot()) {
                lesson1Completeness = LocalSharedPrefManager.getInstance(getActivity()).getLesson1Complete();
                lesson2Completeness = LocalSharedPrefManager.getInstance(getActivity()).getLesson2Complete();
            }
        }
        l1Progress.setText(lesson1Completeness);
        l2Progress.setText(lesson2Completeness);
        l3Progress.setText(lesson3Completeness);
        l4Progress.setText(lesson4Completeness);
        l5Progress.setText(lesson5Completeness);
        l6Progress.setText("");
        l7Progress.setText("");
    }

}

// Jonas Gehring (2019). GraphView - open source graph plotting library for Android [online]. available at http://www.android-graphview.org/ [accessed 1/03/2019]
// blahdiblah (2012). How can I create a table with borders in Android? [online]. available at https://stackoverflow.com/questions/2108456/how-can-i-create-a-table-with-borders-in-android [accessed 1/03/2019]