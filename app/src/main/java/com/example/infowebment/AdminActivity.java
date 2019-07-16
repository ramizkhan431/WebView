package com.example.infowebment;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    WebView wbsr;
    ProgressBar progressr;
    FloatingActionButton fabd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        progressr = (ProgressBar) findViewById(R.id.pbars);
        fabd=(FloatingActionButton) findViewById(R.id.floatingActionButton);


        wbsr = (WebView) findViewById(R.id.webs);
        WebSettings webSettings = wbsr.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        if (savedInstanceState == null) {
            wbsr.post(new Runnable() {
                @Override
                public void run() {
                    wbsr.loadUrl("http://portal.infowebmentsolution.com/admin");
                }
            });
        }

        fabd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //wbsr.loadUrl("http://portal.infowebmentsolution.com/admin");

        wbsr.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(AdminActivity.this, "Page Loading...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressr.setVisibility(View.GONE);
                wbsr.setVisibility(View.VISIBLE);
            }
        });
    }
    // rotate controll on webview
    //start

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        wbsr.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        wbsr.restoreState(savedInstanceState);
    }
    //end
    //
}