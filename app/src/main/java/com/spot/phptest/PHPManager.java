package com.spot.phptest;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by soomi on 2017-03-13.
 */

public class PHPManager {

    public String getDataFromServer() {
        try {
            URL getUrl = new URL("http://35.162.172.246/dbtest.php");
            HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;

            while ((str = reader.readLine()) != null) {
                builder.append(str + "\n");
            }

            String resultText = builder.toString();

            return resultText;
        } catch (MalformedURLException e) {
            return e.getMessage() + 'a';

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public void setDataToServer(String userNum, int drivingDate, int drivingNum, int drivingTime,
                                int drivingDistance, int hardAccNum, int hardStopNum, int quickStartNum,
                                int echoDriving, int recklessDriving, int dozeDriving) {
        try {
            URL getUrl = new URL("http://35.162.172.246/setdata.php");
            HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            StringBuffer buffer = new StringBuffer();
            buffer.append("UserNum").append("=").append(userNum).append("&");                 // php 변수에 값 대입
            buffer.append("DrivingDate").append("=").append(drivingDate).append("&");   // php 변수 앞에 '$' 붙이지 않는다
            buffer.append("DrivingNum").append("=").append(drivingNum).append("&");           // 변수 구분은 '&' 사용
            buffer.append("DrivingTime").append("=").append(drivingTime);
            buffer.append("DrivingDistance").append("=").append(drivingDistance);
            buffer.append("HardAccNum").append("=").append(hardAccNum);
            buffer.append("HardStopNum").append("=").append(hardStopNum);
            buffer.append("QuickStartNum").append("=").append(quickStartNum);
            buffer.append("EchoDriving").append("=").append(echoDriving);
            buffer.append("RecklessDriving").append("=").append(recklessDriving);
            buffer.append("DozeDriving").append("=").append(dozeDriving);

            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }
}
