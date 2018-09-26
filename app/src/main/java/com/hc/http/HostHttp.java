package com.hc.http;

import android.util.Log;

import com.google.gson.Gson;
import com.hc.bean.KEYVALUE;
import com.hc.bean.ResultInfo;
import com.hc.bean.UserInfoList.UserAdd;
import com.hc.bean.UserInfoList.UserUpdataList;
import com.hc.bean.host.HostList;
import com.hc.bean.host.HostUpdataList;
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
import java.util.List;

public class HostHttp {
    private static final String TAG = "HostHttp";

    public static HostList postHostList(String urlString , JSONObject jsonObject){
        Log.e(TAG,"KEYVALUE.getShine_useruid():"+KEYVALUE.getShine_useruid());
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
            HostList hostList =  GsonUtil.parseJsonWithGson(result,HostList.class);
            int error = hostList.getsResultType();
            String message = hostList.getsMessage();
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

    public static boolean conhttp(String rurl, List<HostUpdataList> hostupdataLists, List<String> id, List<UserAdd> userAdds,List<UserUpdataList> lists){
        String result = "";
        try {
            URL url = new URL(rurl );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json;charset=utf-8");
            connection.setRequestProperty("shine_useruid",KEYVALUE.getShine_useruid());
            connection.setRequestProperty("shine_userkey",KEYVALUE.getShine_userkey());
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            connection.connect();
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());

            if (hostupdataLists != null){
              Gson gson = new Gson() ;
                out.write(gson.toJson(hostupdataLists).toString().getBytes("UTF-8"));//**注意点2**，需要此格式
            }
            if (id != null){
                Gson gson = new Gson() ;
                out.write(gson.toJson(id).toString().getBytes("UTF-8"));//**注意点2**，需要此格式
            }
            if (userAdds != null){
                Gson gson = new Gson() ;
                out.write(gson.toJson(userAdds).toString().getBytes("UTF-8"));//**注意点2**，需要此格式
            }
            if (lists != null){
                Gson gson = new Gson() ;
                out.write(gson.toJson(lists).toString().getBytes("UTF-8"));//**注意点2**，需要此格式
                Log.e(TAG,"gson.toJson(lists).toString():"+gson.toJson(lists).toString());
            }
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
                result += lines;
            }
            Log.e(TAG,"HostUpdataList:"+result);
            reader.close();
            connection.disconnect();
            ResultInfo resuitInfo =  GsonUtil.parseJsonWithGson(result,ResultInfo.class);
            int error = resuitInfo.getsResultType();
            String message = resuitInfo.getsMessage();

            if (error == 3){
                return true;
            }

            if (error != 3 && error != 4){
                return false;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
