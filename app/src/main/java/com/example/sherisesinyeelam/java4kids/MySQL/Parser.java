package com.example.sherisesinyeelam.java4kids.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void, Void, Integer> {

    Context context;
    String data;
    ListView listView;

    ArrayList<String> names = new ArrayList<>();

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
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,names);
            listView.setAdapter(arrayAdapter);
        } else{
            Toast.makeText(context, "Unable to Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private int parse(){
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = null;

            names.clear();

            for(int i = 0; i< jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                String email = jsonObject.getString("email");
                names.add(email);
            }

            return 1;
        } catch (JSONException e){
            e.printStackTrace();
        }

        return 0;
    }
}
