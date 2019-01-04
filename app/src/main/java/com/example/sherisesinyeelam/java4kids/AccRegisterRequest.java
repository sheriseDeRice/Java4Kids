package com.example.sherisesinyeelam.java4kids;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class AccRegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://sinyeelam.com/register.php";
    private Map<String, String> params;

    public AccRegisterRequest(String firstname, String lastname, int age, String gender, String email, String password, Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, errorListener);

        params = new HashMap<>();
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("age", age + "");
        params.put("gender", gender);
        params.put("email", email);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}