package com.example.calculator.interfaces

interface IParse {
    val math: IMathExpressionCalculate
    fun parse(string: String): Double
}