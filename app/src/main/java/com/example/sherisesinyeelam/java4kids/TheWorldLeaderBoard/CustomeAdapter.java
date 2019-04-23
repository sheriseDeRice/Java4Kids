package com.example.sherisesinyeelam.java4kids.TheWorldLeaderBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sherisesinyeelam.java4kids.LocalSharedPrefManager;
import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;

import java.util.ArrayList;

public class CustomeAdapter extends BaseAdapter {

    Context context;

    ArrayList<SpaceCraft> spaceCrafts;

    LayoutInflater layoutInflater;

    public CustomeAdapter(Context context, ArrayList<SpaceCraft> spaceCrafts) {
        this.context = context;
        this.spaceCrafts = spaceCrafts;

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return spaceCrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spaceCrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return spaceCrafts.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.content_all_user_list_model, parent, false);
        }

        TextView ranking = convertView.findViewById(R.id.ranking);
        TextView username = convertView.findViewById(R.id.user_username);
        TextView level = convertView.findViewById(R.id.userlevel);
        TextView score = convertView.findViewById(R.id.totalscore);

        ranking.setText((position+1)+"");

        String displayName = spaceCrafts.get(position).getUsername();
        if(SharedPrefManager.getInstance(context).checkLoginOrNot()){
            if (SharedPrefManager.getInstance(context).getUsername().equals(spaceCrafts.get(position).getUsername())){
                displayName = "You";
            }
        } else if (LocalSharedPrefManager.getInstance(context).checkLoginOrNot()){
            if (LocalSharedPrefManager.getInstance(context).getNickname().equals(spaceCrafts.get(position).getUsername())){
                displayName = "You";
            }
        }
        username.setText(displayName);

        level.setText(spaceCrafts.get(position).getLevel()+"");
        score.setText(spaceCrafts.get(position).getScore()+"");

        return convertView;
    }
}
