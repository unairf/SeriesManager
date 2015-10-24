package com.unairf.seriesmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EmptyActivity extends AppCompatActivity {
    private WebView myWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//El modo immersivo permite disfrutar dle 100% de la pantalla sin la barra de navegación ni la
// de notificaciones mientras vemos el vídeo
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(flags);
        setContentView(R.layout.activity_empty);

        loadUrl();
    }
    private void loadUrl() {
        myWebView = (WebView)findViewById(R.id.webView2);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        //    myWebView.getSettings().setUserAgentString("AndroidWebView");
        myWebView.getSettings().setUserAgentString("Desktop");
        Log.d("DEBUG", getIntent().getStringExtra("url"));
        myWebView.loadUrl(getIntent().getStringExtra("url"));
        myWebView.bringToFront();
}
    //El vídeo se para al salir de la aplicación y guardará su estado
    protected void onPause() {
        myWebView.onPause();
        super.onPause();
    }

    //El vídeo se mantiene en el momento en el que se encontraba antes de pausarse para no perder el
    //progreso
    protected void onResume() {
        super.onResume();
        myWebView.onResume();
    }
}
