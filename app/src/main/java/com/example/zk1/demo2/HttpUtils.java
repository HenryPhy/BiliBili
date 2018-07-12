package com.example.zk1.demo2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 帅比浩宇 on 2018/1/28.
 */

public class HttpUtils {

    public static String getStringContent(String path){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            int hasRead = 0;
            byte[]buf = new byte[1024];
            while((hasRead = is.read(buf))!=-1){
                baos.write(buf,0,hasRead);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  baos.toString();
    }
}
