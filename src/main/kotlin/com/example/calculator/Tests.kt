package com.example.calculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.mock


class Tests() {

    val math: IMathExpressionCalculate = ClassicCalculatorMath(ClassicCalculatorParams())
    var classicParser:ClassicRoundParenthesesParser = ClassicRoundParenthesesParser(math)

    @Test
    fun unitMathBlockTests() {
        assertAll(
            { assertEquals(0.0, classicParser.parse("1+2-3"), 0.001) },
            { assertEquals(-1.0, classicParser.parse("1-2"), 0.001) },
            { assertEquals(4.5, classicParser.parse("2.5+2"), 0.001) },
            { assertEquals(-0.5, classicParser.parse("-2.5+2"), 0.001) },
            { assertEquals(0.9, classicParser.parse("-2.5+3.4"), 0.001) },
            { assertEquals(7.0, classicParser.parse("1+2*3"), 0.001) },
            { assertEquals(-9.0, classicParser.parse("1-2*5"), 0.001) },
            { assertEquals(5.0, classicParser.parse("2.5*2"), 0.001) },
            { assertEquals(-4.0, classicParser.parse("-2-2"), 0.001) }
        )
    }

    @Test
    fun integrationParenthesesTest() {
        assertEquals(19.6666, classicParser.parse("1-2*2/3+20"), 0.001)
        assertEquals(11.0, classicParser.parse("(2*2+4)+3"), 0.001)
        assertEquals(4.0, classicParser.parse("-2*(3-5)"), 0.001)
        assertEquals(-16.0, classicParser.parse("(3-(2*3)-5)*2"), 0.001)
        assertEquals(4.0, classicParser.parse("-2*(0-2)"), 0.001)
        assertEquals(1.0, classicParser.parse("-2/(0-2)"), 0.001)
    }
//
//    @Mock
//    val mockParams: CalculationParams = Mockito.mock(CalculationParams::class.java)

//    @MockBean
//    val mockParams = mock(CalculationParams::class.java)
//
//    @Test
//    fun exceptionIfActionListIsEmpty(){
//
//        doThrow(Exception()).`when`(mockParams).actions.isEmpty()
//
//        val math: IMathExpressionCalculate = ClassicCalculatorMath(mockParams)
//
//        assertThrows(Exception::class.java) { math.calculate("1+1") }
//
//    }
}
