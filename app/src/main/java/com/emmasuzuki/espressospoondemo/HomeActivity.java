package com.emmasuzuki.espressospoondemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends Activity {

    TextView txtString;

    public String url= "https://reqres.in/api/users/2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        txtString = (TextView)findViewById(R.id.welcomeMsg);

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {

//        OkHttpClient client = new NetworkModule().provideOkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                call.cancel();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                final String myResponse = response.body().string();
//
//                HomeActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        txtString.setText(myResponse);
//                    }
//                });
//
//            }
//        });
    }
}
