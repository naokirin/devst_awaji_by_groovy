package test.groovy.unit

import spock.lang.Specification
import main.groovy.Myers

class MyersTest extends Specification {

    Myers sut

    def setup() {
        sut = new Myers()
    }

    def "3辺の長さがすべて同じ三角形は、正三角形である"() {
        expect:
            sut.getName(x, y, z) == "正三角形"
        where:
            x | y | z
            1 | 1 | 1
            2 | 2 | 2
    }

    def "2辺の長さが等しい三角形は、二等辺三角形である"() {
        expect:
            sut.getName(x, y, z) == "二等辺三角形"
        where:
            x | y | z
            2 | 2 | 1
            3 | 3 | 2
    }

    def "辺の長さがすべて異なる三角形は、不等辺三角形である"() {
        expect:
            sut.getName(x, y, z) == "不等辺三角形"
        where:
            x | y | z
            2 | 3 | 4
            3 | 4 | 5
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
