package com.example.sherisesinyeelam.java4kids.UserProfilePage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.sherisesinyeelam.java4kids.UserProfilePage.LevelUpTable;

import java.util.HashMap;
import java.util.Map;

public class UserProgressUpdateRequest extends StringRequest {

    private static final String USER_INFO_UPDATE_URL = "http://sinyeelam.com/java4Kids/updateUserProgress.php";
    private Map<String, String> params;

    public UserProgressUpdateRequest(int userID, String l1Completeness, String l2Completeness,
                                     Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Method.POST, USER_INFO_UPDATE_URL, listener, errorListener);

        params = new HashMap<>();
        params.put("userID", userID+"");
        params.put("lesson1", l1Completeness+"");
        params.put("lesson2", l2Completeness+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
