/*
 * Copyright (C) 2016 The Android Open Source Project
 *  Created by  Ant  2016/9/16  9:58
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
package com.ant.wu.zi.qi.activities;

import android.media.MediaPlayer;
import android.view.View;

import com.ant.wu.zi.qi.R;
import com.ant.wu.zi.qi.base.BaseActivity;
import com.ant.wu.zi.qi.views.WuziqiPannel;
import com.ant.wu.zi.qi.views.listeners.WuziqiListener;

/**
 * Created by baichuan on 2016/9/16.
 */
public class IndexActivity extends BaseActivity {
    private WuziqiPannel panel;
    private WuziqiListener listener;
    private MediaPlayer mp;

    @Override
    public int getLaout() {
        return R.layout.ac_index_layout;
    }

    @Override
    public void initView() {
        panel = (WuziqiPannel) findViewById(R.id.ac_index_game_panel);
        listener = panel;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mp = MediaPlayer.create(this, R.raw.start);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public void antClick(View v) {
        switch (v.getId()) {
            case R.id.ac_index_menu_restart:
                listener.restart();
                break;
            case R.id.ac_index_menu_music:
                break;
            case R.id.ac_index_menu_bgmusic:
                break;
            case R.id.ac_index_menu_about:
                jump2Other(HelpActivity.class);
                break;
            case R.id.ac_index_menu_back:
                this.finish();
                break;
        }

    }
}
