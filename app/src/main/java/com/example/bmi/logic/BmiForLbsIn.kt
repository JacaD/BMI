package com.example.bmi.logic

class BmiForLbsIn(var mass : Int, var height: Int) : Bmi{
    override fun countBMI() : Double {
        require(mass > 40 && height > 50){ "Wrong data" }
        return mass * 703.0 / (height* height)
    }
}