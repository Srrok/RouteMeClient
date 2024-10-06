//Пакет
package com.srrok.routeme

//Библиотека
import android.os.Bundle
import androidx.core.view.ViewCompat
import android.view.ViewTreeObserver
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

//Базовый экран
class MainActivity : AppCompatActivity() {
  //При создании
  override fun onCreate(savedInstanceState: Bundle?) {
    //Инициализация экрана и родителя
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
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