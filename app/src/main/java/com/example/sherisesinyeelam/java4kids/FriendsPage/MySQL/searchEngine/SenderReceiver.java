package com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.searchEngine;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.postConnector;


public class SenderReceiver extends AsyncTask<Void,Void,String> {

    Context context;
    String urlAddress;
    String query;
    ListView listView;
    TextView noDataText, noNetworkText;

    ProgressDialog progressDialog;

    public SenderReceiver(Context context, String urlAddress, String query, ListView listView, TextView...textViews){
        this.context = context;
        this.urlAddress = urlAddress;
        this.query = query;
        this.listView = listView;

        this.noDataText = textViews[0];
        this.noNetworkText = textViews[1];
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Searching... Please wait");
        progressDialog.show();

    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.sendAndReceive();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressDialog.dismiss();

        // reset Listview
        listView.setAdapter(null);

        if(s != null){
            if (!s.contains("null")){
                Parser parser = new Parser(context, s, listView);
                parser.execute();

                noNetworkText.setVisibility(View.INVISIBLE);
                noDataText.setVisibility((View.INVISIBLE));
            } else {
                noNetworkText.setVisibility(View.INVISIBLE);
                noDataText.setVisibility((View.VISIBLE));
            }
        } else {
            noNetworkText.setVisibility(View.VISIBLE);
            noDataText.setVisibility((View.INVISIBLE));
        }
    }

    private String sendAndReceive(){

        HttpURLConnection connection = postConnector.connector(urlAddress);

        if(connection == null){
            return null;
        }

        try {
            OutputStream outputStream = connection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(new DataPackager(query, context).packageData());
            bufferedWriter.flush();

            // release res
            bufferedWriter.close();
            outputStream.close();

            // get response code
            int responseCode = connection.getResponseCode();

            // decode
            if(responseCode == connection.HTTP_OK){
                // response code = 200

                // return data stream
                InputStream inputStream = connection.getInputStream();

                // read it
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuffer response = new StringBuffer();

                if(bufferedReader != null){
                    while ((line = bufferedReader.readLine()) != null){
                        response.append(line+"\n");
                    }
                } else{
                    return null;
                }
                return response.toString();

            } else {
                return String.valueOf(responseCode);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
