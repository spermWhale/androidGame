package com.ant.wu.zi.qi;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.ant.wu.zi.qi.activities.HelpActivity;
import com.ant.wu.zi.qi.activities.IndexActivity;
import com.ant.wu.zi.qi.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button start, setting, help, exit;

    @Override
    public int getLaout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        start = (Button) findViewById(R.id.ac_main_start);
        setting = (Button) findViewById(R.id.ac_main_setting);
        help = (Button) findViewById(R.id.ac_main_help);
        exit = (Button) findViewById(R.id.ac_main_exit);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        start.setOnClickListener(this);
        setting.setOnClickListener(this);
        help.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_main_start:
                startGame();
                break;
            case R.id.ac_main_exit:
                this.finish();
                break;
            case R.id.ac_main_help:
                jump2Other(HelpActivity.class);
                break;
        }
    }

    private void startGame() {
        Intent startGame = new Intent(this, IndexActivity.class);
        this.startActivity(startGame);
    }


}
