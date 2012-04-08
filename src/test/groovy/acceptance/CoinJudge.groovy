package test.groovy.acceptance

import main.groovy.Coin;

class CoinJudge {

    Coin sut

    def setup() {
        sut = new Coin()
    }

    def "引数が0の場合、RuntimeExceptionを投げる"() {
        when:
            sut.coinUsage(0)
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
    }

    def "引数が負の数の場合、RuntimeExceptionを投げる"() {
        when:
            sut.coinUsage(amount)
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
        where:
            amount << [-1, Integer.MIN_VALUE]
    }

    def "500円玉のみで実現できる場合"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount    | result
            500       | "500円玉"
            1000      | "500円玉2枚"
            100000000 | "500円玉200000枚"
    }

    def "100円玉のみで実現できる場合"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            100    | "100円玉"
            200    | "100円玉2枚"
            300    | "100円玉3枚"
            400    | "100円玉4枚"
    }

    def "50円玉のみで実現できる場合"() {
        expect:
            sut.coinUsage(50) == "50円玉"
    }

    def "10円玉のみで実現できる場合"() {
        expect:
             sut.coinUsage(amount) == result
        where:
            amount | result
            10     | "10円玉"
            20     | "10円玉2枚"
            30     | "10円玉3枚"
            40     | "10円玉4枚"
    }

    def "5円玉のみで実現できる場合"() {
        expect:
            sut.coinUsage(5) == "5円玉"
    }

    def "1円玉のみで実現できる場合"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            1      | "1円玉"
            2      | "1円玉2枚"
            3      | "1円玉3枚"
            4      | "1円玉4枚"
    }

    def "複数の種類のコインの組み合わせで実現できる場合"() {
        expect:
            sut.coinUsage(amount) == result
        where:
            amount | result
            // 6種類のコインの組み合わせ。
            /* 500*1, 100*2, 50*1, 10*3, 5*1, 1*4 */
            789    | "500円玉と100円玉2枚と50円玉と10円玉3枚と5円玉と1円玉4枚"

            // 5種類のコインの組み合わせ。
            /* 500*2, 100*3, 50*1, 10*4, 5*1, 1*0 */
            1395   | "500円玉2枚と100円玉3枚と50円玉と10円玉4枚と5円玉"
            /* 500*3, 100*4, 50*1, 10*1, 5*0, 1*1 */
            1961   | "500円玉3枚と100円玉4枚と50円玉と10円玉と1円玉"
            /* 500*4, 100*1, 50*1, 10*0, 5*1, 1*2 */
            2157   | "500円玉4枚と100円玉と50円玉と5円玉と1円玉2枚"
            /* 500*5, 100*2, 50*0, 10*2, 5*1, 1*3 */
            2728   | "500円玉5枚と100円玉2枚と10円玉2枚と5円玉と1円玉3枚"
            /* 500*6, 100*0, 50*1, 10*3, 5*1, 1*4 */
            3089   | "500円玉6枚と50円玉と10円玉3枚と5円玉と1円玉4枚"
            /* 500*0, 100*3, 50*1, 10*4, 5*1, 1*1 */
            396    | "100円玉3枚と50円玉と10円玉4枚と5円玉と1円玉"

            // 4種類のコインの組み合わせ。
            /* 500*7, 100*4, 50*1, 10*1, 5*0, 1*0 */
            3960   | "500円玉7枚と100円玉4枚と50円玉と10円玉"
            /* 500*8, 100*1, 50*1, 10*0, 5*1, 1*0 */
            4155   | "500円玉8枚と100円玉と50円玉と5円玉"
            /* 500*9, 100*2, 50*0, 10*2, 5*1, 1*0 */
            4725   | "500円玉9枚と100円玉2枚と10円玉2枚と5円玉"
            /* 500*1, 100*0, 50*1, 10*3, 5*0, 1*2 */
            582    | "500円玉と50円玉と10円玉3枚と1円玉2枚"
            /* 500*0, 100*1, 50*1, 10*4, 5*0, 1*3 */
            193    | "100円玉と50円玉と10円玉4枚と1円玉3枚"
    }

    def "巨大な金額の場合"() {
        expect:
            sut.coinUsage(Integer.MAX_VALUE) == "500円玉4294967枚と100円玉と10円玉4枚と5円玉と1円玉2枚"
    }
}
