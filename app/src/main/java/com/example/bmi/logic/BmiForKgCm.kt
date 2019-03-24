package com.example.bmi.logic

class BmiForKgCm(var mass : Int, var height: Int) : Bmi{
    override fun countBMI() : Double {
        require(mass > 20 && height > 100){ "Wrong data" }
        return mass * 10000.0 / (height* height)
    }
}