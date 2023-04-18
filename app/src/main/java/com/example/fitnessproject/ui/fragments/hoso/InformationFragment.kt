package com.example.fitnessproject.ui.fragments.hoso

import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.fitnessproject.R
import com.example.fitnessproject.ui.BaseFragment
import com.example.fitnessproject.ui.activities.editinformation.EditInformationActivity
import kotlinx.android.synthetic.main.fragment_information.*

class InformationFragment : BaseFragment<InformationViewModel>() {
    override fun getLayoutId() = R.layout.fragment_information
    override fun initScreen() {
        val imgChangeProfile = view?.findViewById<ImageView>(R.id.imgChangeProfile)
        imgChangeProfile?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, EditInformationActivity::class.java)
            startActivity(intent)
        })
    }

    override fun bindData() {
        super.bindData()
        viewModel.userInfoLiveData.observe(this) { user ->
            textView?.text = user.fullName.ifEmpty { "User name" }

            activity?.let {
                user.thumbnail?.let { thumb ->
                    Glide.with(it).load(thumb).into(imgUser)
                }
            }
        }
    }
}