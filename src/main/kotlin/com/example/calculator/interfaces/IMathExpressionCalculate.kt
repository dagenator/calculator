package com.example.calculator.interfaces

interface IMathExpressionCalculate {
    val params : CalculationParams
    fun calculate (input: String, actionIndex: Int = 0): Double
}