package com.example.fitnessproject.ui.activities.editinformation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.fitnessproject.R
import com.example.fitnessproject.data.local.entity.User
import com.example.fitnessproject.domain.model.Gender
import com.example.fitnessproject.ui.BaseActivity
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_edit_information.*

class EditInformationActivity : BaseActivity<EditInformationViewModel>() {
    override fun getLayoutId() = R.layout.activity_edit_information
    val CAMERA_REQUEST = 100
    val STORAGE_REQUEST = 101
    var cameraPermission: Array<String>? = null
    var storagePermission: Array<String>? = null
    var imgSelfie: ImageView? = null
    var imgBack: ImageView? = null
    var resultUri: Uri? = null
    var txtAddImageView: TextView? = null

    override fun initScreen() {
        imgSelfie = findViewById<ImageView>(R.id.imgSelfie)
        imgBack = findViewById(R.id.imgBack)
        txtAddImageView = findViewById(R.id.txtAddImageview)
        imgBack?.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        btnSave?.setOnClickListener {
            viewModel.user?.fullName = editTextFullName?.text?.toString() ?: ""
            viewModel.user?.born = edtBorn?.text?.toString()?.toInt() ?: 1980
            viewModel.user?.phoneNumber = edtPhone?.text?.toString() ?: ""
            viewModel.user?.let { it1 -> viewModel.updateUser(it1) }
        }

        cameraPermission = arrayOf<String>(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        storagePermission = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        txtAddImageView?.setOnClickListener(View.OnClickListener {
            val picd = 0
            if (picd == 0) {
                if (!checkCameraPermission()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestCameraPermission()
                    }
                } else {
                    pickFromGallery()
                }
            } else if (picd == 1) {
                if (!checkStoragePermission()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestStoragePermission()
                    }
                } else {
                    pickFromGallery()
                }
            }
        })
    }

    override fun bindData() {
        super.bindData()
        viewModel.userInfoLiveData.observe(this) { user ->
            viewModel.user = user
            setupData(user)
        }
    }

    private fun setupData(user: User) {
        editTextFullName?.setText(user.fullName)
        edtPhone?.setText(user.phoneNumber)
        edtBorn?.setText(user.born.toString())
        editTextGender?.setText(Gender.valueOf(user.gender)?.name ?: "")
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun requestStoragePermission() {
        requestPermissions(storagePermission!!, STORAGE_REQUEST)
    }

    private fun checkStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun pickFromGallery() {
        CropImage.activity().start(this)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun requestCameraPermission() {
        requestPermissions(cameraPermission!!, CAMERA_REQUEST)
    }

    private fun checkCameraPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        val result1 = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        return result && result1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                resultUri = result.uri
                Picasso.with(this).load(resultUri).into(imgSelfie)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST -> {
                if (grantResults.size > 0) {
                    val camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storage_accepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if (camera_accepted && storage_accepted) {
                        pickFromGallery()
                    } else {
                        Toast.makeText(
                            this,
                            "Please enable camera and storage permission",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            STORAGE_REQUEST -> {
                if (grantResults.size > 0) {
                    val storage_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (storage_accepted) {
                        pickFromGallery()
                    } else {
                        Toast.makeText(this, "Please enable storage permission", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}