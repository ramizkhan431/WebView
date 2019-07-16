package com.example.infowebment;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    WebView wb;
    FloatingActionButton fab;
    com.github.clans.fab.FloatingActionButton fabPerson,fabAdmin;

    int count=0; //no need this
    //
    public long backPressedTime;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //

        //
        progressBar=(ProgressBar) findViewById(R.id.pb);

        fabPerson=findViewById(R.id.fabPerson);
        fabAdmin=findViewById(R.id.fabAdmin);
        //fab=(FloatingActionButton) findViewById(R.id.floatingActionButton2);

        wb=(WebView)findViewById(R.id.webView1);

        //rotate control cheak
        //start

        if (savedInstanceState == null) {
            wb.post(new Runnable() {
                @Override
                public void run() {
                    wb.loadUrl("https://www.infowebmentsolution.com/");
                }
            });
        }


        //end
        //


        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setBuiltInZoomControls(true);

        wb.getSettings().setDisplayZoomControls(false);
        wb.getSettings().setPluginState(WebSettings.PluginState.ON);


        //wb.loadUrl("https://www.infowebmentsolution.com/");


        wb.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(MainActivity.this, "Page Loading...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                wb.setVisibility(View.VISIBLE);
            }
        });

        fabPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PortalActivity.class);
               startActivity(intent);
            }
        });

        fabAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AdminActivity.class);
                startActivity(intent);
            }
        });


//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,PortalActivity.class);
//                startActivity(intent);
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (wb.canGoBack())
        {
            wb.goBack();
        }

        else if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }else
        {
            Toast.makeText(this, "Press back again", Toast.LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis();
    }

    //rotate control code
    //start

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        wb.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
       wb.restoreState(savedInstanceState);
    }
    //end rotate control
    //


       //    @Override
//    public void onBackPressed() {
//        if (count==0)
//        {
//            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
//            count++;
//        }
//        else {
//            super.onBackPressed();
//        }
//    }
}
