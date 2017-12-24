package com.example.sambovisal.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements WebList.Link {
    WebView website;
    TextView tv;


    @Override
    public void setLink(String li) {
        setView(li);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textweb);
        website = (WebView)findViewById(R.id.webSite);
        website.loadUrl("http://www.facebook.com");
        website.getSettings().setJavaScriptEnabled(true);
        website.setWebViewClient(new OpenWeb());
        tv.setText("Welcome to facebook");
    }


    public void setView(String a)
    {
        OpenWeb ab = new OpenWeb();
        tv = (TextView) findViewById(R.id.textweb);
        website = (WebView)findViewById(R.id.webSite);
        website.loadUrl(a);
        website.getSettings().setJavaScriptEnabled(true);
        website.setWebViewClient(ab);
        tv.setText(a);
    }

    private class OpenWeb extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }



}
