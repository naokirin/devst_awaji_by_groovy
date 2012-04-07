package test.groovy.unit

import main.groovy.*
import spock.lang.Specification

class CalculatorTest extends Specification {

    Calculator sut

    def setup() {
        sut = new Calculator()
    }

    def "1+1を受け取って、2を返す"() {
        expect:
            sut.execute('1+1') == '2'
    }

    def "1*1を受け取って、1を返す"() {
        expect:
            sut.execute('1*1') == '1'
    }

    def "1-1を受け取って、0を返す"() {
        expect:
            sut.execute('1-1') == '0'
    }

    def "1/1を受け取って、1を返す"() {
        expect:
            sut.execute('1/1') == '1'
    }

    def "式を受け取って、四則演算ができる"() {
        expect:
            sut.execute(formula) == expect
        where:
            formula | expect
            '10+11' | '21'
            '10*11' | '110'
            '10-11' | '-1'
            '20/10' | '2'
    }

    def "1/3を受け取って、0.333を返す"() {
        expect:
            sut.execute('1/3') == '0.333'
    }

    def "少数点以下が続くとき、第4位を四捨五入する"() {
        expect:
            sut.execute(formula) == expect
        where:
            formula | expect
            '30/21' | '1.429'
            '8/7'   | '1.143'
    }

    def "9999/10000を受け取って、1を返す"() {
        expect:
            sut.execute('9999/10000') == '1'
    }

    def "小数点以下が続き第4位を四捨五入して小数点以下の最後に0があったとき、小数点以下の最後の0は表示しない"() {
        expect:
            sut.execute('8999/10000') == '0.9'
        where:
            formula       | expect
            '8999/10000'  | '0.9'
            '10899/10000' | '1.09'
    }

    def "式が成立しないとき、RuntimeExceptionが発生する"() {
        when:
            sut.execute(formula)
        then:
            thrown RuntimeException
        where:
            formula << ['1+', '01-1', '1/01']
    }

    def "演算子が2つ以上あるとき、RuntimeExceptionが発生する"() {
        when:
            sut.execute(formula)
        then:
            thrown RuntimeException
        where:
            formula << ['1*2+3', '1-1-1', '1/1/1']
    }

    def "左辺もしくは右辺が正の整数でないとき、RuntimeExceptionが発生する"() {
        when:
            sut.execute(formula)
        then:
            thrown RuntimeException
        where:
            formula << ['-1+1', '1*0.1', '0.1/1']
    }

    def "左辺もしくは右辺が0のとき、RuntimeExceptionが発生する"() {
        when:
            sut.execute(formula)
        then:
            thrown RuntimeException
        where:
            formula << ['0+1', '1*0', '1/0']
    }
}
