package test.groovy.acceptance

import main.groovy.JapaneseSyllabary
import spock.lang.Specification

class JapaneseSyllabaryJudge extends Specification {

    JapaneseSyllabary sut

    def setup() {
        sut = new JapaneseSyllabary()
    }

    def "母音"() {
        expect:
            sut.execute((char)roman) == kana
        where:
            roman | kana
            'a'   | "あ"
            'i'   | "い"
            'u'   | "う"
            'e'   | "え"
            'o'   | "お"
    }


    def "子音と母音"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            'k'    | 'a'    | "か"
            'm'    | 'u'    | "む"
    }


    def "特記_ち"() {
        expect:
            sut.execute((char)'t', (char)'i') == "ち"
    }


    def "三文字以上はNG"() {
        when:
            sut.execute((char)'k', (char)'y', (char)'a')
        then:
            thrown RuntimeException
    }


    def "母音続きNG"() {
        when:
            sut.execute((char)'a', (char)'a')
        then:
            thrown RuntimeException
    }


    def "子音のみNG"() {
        when:
            sut.execute((char)'y')
        then:
            thrown RuntimeException
    }


    def "NはOK"() {
        expect:
            sut.execute((char)'n') == "ん"
            sut.execute((char)'n', (char)'n') == "ん"
    }
}
