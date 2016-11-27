package uottawa.ca.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class InstructionsActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/instructions.html");
    }
}
