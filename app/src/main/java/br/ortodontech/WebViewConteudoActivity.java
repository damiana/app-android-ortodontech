package br.ortodontech;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewConteudoActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://denan.com.br/ortodontech/");
    }
}
