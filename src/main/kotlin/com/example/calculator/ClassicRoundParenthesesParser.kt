package com.example.calculator
import java.util.*

class ClassicRoundParenthesesParser(override val math: IMathExpressionCalculate) :IParse {



    override fun parse(input: String,  ): Double {
        var input = input // параметр всегда val? val input

        val parenthesesIndexStack = Stack<Int>()
        while (input.contains('(') ){
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
        return math.calculate(input)
    }

//
//    fun getCalculatorDisc() : String{
//        return calc.description
//    }

}