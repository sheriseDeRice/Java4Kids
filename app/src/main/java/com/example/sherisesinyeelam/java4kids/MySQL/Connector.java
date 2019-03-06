package com.example.sherisesinyeelam.java4kids.MySQL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {

    public static HttpURLConnection connection (String urlAddress){

        try{

            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // set properties
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // return
            return connection;

        } catch (MalformedURLException e ){
            e.printStackTrace();
        } catch (IOException e ){
            e.printStackTrace();
        }

        return null;
    }
}
