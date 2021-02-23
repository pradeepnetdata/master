package com.android.alivecor.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.alivecor.model.Age
import com.android.alivecor.model.AgeCalculator
import com.android.alivecor.model.UserData
import com.android.alivecor.utils.AppUtils
import com.android.alivecor.R
import com.android.alivecor.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*

class UserDetailFragment : BaseFragment(), View.OnClickListener{
    private val TAG = UserDetailFragment::class.java.name
    private lateinit var tv_firstname : TextView
    private lateinit var tv_lastname : TextView
    private lateinit var tv_user_age : TextView
    private lateinit var button_previous: TextView;

    companion object {
        fun newInstance() = UserDetailFragment()
    }
    private lateinit var model: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_userdetail
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_firstname = view.findViewById(R.id.tv_firstname)
        tv_lastname = view.findViewById(R.id.tv_lastname)
        tv_user_age = view.findViewById(R.id.tv_user_age)
        button_previous = view.findViewById(R.id.button_previous)
        button_previous.setOnClickListener(this)
        model = activity?.run {
            ViewModelProvider(this).get(UserViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        model.data.observe(this, Observer<UserData> { item ->
            // Update the UI
            Log.i(TAG, "onCreate ::  start " +item.first_name)
            tv_firstname.setText("First Name:" +item.first_name);
            tv_lastname.setText("Last Name: "+item.last_name);
            tv_user_age.setText("Age: "+ageCalc(item.date_of_birth).toString());
        })
    }
    private fun ageCalc(date: String ) : Age{
        val sdf =  SimpleDateFormat(AppUtils.TIMESTAMP_FORMAT)
        val birthDate: Date  = sdf.parse(date);
        return AgeCalculator.calculateAge(birthDate);
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_previous -> {
                activity?.onBackPressed()
            }
        }
    }
}