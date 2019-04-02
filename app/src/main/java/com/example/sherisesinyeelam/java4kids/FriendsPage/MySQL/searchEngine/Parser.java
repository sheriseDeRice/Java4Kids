package com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.searchEngine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.RequestHandler;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;
import com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.AddFriendRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void, Void, Integer> {

    Context context;
    String data;
    ListView listView;

    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<Integer> userIDs = new ArrayList<>();

    public Parser(Context c, String s, ListView lv){
        this.context = c;
        this.data = s;
        this.listView = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (integer == 1){
            // Bind to listview
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,usernames);
            listView.setAdapter(arrayAdapter);

            // listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    //Snackbar.make(view, usernames.get(position), Snackbar.LENGTH_SHORT).show();

                    final Context c = view.getContext();
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setCancelable(true);
                    builder.setTitle("Add friend");
                    builder.setMessage("Do you want to add " + usernames.get(position) + " to your friend list?");

                    // setting negative "Cancel" button
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    // setting positive "yes" button
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            int userID1 = SharedPrefManager.getInstance(c).getUserID();
                            int userID2 = userIDs.get(position);

                            // insert new friend into DB
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);

                                        //Toast.makeText(c, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                        if (!jsonObject.getBoolean("error")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(c);
                                            builder.setMessage("Friend added!")
                                                    .setNegativeButton("Nice!", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            onCancelled();
                                                        }
                                                    })
                                                    .create()
                                                    .show();
                                        } else {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(c);
                                            builder.setMessage(jsonObject.getString("message"))
                                                    .setNegativeButton("OK", null)
                                                    .create()
                                                    .show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            Response.ErrorListener errorListener = new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(c, error.getMessage(), Toast.LENGTH_LONG).show();;
                                }
                            };

                            AddFriendRequest addFriendRequest = new AddFriendRequest(userID1, userID2, responseListener, errorListener);
                            RequestHandler.getInstance(c).addToRequestQueue(addFriendRequest);
                        }
                    });
                    builder.show();
                }
            });
        } else{
            Toast.makeText(context, "Unable to Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private int parse(){
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = null;

            usernames.clear();

            for(int i = 0; i< jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                String username = jsonObject.getString("username");
                int userID = jsonObject.getInt("userID");

                // prevent user adding himself as friend.
                if(!username.equals(SharedPrefManager.getInstance(context).getUsername())) {
                    usernames.add(username);
                    userIDs.add(userID);
                }
            }

            return 1;
        } catch (JSONException e){
            e.printStackTrace();
        }

        return 0;
    }
}
