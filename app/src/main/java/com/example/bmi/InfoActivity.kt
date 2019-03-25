package com.example.bmi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.example.bmi.logic.BmiAnalyser
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val bmi = intent?.extras?.getDouble("bmi")
        if(bmi !== null) {
            info_TV.text = String.format("%.2f", bmi)
            info_TV.setTextColor(BmiAnalyser.getColorOfTextForGivenBMI(bmi))
            description_TV.text = resources.getString(BmiAnalyser.getDescriptionForGivenBMI(bmi))
            info_image_IV.setImageDrawable(ContextCompat.getDrawable(this, BmiAnalyser.getImageIdForGivenBMI(bmi)))
        }
    }
}
