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
package com.wu.zi.qi.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.wu.zi.qi.utils.AntLog;

/**
 * Created by baichuan on 2016/9/14.
 */
public class GamePanel extends View {
    private String TAG = "GamePanel";

    private int MAX_LINE = 10;
    private int mWidth;
    private float lineHeight;
    private Paint paint = new Paint();


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
        paint.setColor(0x000000);
        paint.setAntiAlias(true);
//        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
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
        lineHeight = mWidth * 1.0f / MAX_LINE;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        AntLog.i(TAG, "onDraw ant");

        drawBoard(canvas);
        drawPiece(canvas);
    }


    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < MAX_LINE; i++) {
            float startX = (lineHeight / 2);
            float stopX = mWidth - startX;
            float startY = (float) (0.5 + i) * (lineHeight / 2);
            float stopY = startY;

            canvas.drawLine(startX, startY, stopX, startY, paint);
        }


    }

    private void drawPiece(Canvas canvas) {

    }
}
