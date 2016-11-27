package uottawa.ca.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * The class InstructionsActivity is the activity
 * that displays the app's instructions from an HTML file.
 * <p>
 * It extends AppCompatActivity.
 */
public class InstructionsActivity extends AppCompatActivity {
    WebView webView;    // WebView for the instructions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        webView = (WebView) findViewById(R.id.instructionsWebView);
        webView.loadUrl("file:///android_asset/instructions.html");
    }
}
