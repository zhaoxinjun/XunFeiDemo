package user.com.xunfeidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SpeechRecognizer mIat;
    private TextView tv;
    private Gson mGson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.listen).setOnClickListener(this);//有UI界面听写
        findViewById(R.id.listen_no_ui).setOnClickListener(this);//无UI界面听写
        findViewById(R.id.read).setOnClickListener(this);//读出
        tv = (TextView) findViewById(R.id.tv);//展示听写内容
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.listen://有UI界面听写
                tv.setText("");
                listen();
            break;
            case R.id.listen_no_ui://无UI界面听写
                tv.setText("");
                listenNoUi();
                break;
            case R.id.read://读出
                read();
                break;
            default:
                break;
        }
    }

    private void listenNoUi() {
        mIat = SpeechRecognizer.createRecognizer(this, null);
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//语言

        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");//说话人物
        // 开始听写
        mIat.startListening(mRecoListener);

    }


    //语音输入 UI 控件
    private void listen() {
        RecognizerDialog mDialog = new RecognizerDialog(this,  null);
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT,  "mandarin");
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
               // Log.e("main",recognizerResult.getResultString());
               // Log.e("main","isLast=" + b);
                Data data=mGson.fromJson(recognizerResult.getResultString(),Data.class);
                StringBuffer buffer=new StringBuffer();
                for(int i=0;i<data.ws.size();i++){
                    Data.WsBean bean = data.ws.get(i);
                    buffer.append(bean.cw.get(0).w);
                }
                tv.setText(tv.getText()+buffer.toString());
               // Log.e("main",tv.getText().toString());
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        mDialog.show();

    }





    private void read() {
        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer(this, null);
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaorong"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED,  "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME,  "80");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm" );
        mTts.startSpeaking( "Study hard and make progress every day", mSynListener);
    }

    private SynthesizerListener mSynListener = new SynthesizerListener(){
        public void onCompleted(SpeechError error) {}
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {}
        public void onSpeakBegin() {}
        public void onSpeakPaused() {}
        public void onSpeakProgress(int percent, int beginPos, int endPos) {}
        public void onSpeakResumed() {}
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {}
    };



    private RecognizerListener mRecoListener = new RecognizerListener() {

        /**
         * 语音识别结果 isLast=true表示会话结束
         */
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.e("main",results.getResultString());
            Log.e("main","isLast=" + isLast);

            Data data=mGson.fromJson(results.getResultString(),Data.class);
            StringBuffer buffer=new StringBuffer();
            for(int i=0;i<data.ws.size();i++){
                Data.WsBean bean = data.ws.get(i);
                buffer.append(bean.cw.get(0).w);
            }
            tv.setText(tv.getText()+buffer.toString());
            Log.e("main",tv.getText().toString());
        }

        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        public void onBeginOfSpeech() {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(SpeechError arg0) {

        }

        @Override
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {

        }

    };
}
