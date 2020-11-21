package com.example.agetominutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {view ->
            openDatePicker(view)
        }
    }

    fun openDatePicker(view: View){

        val myCalendar = Calendar.getInstance()                                                     // getting the current dates
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"           //selected date in string
                selectedDateView.setText(selectedDate)                                              //set the text view to that string

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)                    //converting the string in simple Date format

                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000                                  //getting date in minutes from 1970 till the selected date
                //val selectedDateInDays = theDate!!.time / 60000 / 1440

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))                 //formatting current date
                val currentDateInMinutes = currentDate!!.time / 60000                               // in minutes
                //val currentDateInDays = currentDate!!.time / 60000 / 1440

                val dateInMinutes = currentDateInMinutes - selectedDateInMinutes
                val dateInDays = dateInMinutes / 60 / 24

                dateInDaysView.text = dateInDays.toString()
                dateInMinutesView.text = dateInMinutes.toString()
                textView4.visibility = View.VISIBLE
                textView5.visibility = View.VISIBLE
                textView6.visibility = View.VISIBLE

            }, year, month, day)

        dpd.datePicker.maxDate = Date().time - 86400000                                             //limiting selection of date to be in past not future
        dpd.show()
    }
}