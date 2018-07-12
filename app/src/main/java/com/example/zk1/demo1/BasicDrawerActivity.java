package com.example.zk1.demo1;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.zk1.R;

public class BasicDrawerActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private String TAG = "BasicDrawerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//      抽屉的滑动监听器
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//              抽屉滑动时会被回调的方法
                Log.i(TAG, "onDrawerSlide:slideOffset====== "+slideOffset);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
//              抽屉被打开时会被回调的方法
                Log.i(TAG, "onDrawerOpened: ");
            }
            @Override
            public void onDrawerClosed(View drawerView) {
//              抽屉被关闭时会被回调的方法
                Log.i(TAG, "onDrawerClosed: ");
            }
            @Override
            public void onDrawerStateChanged(int newState) {
//              当抽屉的状态发生改变时会回调的方法      newState:新的状态
//                0：表示关闭    1：表示打开      2：表示滑动
                Log.i(TAG, "onDrawerStateChanged: newState==="+newState);
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
//                打开左边的抽屉
                drawerLayout.openDrawer(Gravity.LEFT);
//                关闭抽屉的方法
//                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.btn2:
//                打开右边的抽屉
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }
}
