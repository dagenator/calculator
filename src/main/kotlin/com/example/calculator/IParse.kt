package com.example.calculator

interface IParse {
    val math:  IMathExpressionCalculate
    fun parse(string: String): Double
}