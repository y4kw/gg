package com.example.gg;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {

    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.main);
        //setContentView(R.layout.webViewp);

        setContentView(R.layout.activity_main);
        deleteDatabase("webView.db");
        deleteDatabase("webViewCache.db");

        webView = (WebView)findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient() {
            private int running = 0; // Could be public if you want a timer to check.
            //private int running = 0; // Could be public if you want a timer to check.

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String urlNewString) {
                Log.d("sholdOverrideUrlLoading","value: " + running);
                running++;
                Log.d("sholdOverrideUrlLoading","value: " + running);
                webView.loadUrl(urlNewString);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d("onPageStarted","value: " + running);
                running = Math.max(running, 1); // First request move it to 1.
                Log.d("onPageStarted","value: " + running);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("onPageFinished","value: " + running);
                //webView.loadUrl( "javascript:window.location.reload( true )" );
                //Log.d("onPageFinished","javascript reload called");
                if(--running == 0) { // just "running--;" if you add a timer.
                    // TODO: finished... if you want to fire a method.
                    Log.d("onPageFinished","value: " + running);
                    //webView.reload();
                    webView.loadUrl( "javascript:window.location.reload( true )" );
                    Log.d("onPageFinished","javascript reload called");
                }
            }
        });


    //    webView.setWebViewClient(new WebViewClient(){
    //        @Override
    //        public boolean shouldOverrideUrlLoading(WebView view, String url) {
    //            return false;
    //        }
    //    });
        //webView.loadUrl("http://www.google.com/");
        String pdfUrl = "http://www.data.jma.go.jp/fcd/yoho/data/jishin/kaisetsu_tanki_latest.pdf";
        String url = "http://docs.google.com/gview?embedded=true&url=" + pdfUrl;

        //webview.invalidate();
        webView.loadUrl(url);
    }
}
