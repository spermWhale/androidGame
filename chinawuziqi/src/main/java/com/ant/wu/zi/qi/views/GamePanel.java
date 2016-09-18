/*
 * Copyright (C) 2016 The Android Open Source Project
 *  Created by  Ant  2016/9/14  10:03
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ant.wu.zi.qi.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ant.wu.zi.qi.R;
import com.ant.wu.zi.qi.utils.AntLog;

import java.util.ArrayList;

/**
 * Created by baichuan on 2016/9/14.
 */
public class GamePanel extends View {
    private String TAG = "GamePanel";

    private int MAX_LINE = 16;
    private int mWidth;
    private float lineHeight;
    private Paint paint = new Paint();

    private Bitmap whitePiece, blackPiece;
    private float rotailPieceOfLineHeight = 3 * 1.0f / 4;

    private boolean isWhait=true;
    private ArrayList<Point> whiteArray=new ArrayList<>();
    private ArrayList<Point> blackArray=new ArrayList<>();

    private int pee=10;


    public GamePanel(Context context) {
        super(context);
        init(null, 0);
    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs, int defStyleAttr) {
        AntLog.i(TAG, "init ant");
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        //init bitmap image
        whitePiece = BitmapFactory.decodeResource(getResources(), R.mipmap.whrite);
        blackPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.black);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mHeight = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(mWidth, mHeight);
        setMeasuredDimension(size, size);
        AntLog.i(TAG, "onMeasuer ant");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        AntLog.i(TAG, "onSizeChanged");
        mWidth = w;
        lineHeight = w / MAX_LINE;
        int piecewidth = (int) (lineHeight * rotailPieceOfLineHeight);
        blackPiece = Bitmap.createScaledBitmap(blackPiece, piecewidth, piecewidth, false);
        whitePiece = Bitmap.createScaledBitmap(whitePiece, piecewidth, piecewidth, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        AntLog.i(TAG, "onDraw ant");

        drawBoard(canvas);
        drawPiece(canvas);
    }


    private void drawBoard(Canvas canvas) {

        for (int i = 0; i < MAX_LINE; i++) {
            AntLog.i(TAG, "width=" + getWidth() + ",   " +
                    "lingheight=" + (getWidth() / MAX_LINE));
            float startX = lineHeight / 2;
            float stopX = mWidth - startX;
            float startY = (0.5f + i) * lineHeight;
            float stopY = startY;

            canvas.drawLine(startX, startY, stopX, startY, paint);
            canvas.drawLine(startY, startX, stopY, stopX, paint);
        }
    }

    /**
     * 绘制棋子
     * @param canvas
     */
    private void drawPiece(Canvas canvas) {
        // 绘制白棋
        for(int i=0,n=whiteArray.size();i<n;i++){
            Point po=whiteArray.get(i);
            canvas.drawBitmap(whitePiece,po.x*lineHeight+pee,po.y*lineHeight+pee,null);
        }
        //绘制黑棋
        for(int i=0,n=blackArray.size();i<n;i++){
            Point po=blackArray.get(i);
            canvas.drawBitmap(blackPiece,po.x*lineHeight+pee,po.y*lineHeight+pee,null);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            AntLog.i(TAG,"this is action up");

            int x= (int) event.getX();
            int y= (int) event.getY();
            Point point=new Point((int)(x/lineHeight),(int)(y/lineHeight));
            if(whiteArray.contains(point) || blackArray.contains(point)){
                return false;
            }
            if(isWhait){
                whiteArray.add(point);
            }else{
                blackArray.add(point);
            }
            isWhait=!isWhait;
            invalidate();
            return true;
        }

        return super.onTouchEvent(event);
    }
}
