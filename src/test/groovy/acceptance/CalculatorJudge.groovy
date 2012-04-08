package test.groovy.acceptance

import main.groovy.Calculator
import spock.lang.Specification

public class CalculatorJudge extends Specification{

    Calculator sut

    def setup() {
        sut = new Calculator()
    }

    def "加算"() {
        expect:
            sut.execute("1+2") == "3"
    }


    def "減算"() {
        expect:
            sut.execute("1-3") == "-2"
    }


    def "乗算"() {
        expect:
            sut.execute("3*2") == "6"
    }


    def "除算_割り切れる"() {
        expect:
            sut.execute("9/3") == "3"
    }


    def "除算_四捨五入切り捨て"() {
        expect:
            sut.execute("100/3") == "33.333"
    }


    def "除算_四捨五入切り上げ"() {
        expect:
            sut.execute("1/16") == "0.063"
    }


    def "左辺ゼロ"() {
        when:
            sut.execute("0+1")
        then:
            thrown RuntimeException
    }


    def "右辺ゼロ"() {
        when:
            sut.execute("1-0")
        then:
            thrown RuntimeException
    }


    def "数式として成立しない_左辺のみ"() {
        when:
            sut.execute("10-")
        then:
            thrown RuntimeException
    }


    def "数式として成立しない_右辺のみ"() {
        when:
            sut.execute("*10")
        then:
            thrown RuntimeException
    }


    def "演算子二つ以上"() {
        when:
            sut.execute("1+2-3")
        then:
            thrown RuntimeException
    }
}
