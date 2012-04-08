package test.groovy.unit

import spock.lang.Specification
import main.groovy.Tsurukamezan

class TsurukamezanTest extends Specification {

    Tsurukamezan sut

    def setup() {
        sut = new Tsurukamezan()
    }

    def "鶴亀算ができる"() {
        expect:
            sut.tsurukame(tsuruAndKame, allLegNum) == result
        where:
            tsuruAndKame | allLegNum | result
            1            | 2         | "鶴1羽、亀0匹"
            1            | 4         | "鶴0羽、亀1匹"
            2            | 6         | "鶴1羽、亀1匹"
            0            | 0         | "鶴0羽、亀0匹"
    }

    def "鶴亀算ができないとき、RuntimeExceptionが発生する"() {
        when:
            sut.tsurukame(tsuruAndKame, allLegNum)
        then:
            thrown RuntimeException
        where:
            tsuruAndKame | allLegNum
            1            | 3
            2            | 2
    }

    def "個体数が負のとき、RuntimeExceptionが発生する"() {
        when:
            sut.tsurukame(-1, 1)
        then:
            thrown RuntimeException
    }

    def "足の本数が負の数のとき、RuntimeExceptionが発生する"() {
        when:
            sut.tsurukame(1, -1)
        then:
            thrown RuntimeException
    }
}
