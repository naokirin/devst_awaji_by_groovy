package test.groovy.unit

import main.groovy.JapaneseSyllabary
import spock.lang.Specification

class JapaneseSyllabaryTest extends Specification {

    JapaneseSyllabary sut

    def setup() {
        sut = new JapaneseSyllabary()
    }

    def "あ行"() {
        expect:
            sut.execute((char)roman) == kana
        where:
            roman | kana
            'a'   | 'あ'
            'i'   | 'い'
            'u'   | 'う'
            'e'   | 'え'
            'o'   | 'お'
    }

    def "か行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            'k'    | 'a'    | 'か'
            'k'    | 'i'    | 'き'
            'k'    | 'u'    | 'く'
            'k'    | 'e'    | 'け'
            'k'    | 'o'    | 'こ'
    }

    def "さ行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            's'    | 'a'    | 'さ'
            's'    | 'i'    | 'し'
            's'    | 'u'    | 'す'
            's'    | 'e'    | 'せ'
            's'    | 'o'    | 'そ'
    }

    def "た行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            't'    | 'a'    | 'た'
            't'    | 'i'    | 'ち'
            't'    | 'u'    | 'つ'
            't'    | 'e'    | 'て'
            't'    | 'o'    | 'と'
    }

    def "な行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            'n'    | 'a'    | 'な'
            'n'    | 'i'    | 'に'
            'n'    | 'u'    | 'ぬ'
            'n'    | 'e'    | 'ね'
            'n'    | 'o'    | 'の'
    }

    def "は行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            'h'    | 'a'    | 'は'
            'h'    | 'i'    | 'ひ'
            'h'    | 'u'    | 'ふ'
            'h'    | 'e'    | 'へ'
            'h'    | 'o'    | 'ほ'
    }

    def "ま行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            'm'    | 'a'    | 'ま'
            'm'    | 'i'    | 'み'
            'm'    | 'u'    | 'む'
            'm'    | 'e'    | 'め'
            'm'    | 'o'    | 'も'
    }

    def "や行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            'y'    | 'a'    | 'や'
            'y'    | 'u'    | 'ゆ'
            'y'    | 'o'    | 'よ'
    }

    def "ら行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            'r'    | 'a'    | 'ら'
            'r'    | 'i'    | 'り'
            'r'    | 'u'    | 'る'
            'r'    | 'e'    | 'れ'
            'r'    | 'o'    | 'ろ'
    }

    def "わ行"() {
        expect:
            sut.execute((char)roman1, (char)roman2) == kana
        where:
            roman1 | roman2 | kana
            'w'    | 'a'    | 'わ'
            'w'    | 'o'    | 'を'
    }

    def "ん"() {
        expect:
            sut.execute((char)'n') == 'ん'
            sut.execute((char)'n', (char)'n') == 'ん'
    }

    def "3文字以上のときはRuntimeExceptionが発生する"() {
        when:
            sut.execute((char)roman1, (char)roman2, (char)roman3)
        then:
            thrown RuntimeException
        where:
            roman1 | roman2 | roman3
            'c'    | 'h'    | 'i'
            't'    | 'y'    | 'a'
    }

    def "ローマ字でないときはRuntimeExceptionが発生する"() {
        when:
            sut.execute((char)roman1, (char)roman2)
        then:
            thrown RuntimeException
        where:
            roman1 | roman2
            'r'    | 'x'
            'g'    | 'v'
    }
}
