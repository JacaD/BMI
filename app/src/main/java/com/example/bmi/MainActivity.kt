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
        val heightStr = height_ET.text.toString()
        val weightStr = weight_ET.text.toString()
        try {
            val bmi: Double = when(imperialSelected){
                true -> BmiForLbsIn(weightStr.toInt(), heightStr.toInt()).countBMI()
                false -> BmiForKgCm(weightStr.toInt(), heightStr.toInt()).countBMI()
            }
            displayBMI(bmi)
        }
        catch(e: Exception){
            Toast.makeText(applicationContext, "Wrong Data", Toast.LENGTH_LONG).show()
        }
    }

    fun showInfo(v: View){
        if(getBmi() == null || getBmi() == ""){
            return
        }
        startActivity(Intent(this, InfoActivity::class.java).putExtra("bmi", getBmi()))
    }

    fun displayBMI(bmi: Double){
        val bmiStr: String = String.format("%.2f", bmi)
        result_TV.setTextColor(BmiAnalyser.getColorOfTextForGivenBMI(bmi))
        result_TV.text = bmiStr
    }

    fun getBmi(): String?{
        val bmi: String? = result_TV.text.toString()
        println(bmi)
        if(bmi !== null && bmi !== ""){
            return bmi
        }
        return null
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        val bmi: String? = result_TV.text.toString()
        if(bmi !== null && bmi !== ""){
            outState?.putDouble("bmi", bmi.toDouble())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val receivedBmi: Double? = savedInstanceState?.getDouble("bmi", -1.0)
        println(receivedBmi)
        if(receivedBmi !== null && receivedBmi >= 0.0){
            displayBMI(receivedBmi)
        }
    }

}
