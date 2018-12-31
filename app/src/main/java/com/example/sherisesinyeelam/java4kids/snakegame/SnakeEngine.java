package com.example.sherisesinyeelam.java4kids.snakegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.Random;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class SnakeEngine extends SurfaceView implements Runnable {

    private Thread thread = null; //game thread for the main game loop
    private Context context; //reference to the Activity

    // TODO create a asset folder and add sounds file (.ogg)
    // For the sound effects
//    private SoundPool soundPool;
//    private int eat_code = -1;
//    private int snake_crash = -1;

    // The snake movement
    public enum HeadingTo {UP, RIGHT, DOWN, LEFT} // movement Heading tracker
    private HeadingTo heading = HeadingTo.RIGHT; // Starting point

    // To hold the screen size in pixels
    private int screenX;
    private int screenY;

    private int snakeLength;

    // Where is coding pieces hiding?
    private int code_pieceX;
    private int code_pieceY;

    private int blockSize; // The size in pixels of a snake segment

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int numBlocksHigh;

    private long nextFrameTime; //Control pausing between updates
    private final long FPS = 10; // Update the game 10 times per second
    private final long MILLIS_PER_SECOND = 3000; // There are 1000 milliseconds in a second

    private int score; // storing the score

    // The location in the grid of all the segments
    private int[] snakeXs;
    private int[] snakeYs;

    private volatile boolean isPlaying; // Is the game currently playing?
    // volatile -> prevent threads from keeping the copy of variable in thread local cache.
    public int max_number_of_death = 3;

    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    // The constructor
    public SnakeEngine(Context context, Point size) {
        super(context);

        context = context;

        screenX = size.x;
        screenY = size.y;

        // Work out how many pixels each block is
        blockSize = screenX / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        numBlocksHigh = screenY / blockSize;

        // Set the sound up
//        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
//        try {
//            // Create objects of the 2 required classes
//            // Use m_Context because this is a reference to the Activity
//            AssetManager assetManager = context.getAssets();
//            AssetFileDescriptor descriptor;
//
//            // Prepare the two sounds in memory
//            descriptor = assetManager.openFd("get_mouse_sound.ogg");
//            eat_code = soundPool.load(descriptor, 0);
//
//            descriptor = assetManager.openFd("death_sound.ogg");
//            snake_crash = soundPool.load(descriptor, 0);
//
//        } catch (IOException e) {
//            // Error
//        }

        // Initialize the drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

        // If you score 200 you are rewarded with a crash achievement!
        snakeXs = new int[200];
        snakeYs = new int[200];

        // Start the game
        newGame();
    }

    // Making the thread run the game loop
    @Override
    public void run() {
        while (isPlaying) {
            // Update 10 times a second
            if(updateRequired()) {
                update();
                draw();
            }
        }
    }

    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void newGame() {
        // Start with a single snake segment
        snakeLength = 1;
        snakeXs[0] = NUM_BLOCKS_WIDE / 2;
        snakeYs[0] = numBlocksHigh / 2;

        // Get Code ready for dinner
        spawnCode();

        // Reset the score
        score = 0;

        // Setup nextFrameTime so an update is triggered
        nextFrameTime = System.currentTimeMillis();
    }

    // TODO
//    public int get_current_Max_death(){
//        return max_number_of_death;
//    }

    public void endGame() {
        // TODO ask if user want to exit the game
        // show alert dialog
//        AlertDialog.Builder alert = new AlertDialog.Builder(super.getContext());
//        alert.setTitle("Exit Alert");
//        alert.setMessage("You have died 3 times, Do you want to exit the game?");
//        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                System.exit(0);
//            }
//        });
//        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                newGame();
//                max_number_of_death = 3;
//            }
//        });
//        alert.create().show();






    }

    public void spawnCode() {
        Random random = new Random();
        code_pieceX = random.nextInt(NUM_BLOCKS_WIDE - 1) + 1;
        code_pieceY = random.nextInt(numBlocksHigh - 1) + 1;
    }

    private void eatCode(){
        // Got the correct piece!
        // Increase the size of the snake
        snakeLength++;
        //replace Code
        // This reminds me of Edge of Tomorrow. One day Code will be ready!
        spawnCode();
        //add to the score
        score = score + 10;
        //soundPool.play(eat_code, 1, 1, 0, 0, 1);
    }

    private void moveSnake(){
        // Move the body
        for (int i = snakeLength; i > 0; i--) {
            // Start at the back and move it
            // to the position of the segment in front of it
            snakeXs[i] = snakeXs[i - 1];
            snakeYs[i] = snakeYs[i - 1];

            // Exclude the head because
            // the head has nothing in front of it
        }

        // Move the head in the appropriate heading
        switch (heading) {
            case UP:
                snakeYs[0]--;
                break;

            case RIGHT:
                snakeXs[0]++;
                break;

            case DOWN:
                snakeYs[0]++;
                break;

            case LEFT:
                snakeXs[0]--;
                break;
        }
    }

    private boolean detectDeath(){
        // Has the snake died?
        boolean dead = false;

        // Hit the screen edge
        if (snakeXs[0] == -1) dead = true;
        if (snakeXs[0] >= NUM_BLOCKS_WIDE) dead = true;
        if (snakeYs[0] == -1) dead = true;
        if (snakeYs[0] == numBlocksHigh) dead = true;

        // Eaten itself?
        for (int i = snakeLength - 1; i > 0; i--) {
            if ((i > 4) && (snakeXs[0] == snakeXs[i]) && (snakeYs[0] == snakeYs[i])) {
                dead = true;
            }
        }

        if(dead == true){
            max_number_of_death--;
        }

        return dead;
    }

    public void update() {
        // Did the head of the snake eat Code?
        if (snakeXs[0] == code_pieceX && snakeYs[0] == code_pieceY) {
            eatCode();
        }

        moveSnake();

        if (detectDeath() && max_number_of_death>0) {
            //start again
            //soundPool.play(snake_crash, 1, 1, 0, 0, 1);

            newGame();
        }
        else if (max_number_of_death == 0){
            endGame();
        }
    }

    public void draw() {
        // Get a lock on the canvas
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

            // Fill the screen with Game Code School blue
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the color of the paint to draw the snake white
            paint.setColor(Color.argb(255, 255, 255, 255));

            // Scale the HUD text
            paint.setTextSize(90);
            canvas.drawText("Score:" + score, 10, 70, paint);

            // Draw the snake one block at a time
            for (int i = 0; i < snakeLength; i++) {
                canvas.drawRect(snakeXs[i] * blockSize,
                        (snakeYs[i] * blockSize),
                        (snakeXs[i] * blockSize) + blockSize,
                        (snakeYs[i] * blockSize) + blockSize,
                        paint);
            }

            // Set the color of the paint to draw Code red
            paint.setColor(Color.argb(255, 255, 0, 0));

            // Draw Code
            canvas.drawRect(code_pieceX * blockSize,
                    (code_pieceY * blockSize),
                    (code_pieceX * blockSize) + blockSize,
                    (code_pieceY * blockSize) + blockSize,
                    paint);

            // Unlock the canvas and reveal the graphics for this frame
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public boolean updateRequired() {

        // Are we due to update the frame
        if(nextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            nextFrameTime =System.currentTimeMillis() + MILLIS_PER_SECOND / FPS;

            // Return true so that the update and draw
            // functions are executed
            return true;
        }

        return false;
    }

    // Handling screen touches (player input)
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (motionEvent.getX() >= screenX / 2) {
                    switch(heading){
                        case UP:
                            heading = HeadingTo.RIGHT;
                            break;
                        case RIGHT:
                            heading = HeadingTo.DOWN;
                            break;
                        case DOWN:
                            heading = HeadingTo.LEFT;
                            break;
                        case LEFT:
                            heading = HeadingTo.UP;
                            break;
                    }
                } else {
                    switch(heading){
                        case UP:
                            heading = HeadingTo.LEFT;
                            break;
                        case LEFT:
                            heading = HeadingTo.DOWN;
                            break;
                        case DOWN:
                            heading = HeadingTo.RIGHT;
                            break;
                        case RIGHT:
                            heading = HeadingTo.UP;
                            break;
                    }
                }
        }
        return true;
    }

}