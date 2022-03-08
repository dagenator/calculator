package com.example.calculator

import com.example.calculator.classicCalculatorImplementation.ClassicCalculatorMath
import com.example.calculator.classicCalculatorImplementation.ClassicCalculatorParams
import com.example.calculator.classicCalculatorImplementation.ClassicRoundParenthesesParser
import com.example.calculator.interfaces.IMathExpressionCalculate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class IntegrationTests() {

    private val math: IMathExpressionCalculate = ClassicCalculatorMath(ClassicCalculatorParams())
    var classicParser: ClassicRoundParenthesesParser = ClassicRoundParenthesesParser(math)

    @Test
    fun integrationParenthesesTest() {
        assertEquals(19.6666, classicParser.parse("1-2*2/3+20"), 0.001)
        assertEquals(11.0, classicParser.parse("(2*2+4)+3"), 0.001)
        assertEquals(4.0, classicParser.parse("-2*(3-5)"), 0.001)
        assertEquals(-16.0, classicParser.parse("(3-(2*3)-5)*2"), 0.001)
        assertEquals(4.0, classicParser.parse("-2*(0-2)"), 0.001)
        assertEquals(1.0, classicParser.parse("-2/(0-2)"), 0.001)
    }


}
