package com.example.sherisesinyeelam.java4kids.TheWorldLeaderBoard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherisesinyeelam.java4kids.R;

public class LeaderBoardActivity extends Fragment {

    String url = "http://sinyeelam.com/java4Kids/leadingBoard.php";
    TextView noNetwork;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_leaderboard, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("World Leading Board");

        noNetwork = (TextView) getView().findViewById(R.id.noNetwork);

        final ListView listView = (ListView) getView().findViewById(R.id.all_user_list);
        final Downloader downloader = new Downloader(getActivity(), url, listView, noNetwork);
        downloader.execute();
    }

}

// stackoverflow(2014). This Activity already has an action bar supplied by the window decor [online]. Available at https://stackoverflow.com/questions/26515058/this-activity-already-has-an-action-bar-supplied-by-the-window-decor [accessed 27/12/2018]
// ProgrammingWizards TV (2016). Android MySQL Database 02 - Select and Show In ListView [HttpUrlConnection] [online]. Available at https://www.youtube.com/watch?v=WPJxnQpb_Vk [accessed 09/03/2019]