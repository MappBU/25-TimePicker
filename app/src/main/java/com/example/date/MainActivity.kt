package com.example.date

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.date.databinding.ActivityMainBinding

// Интерфейс для Дата пикера
class MainActivity : AppCompatActivity(),View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private var binding: ActivityMainBinding? = null
    // Создаем объект Дата пикера
    var datePickerDialog:DatePickerDialog? =null
    // Получаем с календаря 3 переменные (int)
    private var day = 0
    private var month = 0
    private var year = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.selectDate?.setOnClickListener(this)
    }

    // При нажатии на кнопку запускаем метод tartDataPicker
    override fun onClick(view: View) {

        when(view.id) {
            R.id.selectDate -> {
                startDataPicker()
            }

        }
    }

    private fun startDataPicker () {

        // Инициализируем дата пикер и передаем туда 3 переменные для записи в них
        datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog?.datePicker?.minDate = System.currentTimeMillis() - 1000
        datePickerDialog?.setCanceledOnTouchOutside(false)

        // Создаем кнопки подтверждения и отказа
        datePickerDialog?.setOnShowListener(DialogInterface.OnShowListener {
            datePickerDialog?.getButton(Dialog.BUTTON_NEGATIVE)?.setTextColor(Color.WHITE)
            datePickerDialog?.getButton(Dialog.BUTTON_POSITIVE)?.setTextColor(Color.WHITE)
        })

        // Запускает Дата пикер
        datePickerDialog?.show()

    }

    // В этом методе к месяцу добавляем 1, ибо с массива месяца берутся начиная с нуля
    @SuppressLint("SetTextI18n")
    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val saveMonth=month + 1
        // При нажатии ПОдтвердить - записываем в текст вью ...
        binding?.selected?.text = "$dayOfMonth.$saveMonth.$year"
    }
}