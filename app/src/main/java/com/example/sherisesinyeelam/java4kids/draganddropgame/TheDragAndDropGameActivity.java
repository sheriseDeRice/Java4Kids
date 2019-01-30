package com.example.sherisesinyeelam.java4kids.draganddropgame;

import com.example.sherisesinyeelam.java4kids.R;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TheDragAndDropGameActivity extends AppCompatActivity {

    //TextView textPiece1, textPiece2, textPiece3, dropingArea;

    LinearLayout drop_target1, drop_target2, drop_target3, drop_target4, drop_target5;
    Button button1, button2, button3, button4, button5;

    Button piece1, piece2, piece3, piece4, piece5;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_drag_and_drop);

        //todo, apply what I've done and learnt from the tutorial to the actual quiz.

        // the following are sample, followed the tutorial.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // new version
        // target area
        drop_target1 = (LinearLayout) findViewById(R.id.drag_n_drop_target1);
        drop_target2 = (LinearLayout) findViewById(R.id.drag_n_drop_target2);
        drop_target3 = (LinearLayout) findViewById(R.id.drag_n_drop_target3);
        drop_target4 = (LinearLayout) findViewById(R.id.drag_n_drop_target4);
        drop_target5 = (LinearLayout) findViewById(R.id.drag_n_drop_target5);
        // place to drop in the area
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        // piece to drag from
        piece1 = (Button) findViewById(R.id.drag_piece1);
        piece2 = (Button) findViewById(R.id.drag_piece2);
        piece3 = (Button) findViewById(R.id.drag_piece3);
        piece4 = (Button) findViewById(R.id.drag_piece4);
        piece5 = (Button) findViewById(R.id.drag_piece5);

        drop_target1.setOnDragListener(dragListener);
        drop_target2.setOnDragListener(dragListener);
        drop_target3.setOnDragListener(dragListener);
        drop_target4.setOnDragListener(dragListener);
        drop_target5.setOnDragListener(dragListener);

        piece1.setOnLongClickListener(longClickListener);
        piece2.setOnLongClickListener(longClickListener);
        piece3.setOnLongClickListener(longClickListener);
        piece4.setOnLongClickListener(longClickListener);
        piece5.setOnLongClickListener(longClickListener);

        // old version
//        textPiece1 = (TextView) findViewById(R.id.drag_piece1);
//        textPiece2 = (TextView) findViewById(R.id.drag_piece2);
//        textPiece3 = (TextView) findViewById(R.id.drag_piece3);
//
//        dropingArea = (TextView) findViewById(R.id.target_drop_area);
//
//        textPiece1.setOnLongClickListener(longClickListener);
//        textPiece2.setOnLongClickListener(longClickListener);
//        textPiece3.setOnLongClickListener(longClickListener);
//
//        dropingArea.setOnDragListener(dragListener);

    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData clipData = ClipData.newPlainText("","");
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(clipData, dragShadowBuilder, v, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
//                    if(view.getId() == R.id.drag_piece1){
//                        dropingArea.setText("Text Piece 1 is dropped.");
//                    } else if(view.getId() == R.id.drag_piece2){
//                        dropingArea.setText("Text Piece 2 is dropped.");
//                    } else if(view.getId() == R.id.drag_piece3){
//                        dropingArea.setText("Text Piece 3 is dropped.");
//                    }
//                    view.animate().x(dropingArea.getX())
//                                  .y(dropingArea.getY())
//                                  .setDuration(700)
//                                  .start();

                    // if only allow one view to be dropped
//                    if(view.getId() == R.id.drag_piece1){
//                        view.animate().x(dropingArea.getX())
//                                      .y(dropingArea.getY())
//                                      .setDuration(700)
//                                      .start();
//                    } else {
//                        dropingArea.setText("Wrong Answer!");
//                        // can also add sound:
//                        // - create "raw" directory in res, then paste the mp3.
//                        // - do MediaPlayer mediaPlayer = MediaPlayer.create(TheDragAndDropGameActivity.this, R.raw.wrongSound);
//                        // - mediaPlayer.start();
//                    }

                    // new version
                    if(view.getId() == R.id.drag_piece3 && v.getId() == R.id.drag_n_drop_target1) {
                        // check if the correct piece is been dragged and dropped to a correct box.
                        Toast.makeText(TheDragAndDropGameActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                        // replace the blank to the correct answer.
                        LinearLayout oldTarget = (LinearLayout) view.getParent();
                        oldTarget.removeView(view);

                        LinearLayout newPiece = (LinearLayout) v;
                        button1.setVisibility(View.GONE);
                        newPiece.addView(view);

                        i++;

                    } else if(view.getId() == R.id.drag_piece2 && v.getId() == R.id.drag_n_drop_target2) {
                        Toast.makeText(TheDragAndDropGameActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                        LinearLayout oldTarget = (LinearLayout) view.getParent();
                        oldTarget.removeView(view);

                        LinearLayout newPiece = (LinearLayout) v;
                        button2.setVisibility(View.GONE);
                        newPiece.addView(view);

                        i++;

                    } else if(view.getId() == R.id.drag_piece5 && v.getId() == R.id.drag_n_drop_target3) {
                        Toast.makeText(TheDragAndDropGameActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                        LinearLayout oldTarget = (LinearLayout) view.getParent();
                        oldTarget.removeView(view);

                        LinearLayout newPiece = (LinearLayout) v;
                        button3.setVisibility(View.GONE);
                        newPiece.addView(view);

                        i++;

                    } else if(view.getId() == R.id.drag_piece1 && v.getId() == R.id.drag_n_drop_target4) {
                        Toast.makeText(TheDragAndDropGameActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                        LinearLayout oldTarget = (LinearLayout) view.getParent();
                        oldTarget.removeView(view);

                        LinearLayout newPiece = (LinearLayout) v;
                        button4.setVisibility(View.GONE);
                        newPiece.addView(view);

                        i++;

                    } else if(view.getId() == R.id.drag_piece4 && v.getId() == R.id.drag_n_drop_target5) {
                        Toast.makeText(TheDragAndDropGameActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                        LinearLayout oldTarget = (LinearLayout) view.getParent();
                        oldTarget.removeView(view);

                        LinearLayout newPiece = (LinearLayout) v;
                        button5.setVisibility(View.GONE);
                        newPiece.addView(view);

                        i++;

                    } else {
                        Toast.makeText(TheDragAndDropGameActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

            if (i == 5) {
                Toast.makeText(TheDragAndDropGameActivity.this, "Task Complete !", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
    };
}

// Obaidullah Masood (2016). Android Studio Tutorial | Drag and drop 1 | Layout Design [online]. available at https://www.youtube.com/watch?v=ZuPh3WPbytY&list=PLUopjVceujVTRJA4b-uhjTXbmSxTbs5lW&index=1 [assessed 24/01/2019]