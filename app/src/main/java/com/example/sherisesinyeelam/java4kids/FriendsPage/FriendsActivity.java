package com.example.sherisesinyeelam.java4kids.FriendsPage;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.getfriendlist.Downloader;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;

public class FriendsActivity extends Fragment {

    //String url = "http://sinyeelam.com/friend.php";
    String url = "http://sinyeelam.com/java4Kids/userFriendsList.php";
    TextView noNetwork, notLogin;

    static final int REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_friends, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Friends");

        noNetwork = (TextView) getView().findViewById(R.id.noNetwork);
        notLogin = (TextView) getView().findViewById(R.id.not_login);
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);

        notLogin.setVisibility(View.VISIBLE);

        if(SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
            //if user already logged in.
            notLogin.setVisibility(View.INVISIBLE);

            int userID = SharedPrefManager.getInstance(getActivity()).getUserID();
            final ListView listView = (ListView) getView().findViewById(R.id.fd_list);
            final Downloader downloader = new Downloader(getActivity(), url, listView, noNetwork, userID);
            downloader.execute();

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), SearchForFriendsEngine.class);
                    //startActivity(intent);
                    startActivityForResult(intent,REQUEST_CODE);
                }
            });
        } else {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Please login to add friend", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // allow to refresh friend list when returned from the search engine.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                int userID = SharedPrefManager.getInstance(getActivity()).getUserID();
                final ListView listView = (ListView) getView().findViewById(R.id.fd_list);
                final Downloader downloader = new Downloader(getActivity(), url, listView, noNetwork, userID);
                downloader.execute();
            }
        }
    }
}

// stackoverflow(2014). This Activity already has an action bar supplied by the window decor [online]. Available at https://stackoverflow.com/questions/26515058/this-activity-already-has-an-action-bar-supplied-by-the-window-decor [accessed 27/12/2018]
// ProgrammingWizards TV (2016). Android MySQL Database 02 - Select and Show In ListView [HttpUrlConnection] [online]. Available at https://www.youtube.com/watch?v=WPJxnQpb_Vk [accessed 09/03/2019]