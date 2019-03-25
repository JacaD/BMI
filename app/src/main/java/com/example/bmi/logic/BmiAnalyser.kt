package com.example.bmi.logic

import android.graphics.Color
import com.example.bmi.R

class BmiAnalyser {
    companion object {
        fun getDescriptionForGivenBMI(bmi: Double): String{
            return when {
                bmi < 18.5 -> "Underweight. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Vestibulum condimentum mi sapien, ac varius massa porta porta. " +
                        "In eget lacus pellentesque metus facilisis ornare. Nulla posuere urna a molestie bibendum. "
                bmi < 24.9 -> "Normal. \n Nullam quis velit in nulla feugiat semper. " +
                        "Integer efficitur neque eget sapien euismod auctor. " +
                        "Aenean laoreet leo pulvinar ultricies consequat."
                bmi < 29.9 -> "Overweight. \n Donec rhoncus laoreet nunc quis feugiat. " +
                        "Praesent iaculis, velit quis varius vehicula, nisl dui laoreet lectus, " +
                        "vel luctus risus neque non purus."
                bmi < 34.9 -> "Obese. \n Sed dignissim diam at eleifend pellentesque. " +
                        "Vestibulum molestie commodo mauris eu lacinia. " +
                        "Donec et lorem sit amet massa iaculis ultricies."
                else -> "Extremely Obese. \n Etiam nec velit et orci vestibulum pharetra. " +
                        "Phasellus tincidunt et sem et finibus. Nullam porttitor pharetra risus sit amet pellentesque. "
            }
        }

        fun getColorOfTextForGivenBMI(bmi: Double): Int{
            return when {
                bmi < 18.5 -> Color.parseColor("#26619c")
                bmi < 24.9 -> Color.parseColor("#00a693")
                bmi < 29.9 -> Color.parseColor("#ff6600")
                bmi < 34.9 -> Color.parseColor("#b80000")
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