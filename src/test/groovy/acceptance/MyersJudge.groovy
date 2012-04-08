package test.groovy.acceptance

import main.groovy.Myers
import spock.lang.Specification

public class MyersJudge extends Specification {

    Myers sut

    def setup() {
        sut = new Myers()
    }

    def "正三角形"() {
        when:
            def actual = sut.getName(5, 5, 5)
        then:
            actual == "正三角形"
    }

    def "二等辺三角形"() {
        when:
            def actual = sut.getName(5, 2, 5)
        then:
            actual=="二等辺三角形"
    }

    def "不等辺三角形"() {
        when:
            def actual = sut.getName(4, 8, 5)
        then:
            actual=="不等辺三角形"
    }

    def "三角形にならない"() {
        when:
            sut.getName(1, 2, 8)
        then:
            thrown RuntimeException
    }

    def "三角形にならない_境界値"() {
        when:
            sut.getName(3, 5, 8)
        then:
            thrown RuntimeException
    }

    def "辺の値が負"() {
        when:
            sut.getName(4, -5, 6)
        then:
            thrown RuntimeException
    }
}