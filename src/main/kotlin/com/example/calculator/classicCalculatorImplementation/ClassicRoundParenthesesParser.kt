package com.example.calculator.classicCalculatorImplementation
import com.example.calculator.interfaces.IMathExpressionCalculate
import com.example.calculator.interfaces.IParse
import java.util.*

class ClassicRoundParenthesesParser(override val math: IMathExpressionCalculate) : IParse {

    override fun parse(input: String): Double {
        var input = input

        val parenthesesIndexStack = Stack<Int>()
        while (input.contains('(') && input.contains(')')){
            var newInput:String = ""
            for (i in input.indices) {
                if (input[i] == '(') {
                    parenthesesIndexStack.push(i)
                } else if (input[i] == ')'){
                    val firstIndex: Int = parenthesesIndexStack.pop()
                    val partResult = math.calculate(input.substring(firstIndex+1, i))
                    val firstStringPart = input.substring(0, firstIndex)
                    val secondStringPart = input.substring(i + 1, input.length)
                    newInput = firstStringPart + partResult.toString() + secondStringPart
                    break;
                }
            }
            input = newInput
        }
        if(input.contains('(') || input.contains(')'))
            throw Exception("Not correct parentheses expression")
        return math.calculate(input)
    }
}