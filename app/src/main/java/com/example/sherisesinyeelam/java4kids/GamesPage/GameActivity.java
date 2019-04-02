package com.example.sherisesinyeelam.java4kids.GamesPage;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.GamesPage.ChoosingGame.TheChosingGameActivity;
import com.example.sherisesinyeelam.java4kids.GamesPage.DragAndDropGame.TheDragAndDropGameActivity;

public class GameActivity extends Fragment {

    ImageButton snake_lv1, chosing_level1, matching_level1, linking_level1, dragNdrop_level1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_game, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Games");

        chosing_level1 = (ImageButton) getView().findViewById(R.id.chosing_level1);
        chosing_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TheChosingGameActivity.class);
                startActivity(intent);
            }
        });

        dragNdrop_level1 = (ImageButton) getView().findViewById(R.id.drag_and_drop_level1);
        dragNdrop_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TheDragAndDropGameActivity.class);
                startActivity(intent);
            }
        });

        // todo, future game implementation
//        matching_level1 = (ImageButton) getView().findViewById(R.id.matching_level1);
//        matching_level1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), TheMatchingGameActivity.class);
//                startActivity(intent);
//            }
//        });

//        linking_level1 = (ImageButton) findViewById(R.id.linking_level1);
//        linking_level1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(GameActivity.this, TheLinkingGameActivity.class);
//                startActivity(intent);
//            }
//        });

//        snake_lv1 = (ImageButton) findViewById(R.id.snake_level1);
//        snake_lv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(GameActivity.this, SnakeActivity.class);
//                startActivity(intent);
//            }
//        });
    }

}
