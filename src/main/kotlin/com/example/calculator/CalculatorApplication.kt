package com.example.calculator

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalculatorApplication(calculator: CalculationParams): CommandLineRunner {

	val math: IMathExpressionCalculate = ClassicCalculatorMath(ClassicCalculatorParams())
	var parser:ClassicRoundParenthesesParser = ClassicRoundParenthesesParser(math)
	override fun run(vararg args: String?) {
		println("input math expression")
		val exp = readLine()

		if(exp != null){
			println("result: ${parser.parse(exp)}")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}
