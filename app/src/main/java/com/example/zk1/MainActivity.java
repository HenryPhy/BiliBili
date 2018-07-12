package com.example.zk1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zk1.demo1.BasicDrawerActivity;
import com.example.zk1.demo2.BiliBiliActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn1:
                intent.setClass(this, BasicDrawerActivity.class);
                break;
            case R.id.btn2:
                intent.setClass(this, BiliBiliActivity.class);
                break;
        }
        startActivity(intent);
    }
}
