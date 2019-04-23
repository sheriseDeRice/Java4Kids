package com.example.sherisesinyeelam.java4kids.UserProfilePage;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UserInfoUpdateRequest extends StringRequest {

    private static final String USER_INFO_UPDATE_URL = "http://sinyeelam.com/java4Kids/updateUserInfo.php";
    private Map<String, String> params;

    public UserInfoUpdateRequest(int userID, int level, int totalScore, int userIcon,
                                 Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Request.Method.POST, USER_INFO_UPDATE_URL, listener, errorListener);

        if(level != 30){
            LevelUpTable levelUpTable = new LevelUpTable();
            level = levelUpTable.levelUp(totalScore);
        }

        params = new HashMap<>();
        params.put("userID", userID+"");
        params.put("level", level+"");
        params.put("totalScore", totalScore+"");
        params.put("userIcon", userIcon+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
