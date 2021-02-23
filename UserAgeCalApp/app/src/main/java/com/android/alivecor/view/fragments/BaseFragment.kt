package com.android.alivecor.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.android.alivecor.view.BaseActivity

abstract class BaseFragment : Fragment() {
    var mActivity: BaseActivity? = null
    private var mRootView: View? = null
    abstract fun getLayoutId(): Int
    lateinit var mContext: Context;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext= context
        if (context is BaseActivity) {
            val activity: BaseActivity = context as BaseActivity
            mActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayoutId(), container, false)
        return mRootView
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    val baseActivity: BaseActivity?
        get() = mActivity

    fun hideKeyboard() {
        baseActivity?.hideKeyboard()
    }
    fun requestFocus(view: View) {
        if (view.requestFocus()) {
            mActivity?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}