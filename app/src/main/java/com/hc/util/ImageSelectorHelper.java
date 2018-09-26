package com.hc.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by uctimes on 2016/10/25.
 */
public class ImageSelectorHelper  {

    private Activity mActivity;

    public ImageSelectorHelper(Activity mActivity, ImageSelectorCallBack imageSelectorCallBack) {
        this.mActivity = mActivity;
        this.imageSelectorCallBack = imageSelectorCallBack;
    }

    private ImageSelectorCallBack imageSelectorCallBack;
    //相册请求码
    private static final int REQUEST_CODE_ALBUM = 888;
    //相机请求码
    private static final int REQUEST_CODE_CAMERA = 889;
    //剪裁请求码
    private static final int REQUEST_CODE_CROP = 890;

    private static File tempFile;

    public  void setImageSelectorCallBack(ImageSelectorCallBack imageSelectorCallBack) {
        this.imageSelectorCallBack = imageSelectorCallBack;
    }

    /**
     * 从相册获取图片
     */
    public void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        mActivity.startActivityForResult(photoPickerIntent, REQUEST_CODE_ALBUM);
    }

    /**
     * 从相机获取图片
     */
    public void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        //跳转到调用系统相机
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName(), tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        mActivity.startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    /**
     * 裁剪图片
     */
    public void cropPhoto(Activity activity,Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, REQUEST_CODE_CROP);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        switch (requestCode) {
            // 调用相机后返回
            case REQUEST_CODE_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName(), tempFile);
                        cropPhoto(mActivity,contentUri);//裁剪图片
                    } else {
                        cropPhoto(mActivity,Uri.fromFile(tempFile));//裁剪图片
                    }
                }
                break;
            //调用相册后返回
            case REQUEST_CODE_ALBUM:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(mActivity,uri);//裁剪图片
                }
                break;
            //调用剪裁后返回
            case REQUEST_CODE_CROP:
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("userHeader", image);
                    File file = new File(path);
                    if(imageSelectorCallBack!=null){
                        imageSelectorCallBack.onSelected(image,file);
                    }
                }
                break;
        }
    }



    /**
     * 保存图片到本地
     *
     * @param name
     * @param bmp
     * @return
     */
    public static String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static interface ImageSelectorCallBack{
        void onSelected(Bitmap imgBitmap,File imgFile);
    }


}