package uottawa.ca.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutActivity extends AppCompatActivity {
    WebView webView;    // WebView for the about page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        webView = (WebView) findViewById(R.id.aboutWebView);
        webView.loadUrl("file:///android_asset/about.html");
    }
}
