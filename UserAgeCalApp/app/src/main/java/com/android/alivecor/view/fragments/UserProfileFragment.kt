package com.android.alivecor.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.alivecor.R
import com.android.alivecor.model.UserData
import com.android.alivecor.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputLayout

class UserProfileFragment : BaseFragment(), View.OnClickListener, OnFocusChangeListener{

    interface FragmentListener {
        fun handleOnClick()
    }
    private lateinit var fragmentListener: FragmentListener
    private lateinit var button_next: Button
    private lateinit var model: UserViewModel
    private lateinit var edit_firstname : EditText
    private lateinit var inputFirstName : TextInputLayout
    private lateinit var edit_lastname : EditText
    private lateinit var inputLastName : TextInputLayout
    private lateinit var edit_date : EditText
    private lateinit var inputUserDate : TextInputLayout

    companion object {
        private val TAG = UserProfileFragment::class.java.name
        fun newInstance()= UserProfileFragment()
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_userprofile
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = activity as FragmentListener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProvider(this).get(UserViewModel::class.java)
        } ?: throw Exception("Invalid Fragment")

    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated() :: called")
        edit_firstname = view.findViewById(R.id.edit_firstname)
        inputFirstName = view.findViewById(R.id.inputFirstName)
        edit_lastname = view.findViewById(R.id.edit_lastname)
        inputLastName = view.findViewById(R.id.inputLastName)
        edit_date = view.findViewById(R.id.edit_date)
        inputUserDate = view.findViewById(R.id.inputUserDate)
        button_next = view.findViewById(R.id.button_next)
        edit_date.setOnClickListener(this)
        button_next.setOnClickListener(this)
        edit_date.setOnFocusChangeListener(this);
        subscribeDateLiveData();
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.edit_date -> {
            //Code statements
            hideKeyboard()
            model.getDate(mContext)
            }
            R.id.button_next -> {
                //Code statements
                if(validateUserDetails()) {
                    var first_name : String =edit_firstname.getText().toString();
                    var last_name : String =edit_lastname.getText().toString();
                    var dob : String =edit_date.getText().toString();
                    val user = UserData(first_name, last_name, dob)
                    model.setUserdata(user)
                    fragmentListener.handleOnClick();
                }
            }
        }
    }

    override fun onFocusChange(view: View, p1: Boolean) {
        when (view.id ) {
            R.id.edit_date -> {
                //Code statements
                if(view.isFocused()){
                    hideKeyboard()
                    model.getDate(mContext)
                }
            }
        }
    }
    private fun subscribeDateLiveData(){
        model.observerDataLiveData()
            .observe(this, Observer<String> { date ->
                Log.i(TAG, "subscribeDateLiveData ::  start " +date)
                updateDateInfo(date);
            })
    }
    // updates the date in the birth date EditText
    private fun updateDateInfo(date : String) {
        hideKeyboard()
        edit_date!!.setText(date)
        inputUserDate.setError(null);
        inputUserDate.setErrorEnabled(false);
        edit_date.setHint("Date of birth")
    }

    private fun validateUserDetails():Boolean {
        if (edit_firstname.getText().toString().trim().isEmpty() ||
            !nameValidation(edit_firstname.getText().toString().trim())) {
            inputFirstName.setErrorEnabled(true);
            inputFirstName.setError(getString(R.string.err_msg_firstname));
            requestFocus(inputFirstName);
            return false;
        } else {
            inputFirstName.setError(null);
            inputFirstName.setErrorEnabled(false);
        }

        if (edit_lastname.getText().toString().trim().isEmpty() ||
            !nameValidation(edit_lastname.getText().toString().trim())) {
            inputLastName.setErrorEnabled(true);
            inputLastName.setError(getString(R.string.err_msg_lastname));
            requestFocus(inputLastName);
            return false;
        } else {
            inputLastName.setError(null);
            inputLastName.setErrorEnabled(false);
        }
        if (edit_date.getText().toString().trim().isEmpty()) {

            inputUserDate.setErrorEnabled(true);
            inputUserDate.setError(getString(R.string.err_msg_date));
            edit_date.setHint("Click here to select DOB")
            hideKeyboard()
            return false;
        } else {
            inputUserDate.setError(null);
            inputUserDate.setErrorEnabled(false);
        }


        return true;
    }
    fun nameValidation(name: String): Boolean {
        val regex = Regex("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")
       var valid: Boolean  = name.matches(regex)

        return valid
    }
}