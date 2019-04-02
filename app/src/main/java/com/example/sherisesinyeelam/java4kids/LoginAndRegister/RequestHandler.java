package com.example.sherisesinyeelam.java4kids.LoginAndRegister;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

// singleton pattern to prevent duplicate user details.
public class RequestHandler {

    private static RequestHandler instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private RequestHandler(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestHandler getInstance(Context context) {
        if (instance == null) {
            instance = new RequestHandler(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
// Android Developers(2019). Set up RequestQueue [online]. Available at https://developer.android.com/training/volley/requestqueue.html [accessed 14/03/2019]
// Simplified Coding (2016). Android PHP MySQL Tutorial Series [online]. Available at https://www.youtube.com/watch?v=BWaZa1j2xVg&list=PLk7v1Z2rk4hjQaV062aE_CW68xgXdYFpV&index=7 [accessed 14/03/2019]