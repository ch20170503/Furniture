package com.hc.http;

import android.util.Log;

import com.hc.bean.KEYVALUE;
import com.hc.bean.ResultInfo;
import com.hc.util.GsonUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Loginhttp {
    private static final String TAG = "Loginhttp";
    static final KEYVALUE keyvalue = new KEYVALUE();
    //网络请求
    public static ResultInfo LoginPost(String name,String pwd){

        String StringUrl = GsonUtil.urlT+"/Account/Login?uname="+name+"&pwd="+pwd;
        Log.e(TAG,"URL:"+StringUrl);
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .build();
            Request request = new Request.Builder()
                    .url(StringUrl)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    Log.e(TAG,"请求头："+responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    if(responseHeaders.name(i).equals("SHINE_USERKEY")){
                        keyvalue.setShine_userkey(responseHeaders.value(i));
                    }
                    if (responseHeaders.name(i).equals("SHINE_USERUID") ){
                        keyvalue.setShine_useruid(responseHeaders.value(i));
                    }
                }
                String LoginInfo =  response.body().string();
                ResultInfo users = GsonUtil.parseJsonWithGson(LoginInfo, ResultInfo.class);
                Log.e(TAG,"登录："+ LoginInfo);
                return users;
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return null;
        }
    }
}
