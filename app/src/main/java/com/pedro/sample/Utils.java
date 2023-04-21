package com.pedro.sample;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getRTMPServer(String flag) {
        String imei = "362523432446865";
        String playUrl = "rtmp://push.xxxx.com:9002/live/" + imei + "?key=";   //rtmp播放url
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        String before = "bbkj_" + flag + "_0324#live#" + imei + "#" + date;
        String md5 = md5(before);
        //String result = playUrl + md5;
        //MagicLog.d("before:" + before);
        //MagicLog.d("md5:" + md5);
        //MagicLog.d("result:" + result);
        return playUrl + md5;
    }

    public static String getWebRtcUrl() {
        String imei = "362523432446865";
        //rtmp://push.xxxx.com:9002/live/362523432446865?key=0ce0b74f480577b7e8b14f0b8aa96a43
        String playUrl = "rtmp://push.xxxx.com:9002/live/" + imei + "?key=";   //rtmp播放url
//        String playUrl = "webrtc://push.xxxx.com/live/" + imei + "?key=";     //webRtc
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        String before = "bbkj_play" + "_0324#live#" + imei + "#" + date;
        String md5 = md5(before);
        return playUrl + md5;
    }

    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
