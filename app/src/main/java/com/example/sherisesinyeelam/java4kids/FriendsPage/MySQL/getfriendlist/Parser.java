package com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.getfriendlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sherisesinyeelam.java4kids.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void, Void, Integer> {

    Context context;
    ListView listView;
    String data;

    ArrayList<SpaceCraft> spaceCrafts = new ArrayList<>();

    ProgressDialog progressDialog;

    public Parser(Context context, String data, ListView listView) {
        this.context = context;
        this.data = data;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Parsing friends ....Please wait");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        progressDialog.dismiss();

        if(result == 1){
            // calling adapter to bind
            // creating the adapter
            CustomeAdapter customeAdapter = new CustomeAdapter(context, spaceCrafts);
            listView.setAdapter(customeAdapter);

        } else{
            //Toast.makeText(context, "cannot parse data", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "No friend in your friend list", Toast.LENGTH_SHORT).show();
        }

    }

    // parse received data
    private int parse (){
        try {
            // add data to json array first
            JSONArray jsonArray = new JSONArray(data);

            // create json object to hold a single item.
            JSONObject jsonObject = null;

            //old version: friendsList.clear();
            spaceCrafts.clear();
            SpaceCraft spaceCraft = null;

            // retrieve name
            //int userID = jsonObject.getInt("userID");
            String name = SharedPrefManager.getInstance(context).getFirstname();
            String uname = SharedPrefManager.getInstance(context).getUsername();
            //String email = jsonObject.getString("email");
            int lv = SharedPrefManager.getInstance(context).getUserLevel();
            int tScore = SharedPrefManager.getInstance(context).getUserTotalScore();

            spaceCraft = new SpaceCraft();
            spaceCraft.setName(name);
            spaceCraft.setUsername(uname);
            spaceCraft.setLevel(lv);
            spaceCraft.setScore(tScore);

            spaceCrafts.add(spaceCraft);

            for (int i = 0; i < jsonArray.length(); i++){

                jsonObject = jsonArray.getJSONObject(i);

                // retrieve name
                //int userID = jsonObject.getInt("userID");
                String firstname = jsonObject.getString("firstname");
                String username = jsonObject.getString("username");
                //String email = jsonObject.getString("email");
                int level = jsonObject.getInt("level");
                int score = jsonObject.getInt("totalScore");

                spaceCraft = new SpaceCraft();
                spaceCraft.setName(firstname);
                spaceCraft.setUsername(username);
                spaceCraft.setLevel(level);
                spaceCraft.setScore(score);

                spaceCrafts.add(spaceCraft);
            }
            return 1;

        } catch (JSONException e){
            e.printStackTrace();
        }
        return 0;
    }
}

// ProgrammingWizardsTV (2016). Android MySQL Database 07 : ListView - Select and Show Multiple Columns [online]. available at https://www.youtube.com/watch?v=krLVCo58WEo [accessed 10/03/2019]