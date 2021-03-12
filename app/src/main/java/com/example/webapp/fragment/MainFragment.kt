package com.example.webapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.webapp.MainActivity
import com.example.webapp.R
import com.example.webapp.data.DeviceDetails
import com.example.webapp.logger.TimberRemoteTree
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
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
        loadWebView()
        TimberRemoteTree(DeviceDetails()).i( "onActivityCreated: " )
    }

    override fun onResume() {
        super.onResume()
        //loadWebView()
        TimberRemoteTree(DeviceDetails()).i("onResume")
    }
    private fun loadWebView() {
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
            ((activity) as MainActivity).replaceFragment(PfdViewFragment.newInstance(pdf))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }
}