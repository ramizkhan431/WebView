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

public class PortalActivity extends AppCompatActivity {
    WebView wbs;
    ProgressBar progress;
    FloatingActionButton fabss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);
        progress=(ProgressBar) findViewById(R.id.pbar);
        fabss=(FloatingActionButton) findViewById(R.id.fabs);


        wbs=(WebView)findViewById(R.id.web);
        WebSettings webSettings = wbs.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        //rotate control first code
        //start

        if (savedInstanceState == null) {
            wbs.post(new Runnable() {
                @Override
                public void run() {
                    wbs.loadUrl("https://portal.infowebmentsolution.com/");
                }
            });
        }
        //end rotate control first code
        //



        //wbs.loadUrl("https://portal.infowebmentsolution.com/");


        wbs.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(PortalActivity.this, "Page Loading...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progress.setVisibility(View.GONE);
                wbs.setVisibility(View.VISIBLE);
            }
        });


        fabss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PortalActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }




    //rotate controll on webview
    //start
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        wbs.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        wbs.restoreState(savedInstanceState);
    }
    //end
}
