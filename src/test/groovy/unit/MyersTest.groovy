package test.groovy.unit

import spock.lang.Specification
import main.groovy.Myers

class MyersTest extends Specification {

    Myers sut

    def setup() {
        sut = new Myers()
    }

    def "3辺の長さがすべて1のとき、正三角形である"() {
        expect:
            sut.getName(1, 1, 1) == "正三角形"
    }

    def "3辺の長さがすべて同じ三角形は、正三角形である"() {
        expect:
            sut.getName(2, 2, 2) == "正三角形"
    }

    def "辺の長さがそれぞれ2,2,1のとき、二等辺三角形である"() {
        expect:
            sut.getName(2, 2, 1) == "二等辺三角形"
    }

    def "2辺の長さが等しい三角形は、二等辺三角形である"() {
        expect:
            sut.getName(3, 3, 2) == "二等辺三角形"
    }

    def "辺の長さがそれぞれ1,2,4のとき、不等辺三角形である"() {
        expect:
            sut.getName(2, 3, 4) == "不等辺三角形"
    }

    def "辺の長さがすべて異なる三角形は、不等辺三角形である"() {
        expect:
            sut.getName(3, 4, 5) == "不等辺三角形"
    }

    def "最も長い辺が他の2辺の和より長いとき、RuntimeExceptionが発生する"() {
        when:
            sut.getName(1, 1, 2)
        then:
            thrown RuntimeException
    }

    def "辺の値が負のとき、RuntimeExceptionが発生する"() {
        when:
            sut.getName(-1, 1, 1)
        then:
            thrown RuntimeException
    }
}
