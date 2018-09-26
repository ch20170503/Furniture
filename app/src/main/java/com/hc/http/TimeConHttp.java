package com.hc.http;

import android.util.Log;

import com.hc.ReturnInfo;
import com.hc.bean.ResultInfo;
import com.hc.util.GsonUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TimeConHttp {
    private static final String TAG = "Loginhttp";
    //网络请求
    public static boolean HostPost(String value){
        String StringUrl=GsonUtil.urlT+"/Send/SingleLamp?Value="+value;
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
                String LoginInfo =  response.body().string();
                ResultInfo resuitInfo =  GsonUtil.parseJsonWithGson(LoginInfo,ResultInfo.class);
                ReturnInfo returnInfo = GsonUtil.parseJsonWithGson(LoginInfo,ReturnInfo.class);
                Log.e(TAG,"发送："+ LoginInfo);
                if (returnInfo.getErrNum() == 300100){
                    return false;
                }
                if (returnInfo.getErrNum() == 300103){
                    return false;
                }
                if (returnInfo.getErrNum() == 300110){
                    return false;
                }
                int error = resuitInfo.getsResultType();
                if (error <= 3){
                    return true;
                }
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
