package uottawa.ca.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * The class AboutActivity is the activity that
 * displays some info about the app from an HTML file.
 * <p>
 * It extends AppCompatActivity.
 */
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
