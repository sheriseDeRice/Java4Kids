package com.example.sherisesinyeelam.java4kids.LoginAndRegister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateProgressRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://sinyeelam.com/java4Kids/userFirstProgressCreate.php ";
    private Map<String, String> params;

    public CreateProgressRequest(int userID, Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, errorListener);

        params = new HashMap<>();
        params.put("userID", userID+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
