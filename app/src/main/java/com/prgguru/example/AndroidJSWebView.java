package com.prgguru.example;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class AndroidJSWebView extends Activity {

    private Context mContext = this;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        webView = (WebView) findViewById(R.id.webkit);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(), "app");
        webView.loadUrl("file:///android_asset/androidjs.html");
    }

    public void btnGoClick(View view) {
        String msgToSend = "123";
        webView.loadUrl("javascript:testCall(\"" + msgToSend + "\")");
    }

    public class WebAppInterface {

        @SuppressWarnings("unused")
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @SuppressWarnings("unused")
        @JavascriptInterface
        public void showDialog(String dialogMsg) {
            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
            alertDialog.setTitle("JS triggered Dialog");
            alertDialog.setMessage(dialogMsg);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(mContext, "Dialog dismissed!", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.show();
        }

        @SuppressWarnings("unused")
        @JavascriptInterface
        public void moveToNextScreen() {
            Intent chnIntent = new Intent(AndroidJSWebView.this, ChennaiIntent.class);
            startActivity(chnIntent);
        }
    }
}
