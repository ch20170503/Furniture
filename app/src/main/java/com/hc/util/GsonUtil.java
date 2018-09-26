package com.hc.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hc.bean.UserInfoList.UserList;
import com.hc.bean.UserSelect;
import com.hc.bean.host.HostList;
import com.hc.http.HostHttp;
import com.hc.http.UserHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GsonUtil {
    public static final String urlT = "http://101.200.188.229:1258";

    /*
     * 封装的GSON解析工具类，提供泛型参数
     */
    // 将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    //将Json数组解析成相应的映射对象列表
    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }


    //点击确定(防止误操作)
    public abstract static class NoDoubleClickListener implements View.OnClickListener {
        public static final int MIN_CLICK_DELAY_TIME = 3000;
        private long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onNoDoubleClick(v);
            }
        }

        protected abstract void onNoDoubleClick(View v);
    }

    //判断网络是否可用
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;


    }


    public static HostList selectHostList() {
        UserSelect userSelect = new UserSelect();
        userSelect.setFilterGroup("");
        userSelect.setPageIndex(1);
        userSelect.setPageSize(10000000);
        userSelect.setSortField("CreatedTime");
        userSelect.setSortOrder("ASC");
        UserSelect userSelectHR = new UserSelect();
        userSelectHR.setFilterGroup("");
        userSelectHR.setPageIndex(1);
        userSelectHR.setPageSize(10000000);
        userSelectHR.setSortField("UpdateTime");
        userSelectHR.setSortOrder("desc");
        String realUrl = GsonUtil.urlT + "/Host/GetHostGridData";
        JSONObject obj = new JSONObject();
        try {
            obj.put("FilterGroup", userSelect.getFilterGroup());
            obj.put("PageIndex", userSelect.getPageIndex());
            obj.put("PageSize", userSelect.getPageSize());
            obj.put("SortField", userSelect.getSortField());
            obj.put("SortOrder", userSelect.getSortOrder());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HostList hostList = HostHttp.postHostList(realUrl, obj);
        if (hostList != null) {
            return hostList;
        }
        return null;
    }


    public static UserList selectUserList() {
        UserSelect userSelect = new UserSelect();
        userSelect.setFilterGroup("");
        userSelect.setPageIndex(1);
        userSelect.setPageSize(10000000);
        userSelect.setSortField("UserName");
        userSelect.setSortOrder("ASC");
        JSONObject obj = new JSONObject();
        String realUrl = GsonUtil.urlT + "/Account/GridData";
        try {
            obj.put("FilterGroup", userSelect.getFilterGroup());
            obj.put("PageIndex", userSelect.getPageIndex());
            obj.put("PageSize", userSelect.getPageSize());
            obj.put("SortField", userSelect.getSortField());
            obj.put("SortOrder", userSelect.getSortOrder());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        UserList userList = UserHttp.postUserList(realUrl, obj);
        if (userList != null) {
            return userList;
        }
        return null;
    }



public static boolean iSphone(String s){
    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    Matcher m = p.matcher(s);
    return m.matches();
}
    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();

    }

    public static void showToasts(Context context, String text, int cnt) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        showMyToast(toast, cnt);
    }
    //自定义Toast时间
    public static void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt);
    }
}
