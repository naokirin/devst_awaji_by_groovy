package main.groovy
import main.java.*
import java.math.RoundingMode

class Calculator implements features.Calculator {

    @Override
    String execute(String formula) {
        def formulaMatch = splitFormula(formula)
        if (!formulaMatch.matches()) throw RuntimeException
        def (a, b) = [formulaMatch[0][1], formulaMatch[0][3]].collect {it.toString().toInteger()}
        if (formulaMatch[0][2] == '+') return (a + b).toString()
        else if (formulaMatch[0][2] == '*') return (a * b).toString()
        else if (formulaMatch[0][2] == '-') return (a - b).toString()
        else if (formulaMatch[0][2] == '/') return divide(a, b)
    }

    java.util.regex.Matcher splitFormula(String formula) {
        def operatorsRegex = /(\+|\*|-|\/)/
        def numbersRegex = /([1-9][0-9]*)/
        return (formula =~ (/^/ + numbersRegex + operatorsRegex + numbersRegex + /$/))
    }

    String divide(int dividend, int divisor) {
        def result = dividend.toBigDecimal().divide(divisor, 3, RoundingMode.HALF_UP)
        3.downto(1) {
            def roundResult = result.divide(1, it-1, RoundingMode.DOWN)
            if (roundResult == result) {
                result = roundResult
            }
        }
        return result
    }
}
