package com.android.alivecor.model

import android.R
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import com.android.alivecor.providers.IBaseManager
import java.util.*

class EditTextDatePicker(context: Context?, iBaseManager: IBaseManager) : OnDateSetListener {
    private var _day = 0
    private var _month = 0
    private var _birthYear = 0
    private val _context: Context?
    private val TAG = EditTextDatePicker::class.java.name
    private val _iBaseManager: IBaseManager;
    private var isShowDialog = false
    override fun onDateSet(
        view: DatePicker,
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int
    ) {
        _birthYear = year
        _month = monthOfYear
        _day = dayOfMonth
        Log.d(TAG, "onDateSet() :: _birthYear "+_birthYear)
        isShowDialog=false
        _iBaseManager.setDateData(_day,_month,_birthYear);

    }

    fun showDialog() {
        val calendar =
            Calendar.getInstance(TimeZone.getDefault())
        val dialog = DatePickerDialog(
            _context!!, R.style.Theme_Holo_Dialog, this,
            calendar[Calendar.YEAR], calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        dialog.show()
        isShowDialog= true;
    }

    fun isShowingDialog() : Boolean{
        return isShowDialog
    }


    init {
        val act = context as Activity?
        _context = context
        _iBaseManager = iBaseManager
    }
}