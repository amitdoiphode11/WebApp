package com.example.webapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_view_pdf.*

class ViewPdfActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context?, path: String?): Intent {
            return Intent(context, ViewPdfActivity::class.java).apply {
                putExtra("PATH", path)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf)
        val path = intent.getStringExtra("PATH")
        // Toast.makeText(baseContext, "$path", Toast.LENGTH_LONG).show()
        webView1.settings.javaScriptEnabled = true
        webView1.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$path")

    }

}