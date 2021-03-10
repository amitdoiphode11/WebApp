package com.example.webapp

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private val JAVASCRIPT_OBJ = "javascript_obj"
        private val BASE_URL = "file:///android_asset/webview.html"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(JavaScriptInterface(), JAVASCRIPT_OBJ)
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (url == BASE_URL) {
                    injectJavaScriptFunction()
                }
            }
        }
        webView.loadUrl(BASE_URL)


    }

    override fun onDestroy() {
        webView.removeJavascriptInterface(JAVASCRIPT_OBJ)
        super.onDestroy()
    }

    private fun injectJavaScriptFunction() {
        webView.loadUrl(
            "javascript: " +
                    "window.androidObj.textToAndroid = function(message) { " +
                    JAVASCRIPT_OBJ + ".textFromWeb(message) }"
        )
    }


    private inner class JavaScriptInterface {
        @JavascriptInterface
        fun textFromWeb(pdf: String) {
            //txt_from_web.text = fromWeb
            //Toast.makeText(baseContext, "$pdf", Toast.LENGTH_LONG).show()
            intent = ViewPdfActivity.getStartIntent(this@MainActivity, pdf)
            startActivity(intent)
        }
    }
}