package com.example.sherisesinyeelam.java4kids;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sherisesinyeelam.java4kids.UserProfilePage.LevelUpTable;

// another singleton pattern class, used to store user data
public class LocalSharedPrefManager {

    private static LocalSharedPrefManager instance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME = "mylocalsharedpref";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_LEVEL = "userlevel";
    private static final String KEY_USER_TOTALSCORE = "usertotalscore";
    private static final String KEY_USER_ICON = "userIcon";
    private static final String KEY_USER_LOGIN_TREND = "logintrend";
    private static final String KEY_CURRENT_BONUS = "currentbonus";
    private static final String KEY_MON_BONUS = "mondaybonus";
    private static final String KEY_TUE_BONUS = "tuesdaybonus";
    private static final String KEY_WED_BONUS = "wednesdaybonus";
    private static final String KEY_THU_BONUS = "thursdaybonus";
    private static final String KEY_FRI_BONUS = "fridaybonus";
    private static final String KEY_SAT_BONUS = "saturdaybonus";
    private static final String KEY_SUN_BONUS = "sundaybonus";
    private static final String KEY_LESSON1 = "lesson1";
    private static final String KEY_LESSON2 = "lesson2";
    private static final String KEY_NICKNAME = "nickname";

    private LocalSharedPrefManager(Context context) {
        ctx = context;
    }

    public static synchronized LocalSharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new LocalSharedPrefManager(context);
        }
        return instance;
    }

    public boolean guestLogin(int id, String username, int level, int totalScore, int userIcon, int loginDays){
        // mode_private mean only within this application can share the preferences.
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USERNAME, username);
        editor.putInt(KEY_USER_LEVEL, level);
        editor.putInt(KEY_USER_TOTALSCORE, totalScore);
        editor.putInt(KEY_USER_ICON, userIcon);
        editor.putInt(KEY_USER_LOGIN_TREND, loginDays);
        editor.putString(KEY_LESSON1, "Not Started");
        editor.putString(KEY_LESSON2, "Not Started");
        editor.apply();
        return true;
    }
    public boolean checkLoginOrNot(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null) != null){
            // if return null means user is not logged in.
            return true; // is logged in.
        }
        return false;
    }

    public void setNickname(String nickname){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NICKNAME, nickname);
        editor.apply();
    }

    public void prefReset(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
    }

    public void loginTrendUpdate(String todayDate){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(todayDate, true);
        editor.apply();
    }
    public boolean getLoginTrend(String todayDate){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(todayDate,false);
    }
    public void setBonus(int bonus){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_CURRENT_BONUS, bonus);
        editor.apply();
    }
    public int getCurrentBonus(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_CURRENT_BONUS,50);
    }
    public void setMonBonus(int bonus){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_MON_BONUS, bonus);
        editor.apply();
    }
    public void setTueBonus(int bonus){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_TUE_BONUS, bonus);
        editor.apply();
    }
    public void setWedBonus(int bonus){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_WED_BONUS, bonus);
        editor.apply();
    }
    public void setThuBonus(int bonus){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_THU_BONUS, bonus);
        editor.apply();
    }
    public void setFriBonus(int bonus){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_FRI_BONUS, bonus);
        editor.apply();
    }
    public void setSatBonus(int bonus){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_SAT_BONUS, bonus);
        editor.apply();
    }
    public void setSunBonus(int bonus){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_SUN_BONUS, bonus);
        editor.apply();
    }
    public int getMonB(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_MON_BONUS,0);
    }
    public int getTueB(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_TUE_BONUS,0);
    }
    public int getWedB(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_WED_BONUS,0);
    }
    public int getThuB(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_THU_BONUS,0);
    }
    public int getFriB(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_FRI_BONUS,0);
    }
    public int getSatB(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_SAT_BONUS,0);
    }
    public int getSunB(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_SUN_BONUS,0);
    }

    public void userScoreUpdate(int totalScore){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int level = 0;
        if(level != 30){
            LevelUpTable levelUpTable = new LevelUpTable();
            level = levelUpTable.levelUp(totalScore);
        }
        editor.putInt(KEY_USER_LEVEL, level);
        editor.putInt(KEY_USER_TOTALSCORE, totalScore);
        editor.apply();
    }

    public void lesson1ProgressUpdate(String l1Completeness){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LESSON1, l1Completeness);
        editor.apply();
    }
    public void lesson2ProgressUpdate(String l2Completeness){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LESSON2, l2Completeness);
        editor.apply();
    }

    public void userIconUpdate(int userIcon){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ICON, userIcon);
        editor.apply();
    }

    public int getUserID(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ID, 0);
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getNickname(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NICKNAME, null);
    }

    public int getUserLevel(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_LEVEL, 0);
    }

    public int getUserTotalScore(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_TOTALSCORE, 0);
    }

    public int getUserIcon(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ICON, R.drawable.default_icon_foreground);
    }

    public String getLesson1Complete(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LESSON1, null);
    }

    public String getLesson2Complete(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LESSON2, null);
    }

}
