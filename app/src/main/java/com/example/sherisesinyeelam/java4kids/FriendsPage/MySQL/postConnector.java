package com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class postConnector {

    public static HttpURLConnection connector (String urlAddress){

        try{
            URL url = new URL(urlAddress);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

//            //Set props below
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setConnectTimeout(20000);
//            httpURLConnection.setReadTimeout(20000);
//            httpURLConnection.setDoInput(true);

            // set properties
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            return httpURLConnection;

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;

    }
}
