package com.example.calculator

class ClassicCalculatorMath(override val params: CalculationParams) : IMathExpressionCalculate {

    var missedChar: Char? = null

    override fun calculate(input: String, actionIndex: Int): Double {
        if(params.actions.isEmpty()) throw Exception("CalclulationParams.Actions is empty ")

        var input = input
        var missedAction: ((input: Double, Double) -> Double)? = null

        if (input == "") {
            return 0.0
        }

        if (params.actionsPriority.contains(input[input.length - 1])) {
            // Костыль? Проверка * или \ межд дыумя отриц числами - missed char
            missedChar = input[input.length - 1]
            input = input.substring(0, input.length - 1)
        }

        val result = input.toDoubleOrNull()
        if (result != null) {
            return result
        }

        val actionChar = params.actionsPriority[actionIndex]

        val action = params.actions[actionChar]
            ?: throw java.lang.Exception("something wrong with math action array and math action map")

        var currentBlockResult: Double? = null

        input.split(actionChar).forEach {
            if (currentBlockResult == null) {
                currentBlockResult = calculate(it, actionIndex + 1)
            } else {
                if (missedAction != null) {
                    currentBlockResult =
                        missedAction!!(currentBlockResult!!, -1 * calculate(it, actionIndex + 1))
                } else {
                    currentBlockResult = action(currentBlockResult!!, calculate(it, actionIndex + 1))
                }

                if (missedChar != null) missedAction = params.actions[missedChar]!!
                missedChar = null
            }
        }
        return currentBlockResult!!
    }

}


