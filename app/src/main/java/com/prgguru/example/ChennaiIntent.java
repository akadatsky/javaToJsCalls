package com.prgguru.example;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class ChennaiIntent extends Activity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        webView =(WebView)findViewById(R.id.webkit);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://apps.programmerguru.com/examples/chennai.html");
    }

    public void btnGoClick(View view) {
        String msgToSend = "123";
        webView.loadUrl("javascript:testCall(\"" + msgToSend + "\")");
    }

}
