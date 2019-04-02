package com.example.sherisesinyeelam.java4kids.FriendsPage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.searchEngine.SenderReceiver;
import com.example.sherisesinyeelam.java4kids.R;

import java.util.Calendar;

public class SearchForFriendsEngine extends AppCompatActivity {

    String urlAddress = "http://sinyeelam.com/java4Kids/searchUser.php";
    SearchView searchView;
    ListView listView; // show searched results
    TextView noDataText, noNetworkText; // display relevant message when error occurs.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_friends_engine);
        autoChangeBackground();

        //add back button on the toolbar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.show_listFriend);
        searchView = (SearchView) findViewById(R.id.searchView);
        noDataText = (TextView) findViewById(R.id.noData);
        noNetworkText = (TextView) findViewById(R.id.noNetwork);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SenderReceiver senderReceiver = new SenderReceiver(SearchForFriendsEngine.this, urlAddress, query, listView, noDataText, noNetworkText);
                senderReceiver.execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                SenderReceiver senderReceiver = new SenderReceiver(SearchForFriendsEngine.this, urlAddress, query, listView, noDataText, noNetworkText);
                senderReceiver.execute();
                return false;
            }
        });
    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.default_background);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        SearchForFriendsEngine.super.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar c = Calendar.getInstance();
                                int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
                                if(timeOfDay >= 7 && timeOfDay < 17){
                                    layout.setBackgroundResource(R.color.LightSkyBlue);
                                }
                                else{
                                    layout.setBackgroundResource(R.color.PaleGoldenrod);
                                }
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {}
            }
        };
        t.start();

    }

    // return back and refresh friend list
    @Override
    public boolean onSupportNavigateUp() {
        setResult(Activity.RESULT_OK);
        onBackPressed();
        return true;
    }
}


// Camposha (2017) Android PHP MySQL - ListView ServerSide Search/Filter [online]. available at https://camposha.info/source/android-mysql-listview-search-source [access 13/01/2019]