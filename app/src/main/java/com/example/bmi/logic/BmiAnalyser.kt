package com.example.bmi.logic

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import com.example.bmi.R
import kotlin.coroutines.coroutineContext

class BmiAnalyser {
    val it = this
    companion object {
        fun getDescriptionForGivenBMI(bmi: Double): String{
            if(bmi < 18.5){
                return "Underweight. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Vestibulum condimentum mi sapien, ac varius massa porta porta. " +
                        "In eget lacus pellentesque metus facilisis ornare. Nulla posuere urna a molestie bibendum. "
            }
            else if(bmi < 24.9){
                return "Normal. \n Nullam quis velit in nulla feugiat semper. " +
                        "Integer efficitur neque eget sapien euismod auctor. " +
                        "Aenean laoreet leo pulvinar ultricies consequat."
            }
            else if(bmi < 29.9){
                return "Overweight. \n Donec rhoncus laoreet nunc quis feugiat. " +
                        "Praesent iaculis, velit quis varius vehicula, nisl dui laoreet lectus, " +
                        "vel luctus risus neque non purus."
            }
            else if(bmi < 34.9){
                return "Obese. \n Sed dignissim diam at eleifend pellentesque. " +
                        "Vestibulum molestie commodo mauris eu lacinia. " +
                        "Donec et lorem sit amet massa iaculis ultricies."
            }
            return "Extremely Obese. \n Etiam nec velit et orci vestibulum pharetra. " +
                    "Phasellus tincidunt et sem et finibus. Nullam porttitor pharetra risus sit amet pellentesque. "
        }

        fun getColorOfTextForGivenBMI(bmi: Double): Int{
            if(bmi < 18.5){
                return Color.parseColor("#26619c")
            }
            else if(bmi < 24.9){
                return  Color.parseColor("#00a693")
            }
            else if(bmi < 29.9){
                return Color.parseColor("#ff6600")
            }
            else if(bmi < 34.9){
                return Color.parseColor("#b80000")
            }
            return Color.parseColor("#9400d3")
        }

        fun getImageIdForGivenBMI(bmi: Double): Int{
            if(bmi < 18.5){
                return R.drawable.underweight
            }
            else if(bmi < 24.9){
                return R.drawable.normal
            }
            else if(bmi < 29.9){
                return R.drawable.overweight
            }
            else if(bmi < 34.9){
                return R.drawable.obese
            }
            return R.drawable.extreme_obese
        }
    }
}