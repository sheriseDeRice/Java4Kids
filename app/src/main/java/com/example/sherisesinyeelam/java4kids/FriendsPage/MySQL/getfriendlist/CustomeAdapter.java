package com.example.sherisesinyeelam.java4kids.FriendsPage.MySQL.getfriendlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
            convertView = layoutInflater.inflate(R.layout.content_friend_list_model, parent, false);

        }

        TextView ranking = convertView.findViewById(R.id.friends_ranking);
        TextView friendname = convertView.findViewById(R.id.friends_firstname);
        TextView friendLevel = convertView.findViewById(R.id.friends_level);
        TextView friendScore = convertView.findViewById(R.id.friends_score);

        ranking.setText((position+1)+"");
        String displayName = spaceCrafts.get(position).getUsername();
        if(SharedPrefManager.getInstance(context).getUsername().equals(spaceCrafts.get(position).getUsername())){
            displayName = "You";
        }
        friendname.setText(displayName);
        friendLevel.setText(spaceCrafts.get(position).getLevel()+"");
        friendScore.setText(spaceCrafts.get(position).getScore()+"");

        // item clicks
        // todo delete friend
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, spaceCrafts.get(position).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }
}
