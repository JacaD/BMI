package com.example.bmi.logic

class BmiForKgCm(var mass : Int, var height: Int) : Bmi{
    //override fun countBMI(): Double = mass * 10000.0 / (height * height)
    override fun countBMI() : Double {
        require(mass > 20 && height > 50){ "Wrong data" }
        return mass * 10000.0 / (height* height)
    }
}