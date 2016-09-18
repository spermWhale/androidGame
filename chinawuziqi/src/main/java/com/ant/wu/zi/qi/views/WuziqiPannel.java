/*
 * Copyright (C) 2016 The Android Open Source Project
 *  Created by  Ant  2016/9/16  10:59
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
import com.ant.wu.zi.qi.apps.Apps;
import com.ant.wu.zi.qi.views.listeners.WuziqiListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baichuan on 2016/9/16.
 */
public class WuziqiPannel extends View implements WuziqiListener {
    private String TAG = "ant";

    private final int MAX_LINE = Apps.MAX_LINE;
    private final float remoteOfPiec = 0.75f;

    private float lineHeight,pee=0.0f;
    private Paint paint;
    private int width;
    private boolean isWhith = true;
    private Bitmap whitePiece, blackPiece;

    private List<Point> whiteList, blackList;


    public WuziqiPannel(Context context) {
        super(context);
        init(null, 0);
    }

    public WuziqiPannel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public WuziqiPannel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        // init piece ant
        whitePiece = BitmapFactory.decodeResource(getResources(), R.mipmap.whrite);
        blackPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.black);

        whiteList = new ArrayList<>();
        blackList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int size = Math.min(width, height);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        lineHeight = width * 1.0f / MAX_LINE;
        whitePiece = Bitmap.createScaledBitmap(whitePiece, (int) (lineHeight * remoteOfPiec), (int) (lineHeight * remoteOfPiec), false);
        blackPiece = Bitmap.createScaledBitmap(blackPiece, (int) (lineHeight * remoteOfPiec), (int) (lineHeight * remoteOfPiec), false);
        pee=blackPiece.getWidth()*1.0f/8;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBorad(canvas);
        drawPiece(canvas);
    }


    private void drawBorad(Canvas canvas) {
        float startX, startY, stopX, stopY;
        for (int i = 0; i < MAX_LINE; i++) {
            startX = lineHeight / 2;
            startY = (0.5f + i) * lineHeight;
            stopX = width - startX;
            stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            canvas.drawLine(startY, startX, stopY, stopX, paint);
        }

    }

    private void drawPiece(Canvas canvas) {
        Point point = null;
        int i = 0, n = 0;
        for (i = 0, n = whiteList.size(); i < n; i++) {
            point = whiteList.get(i);
            canvas.drawBitmap(whitePiece, point.x * lineHeight+pee, point.y * lineHeight+pee, null);
        }
        for (i = 0, n = blackList.size(); i < n; i++) {
            point = blackList.get(i);
            canvas.drawBitmap(blackPiece, point.x * lineHeight+pee, point.y * lineHeight+pee, null);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        AntLog.i(TAG, "onTouchEvent");
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Point po = getAvaliablePoiont(event.getX(), event.getY());
            if (whiteList.contains(po) || blackList.contains(po)) {
                return false;
            }
            if (isWhith) {
                whiteList.add(po);
            } else {
                blackList.add(po);
            }
            isWhith = !isWhith;
            invalidate();
        }

        return true;
    }

    public Point getAvaliablePoiont(float x, float y) {
        Point pio = new Point();
        pio.set((int) (x / lineHeight), (int) (y / lineHeight));
        return pio;
    }


    /**
     * 新局开始
     */
    @Override
    public void restart() {
        if (whiteList != null && blackList != null) {
            whiteList.clear();
            blackList.clear();
            invalidate();
        }
    }

}
