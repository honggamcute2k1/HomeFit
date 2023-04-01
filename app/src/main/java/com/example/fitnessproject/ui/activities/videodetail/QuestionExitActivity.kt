package com.example.fitnessproject.ui.activities.videodetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessproject.R
import com.example.fitnessproject.ui.activities.main.MainActivity

class QuestionExitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_exit)
        var ivback: ImageView = findViewById(R.id.imgBack)
        var btnExit: Button = findViewById(R.id.mbtnExit)
        var btnSelectOption1: Button = findViewById(R.id.btnSelectOption1)
        var btnSelectOption2: Button = findViewById(R.id.btnSelectOption2)
        var btnSelectOption3: Button = findViewById(R.id.btnSelectOption3)
        var btnSelectOption4: Button = findViewById(R.id.btnSelectOption4)
        ivback.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        btnExit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        btnSelectOption1.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        btnSelectOption2.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        btnSelectOption3.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        btnSelectOption4.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }

}
