package com.example.sherisesinyeelam.java4kids.UserProfilePage;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sherisesinyeelam.java4kids.LocalSharedPrefManager;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;
import com.example.sherisesinyeelam.java4kids.R;

public class UserProfileActivity extends Fragment {

    private ImageView user_icon;
    private TextView user_name, user_firstname, user_level, user_point, user_ranking;
    private TableRow row_firstname, row_ranking;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Profile");

        user_icon = (ImageView) getView().findViewById(R.id.user_icon);
        user_name = (TextView) getView().findViewById(R.id.user_name);
        user_firstname = (TextView) getView().findViewById(R.id.user_firstname);
        user_level = (TextView) getView().findViewById(R.id.user_level);
        user_point = (TextView) getView().findViewById(R.id.user_point);
        user_ranking = (TextView) getView().findViewById(R.id.user_ranking);
        row_firstname = (TableRow) getView().findViewById(R.id.row2);
        row_ranking = (TableRow) getView().findViewById(R.id.row5);

        if(!SharedPrefManager.getInstance(getActivity()).checkLoginOrNot()){
            //if user is not logged in.
            if(LocalSharedPrefManager.getInstance(getActivity()).checkLoginOrNot()) {
                //String username = LocalSharedPrefManager.getInstance(getActivity()).getUsername();
                String nickname = LocalSharedPrefManager.getInstance(getActivity()).getNickname();
                int lv = LocalSharedPrefManager.getInstance(getActivity()).getUserLevel();
                int totalScore = LocalSharedPrefManager.getInstance(getActivity()).getUserTotalScore();
                int icon = LocalSharedPrefManager.getInstance(getActivity()).getUserIcon();
                user_icon.setImageResource(icon);
                TextView textView = (TextView) getView().findViewById(R.id.user_name_description);
                textView.setText("Nickname:");
                user_name.setText(nickname);
                row_firstname.setVisibility(View.GONE);
                user_level.setText("" + lv);
                user_point.setText("" + totalScore);
                row_ranking.setVisibility(View.GONE);
            }

        } else { // user logged in
            // update user information by taking data from the database.
            int iconID = SharedPrefManager.getInstance(getActivity()).getUserIcon();
            String username = SharedPrefManager.getInstance(getActivity()).getUsername();
            String firstname = SharedPrefManager.getInstance(getActivity()).getFirstname();
            int lv = SharedPrefManager.getInstance(getActivity()).getUserLevel();
            int totalScore = SharedPrefManager.getInstance(getActivity()).getUserTotalScore();
            int ranks = 1;
            user_icon.setImageResource(iconID);
            user_name.setText(username);
            user_firstname.setText(firstname);
            row_firstname.setVisibility(View.VISIBLE);
            user_level.setText("" + lv);
            user_point.setText("" + totalScore);
            user_ranking.setText("" + ranks);
            row_ranking.setVisibility(View.INVISIBLE);
        }
    }
}
