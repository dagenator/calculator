package com.example.calculator

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalculatorApplication(calculator: CalculationParams): CommandLineRunner {

	var parser:Parser = Parser(calculator)
	override fun run(vararg args: String?) {
		println("input math expression")
		val exp = readLine()

		if(exp != null){
			println("result: ${parser.parenthesesParser(exp)}")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}
