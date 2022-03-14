package com.example.calculator.classicCalculatorImplementation
import com.example.calculator.interfaces.CalculationParams
import org.springframework.stereotype.Component
import java.util.*

@Component
class ClassicCalculatorParams : CalculationParams {
    override val actions: LinkedHashMap<Char, (input: Double, Double) -> Double> = linkedMapOf(
        '+' to { a, b -> a + b },
        '-' to { a, b -> a - b },
        '*' to { a, b -> a * b },
        '/' to { a, b -> a / b }
    )
    //val actionsPriority = charArrayOf('+', '-','*','/')
    override val actionsPriority = actions.keys.toCharArray()
    override val description = "Classic"
}