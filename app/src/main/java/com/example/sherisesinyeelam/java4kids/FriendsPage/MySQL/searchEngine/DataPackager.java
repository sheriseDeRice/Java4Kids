package com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.searchEngine;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class DataPackager {

    String username;

    public DataPackager(String query){
        this.username = query;
    }

    public String packageData(){
        JSONObject jsonObject = new JSONObject();
        StringBuffer queryString = new StringBuffer();

        try{
            jsonObject.put("username", username);

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

//ProgrammingWizard TV (2016). Android MySQL Database 04 : ListView - ServerSide Search/Filter [HttpURLConnection] [online]. available at https://www.youtube.com/watch?v=YWe2s22f9hw [access 13/01/2019]