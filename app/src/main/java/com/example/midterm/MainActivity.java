package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    EditText enterInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn).setOnClickListener((view) -> {

            enterInput = (EditText)findViewById(R.id.enterInput);
            String text = enterInput.getText().toString();

            TextView resultTv = (TextView)findViewById(R.id.resultTv);
            OkHttpClient newClient = new OkHttpClient();
            String url;

            
            url = "https://learn.operatoroverload.com/rental/"+ text;
            Request newRequest = new Request.Builder().url(url).build();






            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Response response = newClient.newCall(newRequest).execute();
                        String text = response.body().string();


                        runOnUiThread(() -> {
                            resultTv.setText("text");
                            ((TextView) findViewById(R.id.resultTv)).setText(text);
                        });
                    } catch (IOException e) {
                        runOnUiThread(() -> {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        });

                    }
                }
            };

            t.start();


        });




    }
}





//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final TextView resultTv;
//
//
//
//        final Button  btn = findViewById(R.id.btn);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                EditText enterInput = findViewById(R.id.enterInput);
//                String text= enterInput.getEditableText().toString();
//
//
//                OkHttpClient client = new OkHttpClient();
//
//
//
//                    final String url = "https://learn.operatoroverload.com/rental/"+text;
//                    Request request = new Request.Builder()
//                            .url(url)
//                            .build();
//                    client.newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//
//                            final TextView resultTv = (TextView) findViewById(R.id.resultTv);
//
//
//                            if (response.isSuccessful()) {
//                                final String myResponse = response.body().string();
//
//                                MainActivity.this.runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        resultTv.setText(myResponse);
//                                    }
//                                });
//                            }
//                        }
//                    });
//                }
//
//
//
//        });
//
//
//    }
//}
