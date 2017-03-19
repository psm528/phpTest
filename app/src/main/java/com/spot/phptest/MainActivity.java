package com.spot.phptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static TextView tv;
    public static PHPManager pm;
    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtn(View view) {
        tv = (TextView) findViewById(R.id.testtext);
        pm = new PHPManager();

        new Thread(new Runnable() {
            @Override
            public void run() {
                text = pm.getDataFromServer();
                Log.v("thread","진입");
                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("post","진입");
                        tv.setText(text);
                        Log.v("post","완료");
                    }
                });
            }
        }).start();
    }
}
