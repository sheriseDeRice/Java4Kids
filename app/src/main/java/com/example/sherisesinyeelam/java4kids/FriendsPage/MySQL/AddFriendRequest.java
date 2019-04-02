package com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddFriendRequest extends StringRequest {

    private static final String ADD_FRIEND_URL = "http://sinyeelam.com/java4Kids/addFriends.php";
    private Map<String, String> params;

    public AddFriendRequest(int userID1, int userID2,
                            Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Request.Method.POST, ADD_FRIEND_URL, listener, errorListener);

        params = new HashMap<>();
        params.put("userID1", userID1+"");
        params.put("userID2", userID2+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
