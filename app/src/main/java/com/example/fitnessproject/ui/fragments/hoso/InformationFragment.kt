package com.example.fitnessproject.ui.fragments.hoso

import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.example.fitnessproject.R
import com.example.fitnessproject.ui.BaseFragment
import com.example.fitnessproject.ui.activities.editinformation.EditInformationActivity
import com.squareup.picasso.Picasso

class InformationFragment : BaseFragment<InformationViewModel>() {
    override fun getLayoutId() = R.layout.fragment_information
    override fun initScreen() {
        val imgUser  = view?.findViewById<ImageView>(R.id.imgUser)
        val imgChangeProfile = view?.findViewById<ImageView>(R.id.imgChangeProfile)
        imgChangeProfile?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, EditInformationActivity::class.java)
            startActivity(intent)
        })
    }
}