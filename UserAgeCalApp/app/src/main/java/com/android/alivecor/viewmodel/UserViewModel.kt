package com.android.alivecor.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.alivecor.model.EditTextDatePicker
import com.android.alivecor.model.UserData
import com.android.alivecor.providers.IBaseManager


class UserViewModel : ViewModel() , IBaseManager {
    private val TAG = EditTextDatePicker::class.java.name
    var dateLiveData = MutableLiveData<String>()
    var data = MutableLiveData<UserData>()
    lateinit var datePicker: EditTextDatePicker;

    fun getDate(context: Context) {
        datePicker = EditTextDatePicker(context,this);
        if(!datePicker.isShowingDialog()) {
            datePicker.showDialog()
        }

    }
    fun isShowingDateDialog() : Boolean {
        return datePicker.isShowingDialog()
    }

    override fun setDateData(day: Int, month: Int, birthYear: Int) {

        var date : StringBuilder =  StringBuilder()
            // Month is 0 based so add 1
            .append(day).append("/").append(month + 1).append("/").append(birthYear).append(" ")
        dateLiveData.postValue(date.toString())
        Log.d(TAG, "setDateData() :: date=$date")
    }

    fun observerDataLiveData(): LiveData<String> {
        return dateLiveData
    }

    fun setUserdata(user: UserData) {
        data.value = user;
    }
}