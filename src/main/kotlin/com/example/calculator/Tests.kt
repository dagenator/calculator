package com.example.calculator
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
//import org.mockito.Mockito

class Tests() {

// Глюк IDE? intelij не выполняет предлагаемые ею импорт в classpath и не видит mockito
//    @Mock
//    var calculator: CalculationParams

    private var classicParser = Parser(ClassicCalculator())

    @Test
    fun unitMathBlockTests() {
        assertAll(
            { assertEquals(0.0, classicParser.mathBlockParse("1+2-3"), 0.001) },
            { assertEquals(-1.0, classicParser.mathBlockParse("1-2"), 0.001) },
            { assertEquals(4.5, classicParser.mathBlockParse("2.5+2"), 0.001) },
            { assertEquals(-0.5, classicParser.mathBlockParse("-2.5+2"), 0.001) },
            { assertEquals(0.9, classicParser.mathBlockParse("-2.5+3.4"), 0.001) },
            { assertEquals(7.0, classicParser.mathBlockParse("1+2*3"), 0.001) },
            { assertEquals(-9.0, classicParser.mathBlockParse("1-2*5"), 0.001) },
            { assertEquals(5.0, classicParser.mathBlockParse("2.5*2"), 0.001) },
            { assertEquals(-4.0, classicParser.mathBlockParse("-2-2"), 0.001) }
        )
    }

    @Test
    fun integrationParenthesesTest() {
        assertEquals(19.6666, classicParser.parenthesesParser("1-2*2/3+20"), 0.001)
        assertEquals(11.0, classicParser.parenthesesParser("(2*2+4)+3"), 0.001)
        assertEquals(4.0, classicParser.parenthesesParser("-2*(3-5)"), 0.001)
        assertEquals(-16.0, classicParser.parenthesesParser("(3-(2*3)-5)*2"), 0.001)
        assertEquals(4.0, classicParser.parenthesesParser("-2*(0-2)"), 0.001)
        assertEquals(1.0, classicParser.parenthesesParser("-2/(0-2)"), 0.001)
    }
}
