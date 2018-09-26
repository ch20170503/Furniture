package com.hc.http;

import android.util.Log;

import com.hc.bean.KEYVALUE;
import com.hc.bean.UserInfoList.UserList;
import com.hc.bean.host.HostList;
import com.hc.util.GsonUtil;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserHttp {
    private static final String TAG = "HostHttp";

    public static UserList postUserList(String urlString , JSONObject jsonObject){
        Log.e(TAG,"KEYVALUE.getShine_useruid():"+ KEYVALUE.getShine_useruid());
        Log.e(TAG,"KEYVALUE.getShine_userkey():"+KEYVALUE.getShine_userkey());
        String result="";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("SHINE_USERUID", KEYVALUE.getShine_useruid());
            connection.setRequestProperty("SHINE_USERKEY",KEYVALUE.getShine_userkey());
            connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");//**注意点1**，需要此格式，后边这个字符集可以不设置
            connection.connect();
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            out.write(jsonObject.toString().getBytes("UTF-8"));//**注意点2**，需要此格式
            out.flush();
            out.close();
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));//**注意点3**，需要此格式
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
                result += lines;
            }
            Log.e(TAG,"listData:"+result);
            reader.close();
            // 断开连接
            connection.disconnect();
            UserList hostList =  GsonUtil.parseJsonWithGson(result,UserList.class);
            int error = hostList.getResultType();
            if (error == 3){
                return hostList;
            }

            if (error != 3 && error != 4){
                return null;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
