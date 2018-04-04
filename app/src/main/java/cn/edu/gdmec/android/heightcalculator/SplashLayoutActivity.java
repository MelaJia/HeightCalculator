package cn.edu.gdmec.android.heightcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashLayoutActivity extends Activity {


    private TextView version;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        initView();

     //   pic = (ImageView) findViewById(R.id.pic);
        version = (TextView) findViewById(R.id.version);
        try {
            PackageInfo packageInfo=getPackageManager().getPackageInfo(getPackageName(),0);
            version.setText(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashLayoutActivity.this, HeightCalculatorActivity.class);
                startActivity(intent);
                SplashLayoutActivity.this.finish();
            }
        };
        timer.schedule(timerTask, 3000);
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
    }
}
