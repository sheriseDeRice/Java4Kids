package com.example.sherisesinyeelam.java4kids.TheWorldLeaderBoard;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    ProgressDialog progressDialog;

    public Downloader(Context context, String address, ListView listView, TextView textView) {
        this.context = context;
        this.address = address;
        this.listView = listView;
        this.noNetworkText = textView;
    }

    // before job starts
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Fetching ... please wait");
        progressDialog.show();

        // cancel the progress dialog if it runs overtime.
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
//                noNetworkText.setVisibility(View.VISIBLE);
//                Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);


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

        HttpURLConnection httpURLConnection = getConnector.connector(address);

        if(httpURLConnection == null){
            return null;
        }
        //connect to the data and get a stream
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;
            StringBuffer response = new StringBuffer();

            if(bufferedReader != null){
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line + "\n");
                }

                bufferedReader.close();

            } else {
                return null;
            }
            return response.toString();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
