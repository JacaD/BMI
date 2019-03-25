package com.example.bmi.logic

import android.graphics.Color
import com.example.bmi.R

class BmiAnalyser {
    companion object {
        fun getDescriptionForGivenBMI(bmi: Double): Int{
            return when {
                bmi < 18.5 -> R.string.bmi_main_description_for_underweight
                bmi < 24.9 -> R.string.bmi_main_description_for_normal
                bmi < 29.9 -> R.string.bmi_main_description_for_overweight
                bmi < 34.9 -> R.string.bmi_main_description_for_obese
                else -> R.string.bmi_main_description_for_extremely_obese
            }
        }

        fun getColorOfTextForGivenBMI(bmi: Double): Int{
            return when {
                bmi < 18.5 -> Color.parseColor("#26619c") //lapis lazuli
                bmi < 24.9 -> Color.parseColor("#00a693") //grynszpan
                bmi < 29.9 -> Color.parseColor("#ff6600")
                bmi < 34.9 -> Color.parseColor("#b80000") //róż pompejański
                else -> Color.parseColor("#9400d3")
            }
        }

        fun getImageIdForGivenBMI(bmi: Double): Int{
            return when {
                bmi < 18.5 -> R.drawable.underweight
                bmi < 24.9 -> R.drawable.normal
                bmi < 29.9 -> R.drawable.overweight
                bmi < 34.9 -> R.drawable.obese
                else -> R.drawable.extreme_obese
            }
        }
    }
}