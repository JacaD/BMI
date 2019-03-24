package com.example.bmi

import com.example.bmi.logic.BmiForKgCm
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import org.junit.Assert

class MojePierwszeFajneTesty : StringSpec() {
    init {
        "for valid mass and height return bmi"{
            val bmi = BmiForKgCm(65, 170)
            bmi.countBMI() shouldBeAround 22.491
        }
        "for mass or height lower than 0 should throw exception"{
            val bmi = BmiForKgCm(-1, 0)
            shouldThrow<IllegalArgumentException> {
                bmi.countBMI()
            }
        }
    }

    infix fun Double.shouldBeAround(value :Double){
        Assert.assertEquals(value,this,0.001)
    }
}