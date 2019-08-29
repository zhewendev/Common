package com.baiheng.common.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baiheng.common.R;
import com.baiheng.common.network.callback.Callback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class NetworkDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private String mBaseUrl = "http://192.168.31.242:8888/okHttpServer/";
    private Map<String, String> mRequest;
    private Button mGetBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_demo);
        mRequest = new HashMap<>();
        mRequest.put("get","get the value");
        mGetBtn = (Button) findViewById(R.id.get_okhttp);
        mGetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.get_okhttp:
                sendGetHttp();
                break;
            default:
                break;
        }
    }

    private void sendGetHttp() {
        OkHttpUtils.get().params(mRequest).url(mBaseUrl).tag("NetworkActivity").build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("Okhttp","onError");
            }

            @Override
            public void onResponse(Object response, int id) {
                Log.e("okhttp","onResponse");
            }
        });
    }
}
