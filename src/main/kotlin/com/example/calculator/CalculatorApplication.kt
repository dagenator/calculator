package com.example.calculator

import com.example.calculator.classicCalculatorImplementation.ClassicCalculatorMath
import com.example.calculator.classicCalculatorImplementation.ClassicCalculatorParams
import com.example.calculator.classicCalculatorImplementation.ClassicRoundParenthesesParser
import com.example.calculator.interfaces.CalculationParams
import com.example.calculator.interfaces.IMathExpressionCalculate
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalculatorApplication(calculator: CalculationParams): CommandLineRunner {

	val math: IMathExpressionCalculate = ClassicCalculatorMath(ClassicCalculatorParams())
	var parser: ClassicRoundParenthesesParser = ClassicRoundParenthesesParser(math)
	override fun run(vararg args: String?) {
		println("input math expression")
		val exp = readLine()

		if(exp != null){
			val res = parser.parse(exp)
			println("result: $res")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}
