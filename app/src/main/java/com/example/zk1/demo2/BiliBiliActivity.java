package com.example.zk1.demo2;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.zk1.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BiliBiliActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView rv;
    private LinearLayoutManager manager;
    private List<Map<String, String>> contactsList;
    private List<Map<String, String>> callLogList;
    private ContactAdapter contactAdapter;
    private CalllogAdapter calllogAdapter;

    private List<FoodBean.DataBean> cookList = new ArrayList<>();

    private String url = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1";
    private CookAdapter cookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        隐藏顶部标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bili_bili);
        toolbar = (Toolbar) findViewById(R.id.bili_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.bili_drawerlayout);
        setToolbar();
//        1.查找控件
        rv = (RecyclerView) findViewById(R.id.bilirv);
//        2.设置布局管理器
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(manager);
//        3.数据源     三个：1.通讯录   2.通话记录  3网络数据
        contactsList = getContacts();
        callLogList = getCallLog();
//         4.获取适配器对象
        contactAdapter = new ContactAdapter(this,contactsList);
        calllogAdapter = new CalllogAdapter(this,callLogList);
        cookAdapter = new CookAdapter(this,cookList);
//        5.设置适配器对象
        rv.setAdapter(contactAdapter);
        loadWebData(url);
    }
    /**
     * 加载网络数据的方法
     * */
    @SuppressLint("StaticFieldLeak")
    private void loadWebData(final String url) {
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
//                执行网络请求
                String s = HttpUtils.getStringContent(url);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s!=null&&!s.isEmpty()) {
//                    解析数据
                    Gson gson = new Gson();
                    FoodBean foodBean = gson.fromJson(s, FoodBean.class);
                    cookList.addAll(foodBean.getData());
                    cookAdapter.notifyDataSetChanged();
                }
            }
        }.execute();
    }

    public void setToolbar() {
        toolbar.inflateMenu(R.menu.bili);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                判断左边的抽屉是否已经被打开，如果打开就关闭，如果关闭了，就打开
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
//        添加menu的点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Toast.makeText(BiliBiliActivity.this,"您点击了搜索按钮",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.download:
                        Toast.makeText(BiliBiliActivity.this,"您点击了下载按钮",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.shop:
                        Toast.makeText(BiliBiliActivity.this,"您点击了购物按钮",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.game:
                        Toast.makeText(BiliBiliActivity.this,"您点击了游戏按钮",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 获取手机通讯录信息  day02/demo01
     * */
    public List<Map<String,String>> getContacts(){
        List<Map<String,String>>list = new ArrayList<>();
//        1.通讯录是系统数据库的内容，所以通过ContentResolver获取
        ContentResolver resolver = getContentResolver();
//        2.获取Uri地址 :只能够获取到id和name
        Uri rawUri = ContactsContract.Contacts.CONTENT_URI;
//        获取到电话号码的Uri
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        3.开始查询
        Cursor rawCursor = resolver.query(rawUri, null, null, null, null);
//        4.移动cursor获取信息
        while (rawCursor.moveToNext()) {
            String id = rawCursor.getString(rawCursor.getColumnIndex("_id"));
            String display_name = rawCursor.getString(rawCursor.getColumnIndex("display_name"));
            String phone = "";
            Cursor phoneCursor = resolver.query(phoneUri, null, "raw_contact_id=?", new String[]{id}, null);
            if (phoneCursor.moveToFirst()) {
                phone = phoneCursor.getString(phoneCursor.getColumnIndex("data1"));
            }
            Map<String,String>map = new HashMap<>();
            map.put("id",id);
            map.put("name",display_name);
            map.put("phone",phone);
//            将在循环当中获取到的map添加到list集合当中
            list.add(map);
        }
        return list;
    }
    /**
     * 获取通话记录数据源的方法  day01/demo01
     * */
    public List<Map<String,String>>getCallLog(){
//        1.通话记录也是系统数据库当中的内容，所以也需要通过ContentResolver获取
        ContentResolver resolver = getContentResolver();
//        2.获取访问的uri地址
        Uri uri = CallLog.Calls.CONTENT_URI;
//        3.开始查询
        Cursor cursor = resolver.query(uri, null, null, null, null);
//
        List<Map<String,String>>list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(CallLog.Calls._ID));
            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            long l = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(l);
            String time = sdf.format(date);
            Map<String,String>map = new HashMap<>();
            map.put("id",id);
            map.put("number",number);
            map.put("time",time);
            list.add(map);
        }
        return list;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_drawer_btn1:     //联系人信息
                rv.setAdapter(contactAdapter);
                drawerLayout.closeDrawers();
                break;
            case R.id.left_drawer_btn2:     //通话记录
                rv.setAdapter(calllogAdapter);
                drawerLayout.closeDrawers();
                break;
            case R.id.left_drawer_btn3:     //连接网络数据
                rv.setAdapter(cookAdapter);
                drawerLayout.closeDrawers();
                break;
        }
    }
}
