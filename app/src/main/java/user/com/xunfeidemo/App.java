package user.com.xunfeidemo;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * 1.类的用途
 * 2.@author:zhaoxinjun
 * 3.@  2016/12/16.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(getApplicationContext(),  SpeechConstant.APPID +"=585331d0" );
    }
}
