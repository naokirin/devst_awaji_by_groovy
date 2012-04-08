package test.groovy.unit

import spock.lang.Specification
import main.groovy.Coin

class CoinTest extends Specification {

    Coin sut

    def setup() {
        sut = new Coin()
    }

    def "1円玉のみのとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            1      | '1円玉'
            2      | '1円玉2枚'
            4      | '1円玉4枚'
    }

    def "5円のみのとき"() {
        expect:
            sut.coinUsage(5) == '5円玉'
    }

    def "10円玉のみのとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            10     | '10円玉'
            20     | '10円玉2枚'
            40     | '10円玉4枚'
    }

    def "50円のみのとき"() {
        expect:
            sut.coinUsage(50) == '50円玉'
    }

    def "100円玉のみのとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            100    | '100円玉'
            200    | '100円玉2枚'
            400    | '100円玉4枚'
    }

    def "500円玉のみのとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            500    | '500円玉'
            1000   | '500円玉2枚'
            1500   | '500円玉3枚'
    }

    def "複数の硬貨を使うとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            6      | '5円玉と1円玉'
            7      | '5円玉と1円玉2枚'
            16     | '10円玉と5円玉と1円玉'
            49     | '10円玉4枚と5円玉と1円玉4枚'
            66     | '50円玉と10円玉と5円玉と1円玉'
            95     | '50円玉と10円玉4枚と5円玉'
            166    | '100円玉と50円玉と10円玉と5円玉と1円玉'
            499    | '100円玉4枚と50円玉と10円玉4枚と5円玉と1円玉4枚'
    }

    def "0未満の金額のときは、RuntimeExceptionが発生する"() {
        when:
            sut.coinUsage(amount)
        then:
            thrown RuntimeException
        where:
            amount << [0, -1]
    }
}