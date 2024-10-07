//Пакет
package com.srrok.routeme

//Библиотека
import android.os.Bundle
import android.app.Activity
import android.webkit.WebView
import android.webkit.WebSettings
import androidx.core.view.ViewCompat
import android.view.ViewTreeObserver
import android.annotation.SuppressLint
import androidx.core.view.WindowInsetsCompat
import androidx.constraintlayout.widget.ConstraintLayout

//Базовый экран
class MainActivity : Activity() {
  //При создании экрана
  @SuppressLint("SetJavaScriptEnabled")
  override fun onCreate(savedInstanceState: Bundle?) {
    //Инициализация экрана и родителя
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //Получаем WebView и параметры по ID
    val webView = findViewById<WebView>(R.id.view)
    val webSettings = webView.settings
    //Присваиваем ссылку
    webView.loadUrl(getString(R.string.link))
    //Параметры кэширования страницы
    webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
    //Параметры JavaScript, геолокации и хранилища
    webSettings.javaScriptEnabled = true
    webSettings.domStorageEnabled = true
    webSettings.setGeolocationEnabled(true)
    webSettings.javaScriptCanOpenWindowsAutomatically = true
    webSettings.javaScriptCanOpenWindowsAutomatically = true
    //Отключаем масштабирование
    webSettings.setSupportZoom(false)
    webSettings.builtInZoomControls = false
    webSettings.displayZoomControls = false
    //Параметры связи с Web интерфейсом
    webSettings.userAgentString = "RouteMeClient"
    //Получаем ConstraintLayout по ID
    val mainConstraintLayout = findViewById<ConstraintLayout>(R.id.main)
    //Устанавливаем слушатель для получения отступов
    mainConstraintLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
      //При инициализации экрана
      override fun onGlobalLayout() {
        //Удаляем слушателя после получения отступов
        mainConstraintLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
        //Устанавливаем слушатель для отступов
        ViewCompat.setOnApplyWindowInsetsListener(mainConstraintLayout) { v, insets ->
          val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
          v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
          insets
        }
      }
    })
  }
}