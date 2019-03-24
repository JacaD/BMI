package com.example.bmi

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.example.bmi.logic.BmiAnalyser
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.activity_main.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val bmi = intent?.extras?.getString("bmi")
        if(bmi !== null) {
            info_TV.text = bmi.toString().format("%.2f")
            info_TV.setTextColor(BmiAnalyser.getColorOfTextForGivenBMI(bmi.toDouble()))
            description_TV.text = BmiAnalyser.getDescriptionForGivenBMI(bmi.toDouble())
            info_image_IV.setImageDrawable(ContextCompat.getDrawable(this, BmiAnalyser.getImageIdForGivenBMI(bmi.toDouble())))
        }
    }
}
