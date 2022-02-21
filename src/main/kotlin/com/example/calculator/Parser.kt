package com.example.calculator
import java.util.*

class Parser (private val calc: CalculationParams){

    var missedChar: Char? = null

    fun parenthesesParser( input: String): Double {
        var input = input // параметр всегда val? val input

        val parenthesesIndexStack = Stack<Int>()
        while (input.contains('(') ){
            var newInput:String = ""
            for (i in input.indices) {
                if (input[i] == '(') {
                    parenthesesIndexStack.push(i)
                } else if (input[i] == ')'){
                    val firstIndex: Int = parenthesesIndexStack.pop()
                    val partResult = mathBlockParse(input.substring(firstIndex+1, i))
                    val firstStringPart = input.substring(0, firstIndex)
                    val secondStringPart = input.substring(i + 1, input.length)
                    newInput = firstStringPart + partResult.toString() + secondStringPart
                    break;
                }
            }
            input = newInput
        }
        return mathBlockParse(input)
    }

    fun mathBlockParse(input: String, actionIndex: Int = 0): Double {

        var input = input
        var missedAction: ((input: Double, Double) -> Double)? = null

        if (input == ""){
            return 0.0
        }

        if (calc.actionsPriority.contains(input[input.length - 1])) {
            // Костыль? Проверка * или \ межд дыумя отриц числами - missed char
            missedChar = input[input.length - 1]
            input = input.substring(0, input.length - 1)
        }

        val result = input.toDoubleOrNull()
        if (result != null){
            return result
        }

        val actionChar = calc.actionsPriority[actionIndex]

        val action = calc.actions[actionChar]
            ?: throw java.lang.Exception("something wrong with math action array and math action map")

        var currentBlockResult: Double? = null

        input.split(actionChar).forEach {
            if (currentBlockResult == null) {
                currentBlockResult = mathBlockParse(it, actionIndex + 1)
            } else {
                if(missedAction != null){
                    currentBlockResult = missedAction!!(currentBlockResult!!,-1* mathBlockParse(it, actionIndex + 1))
                } else{
                    currentBlockResult = action(currentBlockResult!!, mathBlockParse(it, actionIndex + 1))
                }

                if(missedChar != null) missedAction = calc.actions[missedChar]!!
                missedChar = null
            }
        }
        return currentBlockResult!!
    }

    fun getCalculatorDisc() : String{
        return calc.description
    }

}