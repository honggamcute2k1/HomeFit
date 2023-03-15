package com.example.fitnessproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fitnessproject.R
import kotlinx.android.synthetic.main.base_fragment.*

abstract class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.base_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.layoutInflater.inflate(getLayoutId(), baseView, true)
        Glide.with(this).load(R.drawable.loading).into(imgLoading)
        initScreen()
    }

    abstract fun getLayoutId(): Int
    abstract fun initScreen()

}