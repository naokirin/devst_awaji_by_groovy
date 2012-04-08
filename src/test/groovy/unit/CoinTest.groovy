package test.groovy.unit

import spock.lang.Specification
import main.groovy.Coin

class CoinTest extends Specification {

    Coin sut

    def setup() {
        sut = new Coin()
    }

    def "1円のとき、1円玉一枚"() {
        expect:
            sut.coinUsage(1) == '1円玉'
    }

    def "1円玉のみのとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            2      | '1円玉2枚'
            4      | '1円玉4枚'
    }

    def "5円のとき、5円玉1枚"() {
        expect:
            sut.coinUsage(5) == '5円玉'
    }

    def "10円のとき、10円玉1枚"() {
        expect:
            sut.coinUsage(10) == '10円玉'
    }

    def "10円玉のみのとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            20     | '10円玉2枚'
            40     | '10円玉4枚'
    }

    def "50円のとき、50円玉1枚"() {
        expect:
            sut.coinUsage(50) == '50円玉'
    }

    def "100円のとき、100円玉1枚"() {
        expect:
            sut.coinUsage(100) == '100円玉'
    }

    def "100円玉のみのとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            200    | '100円玉2枚'
            400    | '100円玉4枚'
    }

    def "500円のとき、500円玉1枚"() {
        expect:
            sut.coinUsage(500) == '500円玉'
    }

    def "500円玉のみのとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            1000   | '500円玉2枚'
            1500   | '500円玉3枚'
    }

    def "6円のとき、5円玉1枚と1円玉1枚"() {
        expect:
            sut.coinUsage(6) == '5円玉と1円玉'
    }

    def "複数の硬貨を使うとき"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
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