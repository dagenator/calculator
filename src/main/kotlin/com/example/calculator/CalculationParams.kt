package com.example.calculator

import java.util.LinkedHashMap

interface CalculationParams {
    val actions: LinkedHashMap<Char, (input: Double, Double) -> Double>
    val actionsPriority: CharArray
    val description:String

}