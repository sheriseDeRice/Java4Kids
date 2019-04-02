package com.example.sherisesinyeelam.java4kids.ProgressPage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetUserLessonProgress extends StringRequest {

    private static final String USER_INFO_UPDATE_URL = "http://sinyeelam.com/java4Kids/getUserProgress.php";
    private Map<String, String> params;

    public GetUserLessonProgress(int userID,
                                 Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Method.POST, USER_INFO_UPDATE_URL, listener, errorListener);

        params = new HashMap<>();
        params.put("userID", userID+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
