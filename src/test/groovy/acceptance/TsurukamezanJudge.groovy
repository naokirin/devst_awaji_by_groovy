package test.groovy.acceptance

import main.groovy.Tsurukamezan
import spock.lang.Specification

class TsurukamezanJudge extends Specification {

    Tsurukamezan sut

    def setup() {
        sut = new Tsurukamezan()
    }

    def "鶴一羽"() {
        when:
            def actual = sut.tsurukame(1, 2)
        then:
            actual == "鶴1羽、亀0匹"
    }


    def "亀一匹"() {
        when:
            def actual = sut.tsurukame(1, 4)
        then:
            actual == "鶴0羽、亀1匹"
    }


    def "鶴八羽と亀五匹"() {
        when:
            def actual = sut.tsurukame(13, 36)
        then:
            actual == "鶴8羽、亀5匹"
    }


    def "計算が成立しない"() {
        when:
            sut.tsurukame(1, 3)
        then:
            thrown RuntimeException
    }


    def "両方ゼロ"() {
        when:
            def actual = sut.tsurukame(0, 0)
        then:
            actual == "鶴0羽、亀0匹"
    }


    def "マイナスはダメ"() {
        when:
            sut.tsurukame(-1, -2)
        then:
            thrown RuntimeException
    }
}
