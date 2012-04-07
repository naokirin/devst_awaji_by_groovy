package main.groovy
import main.java.*
import java.math.RoundingMode

class Calculator implements features.Calculator {

    @Override
    String execute(String formula) {

        def operatorsRegix = /(\+|\*|-|\/)/
        def numbersRegix = /([1-9][0-9]*)/
        def formulaSplits = (formula =~ (/^/ + numbersRegix + operatorsRegix + numbersRegix + /$/))
        if (formulaSplits.matches()) {
            def (a, b) = [formulaSplits[0][1], formulaSplits[0][3]].collect {it.toString().toInteger()}
            if(a == 0 || b == 0) throw new RuntimeException()
            else if (formulaSplits[0][2] == '+') return (a + b).toString()
            else if (formulaSplits[0][2] == '*') return (a * b).toString()
            else if (formulaSplits[0][2] == '-') return (a - b).toString()
            else if (formulaSplits[0][2] == '/') {
                def result = a.toBigDecimal().divide(b, 3, RoundingMode.HALF_UP)
                2.downto(1) {
                    def roundResult = result.divide(1, it, RoundingMode.DOWN)
                    if (roundResult == result) {
                        result = roundResult
                    }
                }
                if(result.toInteger() == result) return result.toInteger()
                return result
            }
        }
        else
            throw new RuntimeException()
    }
}
