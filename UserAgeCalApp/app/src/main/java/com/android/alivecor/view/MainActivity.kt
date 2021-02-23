package com.android.alivecor.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.android.alivecor.R
import com.android.alivecor.view.fragments.UserDetailFragment
import com.android.alivecor.view.fragments.UserProfileFragment

class MainActivity() : BaseActivity(), UserProfileFragment.FragmentListener {

    private val TAG = MainActivity::class.simpleName;
    lateinit var context: Context
    override fun getLayoutId(): Int {
       return  R.layout.activity_main;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this@MainActivity

        var mainFragment: UserProfileFragment? = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as UserProfileFragment?
        if (mainFragment == null) {
            mainFragment = UserProfileFragment.newInstance()
            addFragment(mainFragment)
        }
    }
    private fun addFragment(fragment: Fragment) {
        val transaction =
            supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        val count = supportFragmentManager.backStackEntryCount
        Log.i(TAG, "onBackPressed() :: count>>" +count)
    }

    override fun handleOnClick() {
        Log.i(TAG, "handleOnClick() :: called" )
        var userFragment: UserDetailFragment? = supportFragmentManager.findFragmentById(R.id.fragment_container) as? UserDetailFragment
        if (userFragment == null) {
            userFragment = UserDetailFragment.newInstance()
            addFragment(userFragment)
        }
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
