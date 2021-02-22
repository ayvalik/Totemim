package com.smartapps.totemim

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var progressBar: ProgressBar
    lateinit var buttonBarcode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressbar)
        webView = findViewById(R.id.webview)
        buttonBarcode = findViewById(R.id.btn_barcode)
        clickBarcode()
        val urlBarcode = intent.getStringExtra("Url")
        webView.settings.setJavaScriptEnabled(true)
        webView.webViewClient = WebViewClient()

       if (urlBarcode != null)
            webView.loadUrl(urlBarcode)
        else
            webView.loadUrl("https://www.totemim.com/")
    }

    private fun clickBarcode() {
        buttonBarcode.setOnClickListener {

            val intent = Intent(this, BarcodeScanningActivity::class.java)
            startActivity(intent)
        }
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
            webView.visibility = View.VISIBLE
        }
    }
}