package com.example.sherisesinyeelam.java4kids;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class RegisterAccRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://sinyeelam.com/register.php";
    private Map<String, String> params;

    public RegisterAccRequest(String firstname, String lastname, String age, String email, String password,  Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("age", age);
        //params.put("gender", gender);
        params.put("email", email);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}