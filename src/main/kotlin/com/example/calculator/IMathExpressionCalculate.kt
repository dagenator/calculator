package com.example.calculator

interface IMathExpressionCalculate {
    val params :CalculationParams
    fun calculate (input: String, actionIndex: Int = 0): Double
}