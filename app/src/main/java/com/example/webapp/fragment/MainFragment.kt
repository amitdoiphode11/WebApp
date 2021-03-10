package com.example.webapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.webapp.MainActivity
import com.example.webapp.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        private val JAVASCRIPT_OBJ = "javascript_obj"
        private val BASE_URL = "file:///android_asset/webview.html"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        //webView.removeJavascriptInterface(JAVASCRIPT_OBJ)
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
            Toast.makeText(context, "$pdf", Toast.LENGTH_LONG).show()
            ((activity) as MainActivity).replaceFragment(PfdViewFragment.newInstance(pdf))
        }
    }
}