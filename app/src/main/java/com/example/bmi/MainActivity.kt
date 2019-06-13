package com.example.bmi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.bmi.logic.BmiAnalyser
import com.example.bmi.logic.BmiForKgCm
import com.example.bmi.logic.BmiForLbsIn
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import org.json.JSONObject
import java.nio.file.Files.size
import android.preference.PreferenceManager
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    var imperialSelected: Boolean = false
    var bmi: Double = -1.0

    data class HistoryEntry(
        val weight: Double,
        val height: Double
    )

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val aboutItem: MenuItem? = menu?.findItem(R.id.about)
        val aboutIntent = Intent(this, AboutActivity::class.java)
        aboutItem?.intent = aboutIntent
        val historyItem: MenuItem? = menu?.findItem(R.id.history)
        val historyIntent = Intent(this, HistoryActivity::class.java)
        historyItem?.intent = historyIntent
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
            saveBMI(weight, height)
        }
        catch(e: Exception){
            Toast.makeText(applicationContext, "Wrong Data", Toast.LENGTH_LONG).show()
        }
    }

    fun showInfo(v: View){
        if(bmi <= 0.0) return
        startActivity(Intent(this, InfoActivity::class.java).putExtra("bmi", bmi))
    }

//    fun showHistory(v: View){
//        startActivity(Intent(this, HistoryActivity::class.java))
//    }

    private fun saveBMI(weight: Int, height: Int){
        val history: ArrayList<String?> = ArrayList()
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val size = sp.getInt("HistorySize", 0)
        for (i in 0 until size) {
            history.add(sp.getString("Entry_$i", null))
        }

        if(history.size == 10){
            history.removeAt(0)
        }
        if(!history.contains("$weight,$height,$imperialSelected")) {
            history.add("$weight,$height,$imperialSelected")
        }

        val spEdit = sp.edit()
        spEdit.putInt("HistorySize", history.size)

        for (i in 0 until history.size) {
            spEdit.remove("Entry_$i")
            spEdit.putString("Entry_$i", history[i])
        }
        spEdit.apply()
    }

    private fun displayBMI(){
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
