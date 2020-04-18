package com.baiheng.common;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    private Button mNetworkBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetworkBtn = findViewById(R.id.network_button);
        mNetworkBtn.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view) {
        
        int id = view.getId();
        Intent intent = new Intent();
        switch (id) {
        case R.id.network_button:
            ComponentName componentName = new ComponentName("com.baiheng.common",
                    "com.baiheng.common.network.NetworkDemoActivity");
            intent.setComponent(componentName);
            startActivity(intent);
            break;
        default:
            break;
        }
    }
}
