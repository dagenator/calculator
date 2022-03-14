package com.example.calculator;

import com.example.calculator.classicCalculatorImplementation.ClassicCalculatorMath;
import com.example.calculator.classicCalculatorImplementation.ClassicRoundParenthesesParser;
import com.example.calculator.interfaces.CalculationParams;
import com.example.calculator.interfaces.IMathExpressionCalculate;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class UnitMockitoTests {

    @Mock
    CalculationParams mockParamsEmpty = Mockito.mock(CalculationParams.class);

    @Test
    public void testForIOException() throws Exception {
        doThrow(new MockitoException("CalclulationParams.Actions is empty ") ).when(mockParamsEmpty).getActions();
        IMathExpressionCalculate math = new ClassicCalculatorMath(mockParamsEmpty);
        assertThrows(Exception.class, () -> math.calculate("1+1", 0));
    }

    @Mock
    IMathExpressionCalculate math = new ClassicCalculatorMath(mockParamsEmpty);

    @Test
    public void ClassicParserNorCorrectExpressionTest(){
        var parser = new ClassicRoundParenthesesParser(math);
        Exception exception = assertThrows(Exception.class, () -> {
            parser.parse("()(()");
        });
        String expectedMessage = "Not correct parentheses expression";
        String actualMessage = exception.getMessage();

        assertSame(expectedMessage, actualMessage);
    }

    @Mock
    IMathExpressionCalculate mathReturnZero = Mockito.spy(IMathExpressionCalculate.class);
    @Test
    public void ClassicParserExpressionTest(){

        Mockito.when(mathReturnZero.calculate("",0)).thenReturn(0.0);

        var parser = new ClassicRoundParenthesesParser(mathReturnZero);
        assertEquals(0.0, parser.parse("()"), 0.001);
        assertEquals(0.0, parser.parse("(())"), 0.001);
        assertEquals(0.0, parser.parse("(())()"), 0.001);
        assertEquals(0.0, parser.parse("((())())"), 0.001);
        assertEquals(0.0, parser.parse(" "), 0.001);
    }
    
}
