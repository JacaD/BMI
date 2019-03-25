package com.example.bmi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.bmi.logic.BmiAnalyser
import com.example.bmi.logic.BmiForKgCm
import com.example.bmi.logic.BmiForLbsIn
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var imperialSelected: Boolean = false
    var bmi: Double = -1.0

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val aboutItem: MenuItem? = menu?.findItem(R.id.about)
        val aboutIntent = Intent(this, AboutActivity::class.java)
        aboutItem?.intent = aboutIntent
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.changeUnits -> {
                if(imperialSelected){
                    imperialSelected = false
                    item.title = getString(R.string.bmi_menu_change_units_to_lbs_ft)
                    textView6.text = getString(R.string.bmi_main_weight_kg)
                    textView.text = getString(R.string.bmi_main_height_cm)
                }
                else{
                    imperialSelected = true
                    item.title = getString(R.string.bmi_menu_change_units_to_kg_cm)
                    textView6.text = getString(R.string.bmi_main_weight_lbs)
                    textView.text = getString(R.string.bmi_main_height_ft)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun calculateBMI(v: View){
        val height = height_ET.text.toString().toInt()
        val weight = weight_ET.text.toString().toInt()
        try {
            bmi = when(imperialSelected){
                true -> BmiForLbsIn(weight, height).countBMI()
                false -> BmiForKgCm(weight, height).countBMI()
            }
            displayBMI()
        }
        catch(e: Exception){
            Toast.makeText(applicationContext, "Wrong Data", Toast.LENGTH_LONG).show()
        }
    }

    fun showInfo(v: View){
        if(bmi <= 0.0) return
        startActivity(Intent(this, InfoActivity::class.java).putExtra("bmi", bmi))
    }

    fun displayBMI(){
        val bmiStr: String = String.format("%.2f", bmi)
        result_TV.setTextColor(BmiAnalyser.getColorOfTextForGivenBMI(bmi))
        result_TV.text = bmiStr
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putDouble("bmi", bmi)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val receivedBmi: Double? = savedInstanceState?.getDouble("bmi", -1.0)
        if(receivedBmi !== null && receivedBmi >= 0.0){
            bmi = receivedBmi
            displayBMI()
        }
    }
}
