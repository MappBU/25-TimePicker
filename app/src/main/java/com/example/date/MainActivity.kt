package com.example.date

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.date.databinding.ActivityMainBinding

// Интерфейс для Дата пикера
class MainActivity : AppCompatActivity(),View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private var binding: ActivityMainBinding? = null
    // Создаем объект Тайм пикера
    var timePickerDialog: TimePickerDialog? =null
    // Получаем с ТАйм пикера 2 переменные (int)
    private var hours = 0
    private var minutes = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.selectTime?.setOnClickListener(this)
    }

    // При нажатии на кнопку запускаем метод startTimePicker
    override fun onClick(view: View) {

        when(view.id) {
            R.id.selectTime -> {
                startTimePicker()
            }

        }
    }

    private fun startTimePicker () {

        // Инициализируем дата пикер и передаем туда 3 переменные для записи в них
        timePickerDialog = TimePickerDialog(this, this, hours, minutes,true)

        // Этот метод держим выключенным(некорректно раньше работал) Отключал закрытие пикера
        timePickerDialog?.setCanceledOnTouchOutside(false)

        // Создаем кнопки подтверждения и отказа
        // Здесь же можно задать стили для ПИКЕРА
        timePickerDialog?.setOnShowListener(DialogInterface.OnShowListener {
            timePickerDialog?.getButton(Dialog.BUTTON_NEGATIVE)?.setTextColor(Color.WHITE)
            timePickerDialog?.getButton(Dialog.BUTTON_POSITIVE)?.setTextColor(Color.WHITE)
        })

        // Запускает Тайм пикер
        timePickerDialog?.show()

    }

    // Получаем время выбранное на часах и записываем в текст вью
    @SuppressLint("SetTextI18n")
    override fun onTimeSet(timePicker: TimePicker?, hours: Int, minutes: Int) {
        binding?.selected?.text = "$hours:$minutes"
    }
}