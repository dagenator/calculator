package com.example.calculator

import com.example.calculator.classicCalculatorImplementation.ClassicCalculatorMath
import com.example.calculator.classicCalculatorImplementation.ClassicCalculatorParams
import com.example.calculator.classicCalculatorImplementation.ClassicRoundParenthesesParser
import com.example.calculator.interfaces.IMathExpressionCalculate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UnitTests {
    private val math: IMathExpressionCalculate = ClassicCalculatorMath(ClassicCalculatorParams())
    private var classicParser: ClassicRoundParenthesesParser = ClassicRoundParenthesesParser(math)

    @Test
    fun unitMathBlockTests() {
        Assertions.assertAll(
            { Assertions.assertEquals(0.0, classicParser.parse("1+2-3"), 0.001) },
            { Assertions.assertEquals(-1.0, classicParser.parse("1-2"), 0.001) },
            { Assertions.assertEquals(4.5, classicParser.parse("2.5+2"), 0.001) },
            { Assertions.assertEquals(-0.5, classicParser.parse("-2.5+2"), 0.001) },
            { Assertions.assertEquals(0.9, classicParser.parse("-2.5+3.4"), 0.001) },
            { Assertions.assertEquals(7.0, classicParser.parse("1+2*3"), 0.001) },
            { Assertions.assertEquals(-9.0, classicParser.parse("1-2*5"), 0.001) },
            { Assertions.assertEquals(5.0, classicParser.parse("2.5*2"), 0.001) },
            { Assertions.assertEquals(-4.0, classicParser.parse("-2-2"), 0.001) },
            { Assertions.assertEquals(0.0, classicParser.parse(""), 0.001) }
        )
    }
}