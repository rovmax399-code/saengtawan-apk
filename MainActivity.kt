package th.co.saengtawan.airservice

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var webView: WebView
    private val homeUrl = "https://saengtawan-air-service.blogspot.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
        }

        val header = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 12, 16, 10)
            setBackgroundColor(Color.rgb(10, 67, 126))
        }
        val title = TextView(this).apply {
            text = "แสงตะวันแอร์"
            textSize = 22f
            setTextColor(Color.WHITE)
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        val subtitle = TextView(this).apply {
            text = "จำหน่าย • ติดตั้ง • ซ่อมบำรุงเครื่องปรับอากาศ"
            textSize = 13f
            setTextColor(Color.WHITE)
        }
        header.addView(title)
        header.addView(subtitle)

        val actions = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(8, 6, 8, 6)
            setBackgroundColor(Color.rgb(239, 246, 255))
        }
        val callButton = Button(this).apply {
            text = "โทรหาร้าน"
            isAllCaps = false
            setOnClickListener { openExternal("tel:0993618518") }
        }
        val lineButton = Button(this).apply {
            text = "ติดต่อ LINE"
            isAllCaps = false
            setOnClickListener {
                openExternal("https://line.me/R/ti/p/%40558turjd")
            }
        }
        actions.addView(callButton, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f))
        actions.addView(lineButton, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f))

        webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.setSupportMultipleWindows(false)
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    val uri = request?.url ?: return false
                    val scheme = uri.scheme ?: ""
                    val host = uri.host ?: ""
                    if (scheme == "http" || scheme == "https") {
                        if (host == "saengtawan-air-service.blogspot.com" ||
                            host.endsWith(".blogspot.com") ||
                            host == "www.blogger.com") {
                            return false
                        }
                    }
                    openExternal(uri.toString())
                    return true
                }
            }
        }

        root.addView(header)
        root.addView(actions)
        root.addView(webView, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
        ))
        setContentView(root)

        if (savedInstanceState == null) {
            webView.loadUrl(homeUrl)
        } else {
            webView.restoreState(savedInstanceState)
        }
    }

    private fun openExternal(value: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(value)))
        } catch (_: Exception) {
            Toast.makeText(this, "ไม่พบแอปที่รองรับลิงก์นี้", Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Android API")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (::webView.isInitialized) webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        if (::webView.isInitialized) {
            webView.stopLoading()
            webView.destroy()
        }
        super.onDestroy()
    }
}
