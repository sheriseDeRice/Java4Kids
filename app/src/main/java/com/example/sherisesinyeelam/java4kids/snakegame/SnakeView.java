package com.example.sherisesinyeelam.java4kids.snakegame1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SnakeView extends View {

    private Paint paint = new Paint();
    private TileType snakeViewMap[][];

    public SnakeView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    public void setSnakeViewMap(TileType[][] map){
        this.snakeViewMap = map;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(snakeViewMap != null){
            float tileSizeX = canvas.getWidth() / snakeViewMap.length;
            float tileSizeY = canvas.getHeight() / snakeViewMap[0].length;

            float circleSize = Math.min(tileSizeX,tileSizeY)/2;

            for(int x = 0; x <snakeViewMap.length; x++){
                for (int y = 0; y < snakeViewMap[x].length; y++){
                    switch (snakeViewMap[x][y]){
                        case Nothing:
                            paint.setColor(Color.WHITE);
                            break;
                        case Wall:
                            paint.setColor(Color.BLACK);
                            break;
                        case SnakeHead:
                            paint.setColor(Color.BLUE);
                            break;
                        case SnakeTail:
                            paint.setColor(Color.BLUE);
                            break;
                        case Apple:
                            paint.setColor(Color.RED);
                            break;
                    }

                    canvas.drawCircle(x * tileSizeX + tileSizeX/2f + circleSize/2,
                            y * tileSizeY + tileSizeY/2f + circleSize/2, circleSize, paint);
                }
            }
        }
    }
}
