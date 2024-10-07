//Пакет
package com.srrok.routeme

//Библиотека
import android.view.View
import android.os.Bundle
import android.os.Vibrator
import android.app.Activity
import android.webkit.WebView
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.webkit.WebSettings
import android.widget.LinearLayout
import android.view.ViewTreeObserver
import androidx.core.view.ViewCompat
import android.annotation.SuppressLint
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import androidx.core.view.WindowInsetsCompat
import androidx.constraintlayout.widget.ConstraintLayout

//Базовый экран
@Suppress("DEPRECATION")
class MainActivity: Activity() {
  //Компоненты WebView
  private lateinit var webView: WebView
  private lateinit var loader: LinearLayout
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
    loader = findViewById(R.id.loader)
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
    //Устанавливаем WebViewClient для отслеживания загрузки
    webView.webViewClient = object: android.webkit.WebViewClient() {
      //Получаем контекст
      val context = this@MainActivity
      //При начале загрузки страницы
      override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        //Скрываем WebView, отображая загрузчик
        loader.visibility = View.VISIBLE
        webView.visibility = View.GONE
      }
      //При загрузке страницы
      override fun onPageFinished(view: WebView?, url: String?) {
        //Скрываем экран после окончания загрузки WebView
        loader.visibility = View.GONE
        webView.visibility = View.VISIBLE
      }
      //При ошибке загрузки
      override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        //Если есть ошибка
        if (error != null) {
          //Если есть контекст ошибки
          if (error.errorCode != -2) {
            //Обработчик диалога
            val builder = AlertDialog.Builder(context)
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
              //Создаем диалог с ошибкой
              builder.setTitle("Ошибка загрузки страницы: ${error.errorCode}")
              builder.setMessage(error.description ?: "Время ожидания истекло!")
              builder.setNegativeButton("Выйти") { _, _ -> finish() }
              builder.show()
            }
          }
        }
      }
    }
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