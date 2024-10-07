//Пакет
package com.srrok.routeme

//Библиотека
import android.os.Bundle
import android.os.Vibrator
import android.app.Activity
import android.webkit.WebView
import android.app.AlertDialog
import android.content.Context
import android.webkit.WebSettings
import android.view.ViewTreeObserver
import androidx.core.view.ViewCompat
import android.annotation.SuppressLint
import androidx.core.view.WindowInsetsCompat
import androidx.constraintlayout.widget.ConstraintLayout

//Базовый экран
@Suppress("DEPRECATION")
class MainActivity: Activity() {
  //Компоненты WebView
  private lateinit var webView: WebView
  private lateinit var webSettings: WebSettings

  //Сервис вибратора
  private lateinit var vibrator: Vibrator
  private var canVibrate: Boolean = false

  //При создании
  @SuppressLint("SetJavaScriptEnabled")
  override fun onCreate(savedInstanceState: Bundle?) {
    //Инициализация экрана и родителя
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //Инициализация вибратора
    vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    canVibrate = vibrator.hasVibrator()
    //Получаем WebView и параметры по ID
    webView = findViewById(R.id.view)
    webSettings = webView.settings
    //Присваиваем ссылку
    webView.loadUrl(getString(R.string.link))
    //Параметры кэширования страницы
    webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
    //Параметры JavaScript, геолокации и хранилища
    webSettings.javaScriptEnabled = true
    webSettings.domStorageEnabled = true
    webSettings.setGeolocationEnabled(true)
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
    mainConstraintLayout.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
      //При инициализации экрана
      override fun onGlobalLayout() {
        //Удаляем слушателя после получения отступов
        mainConstraintLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
        //Устанавливаем слушатель для отступов
        ViewCompat.setOnApplyWindowInsetsListener(mainConstraintLayout) { v, insets ->
          val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
          // Установка отступов:
          v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
          insets
        }
      }
    })
  }

  //Функция обратного перехода
  @Deprecated("Deprecated in Java")
  override fun onBackPressed() {
    //Обработчик диалога
    val builder = AlertDialog.Builder(this)
    //Если есть возможность вернуться
    if (webView.canGoBack()) {
      //Возвращаемся
      webView.goBack()
    } else {
      //Если может вибрировать
      if (canVibrate) {
        //Вибрируем
        vibrator.vibrate(100L)
      }
      //Создаем диалог
      builder.setTitle("Предупреждение")
      builder.setMessage("Хотите выйти?")
      builder.setPositiveButton("Остаться") { _, _ -> }
      builder.setNegativeButton("Выйти") { _, _ -> finish() }
      builder.show()
    }
  }
}