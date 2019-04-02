package com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.getfriendlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.postConnector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Iterator;

public class Downloader extends AsyncTask<Void, Void, String> {

    Context context;
    String address;
    ListView listView;
    TextView noNetworkText;

    int userID;

    ProgressDialog progressDialog;

    public Downloader(Context context, String address, ListView listView, TextView textView, int userID) {
        this.context = context;
        this.address = address;
        this.listView = listView;
        this.noNetworkText = textView;
        this.userID = userID;
    }

    // before job starts
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Fetching friends... please wait");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadingData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressDialog.dismiss();

        if(s != null){
            // call parser to parse
            Parser parser = new Parser(context, s, listView);
            parser.execute();

        } else {
            noNetworkText.setVisibility(View.VISIBLE);
            Toast.makeText(context,"Unable to download data", Toast.LENGTH_SHORT).show();
        }

    }

    private String downloadingData(){
        //connect to the data and get a stream
        InputStream inputStream = null;
        String line = null;

        try{
            HttpURLConnection httpURLConnection = postConnector.connector(address);

            if(httpURLConnection == null){
                return null;
            }

            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(packageData());

            bufferedWriter.flush();

            // release res
            bufferedWriter.close();
            outputStream.close();

            // get response code
            int responseCode = httpURLConnection.getResponseCode();

            // decode
            if(responseCode == httpURLConnection.HTTP_OK) {

                inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer response = new StringBuffer();

                if (bufferedReader != null) {
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line + "\n");
                    }
                    bufferedReader.close();

                } else {
                    return null;
                }
                return response.toString();
            }  else {
                return String.valueOf(responseCode);
            }

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String packageData(){

        JSONObject jsonObject = new JSONObject();
        StringBuffer queryString = new StringBuffer();

        try{
            jsonObject.put("userID", userID);

            Boolean firstValue = true;
            Iterator iterator = jsonObject.keys();

            do{
                String key = iterator.next().toString();
                String value = jsonObject.get(key).toString();

                if(firstValue){
                    firstValue = false;
                } else{
                    queryString.append("&"); // & used to separate each query.
                }
                queryString.append(URLEncoder.encode(key,"UTF-8"));
                queryString.append("=");
                queryString.append(URLEncoder.encode(value,"UTF-8"));

            } while (iterator.hasNext());

            return queryString.toString();

        } catch (JSONException e){
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }
}
