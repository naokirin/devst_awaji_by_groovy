package test.groovy.unit

import spock.lang.Specification
import main.groovy.RomanConverter

class RomanConverterTest extends Specification {

    RomanConverter sut

    def setup() {
        sut = new RomanConverter()
    }

    def "Iのみで表現できる"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman | arabic
            'I'   | 1
            'II'  | 2
            'III' | 3
    }

    def "IとVで表現できる"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman  | arabic
            'IV'   | 4
            'V'    | 5
            'VI'   | 6
            'VII'  | 7
            'VIII' | 8
    }

    def "XとIで表現できる"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman | arabic
            'IX'  | 9
            'X'   | 10
            'XIX' | 19
            'XX'  | 20
            'XXX' | 30
    }

    def "XとLで表現できる"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman  | arabic
            'XL'   | 40
            'L'    | 50
            'LX'   | 60
            'LXX'  | 70
            'LXXX' | 80
    }

    def "Cで表現できる"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman | arabic
            'C'   | 100
            'CC'  | 200
            'CCC' | 300
    }

    def "DとCで表現できる"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman  | arabic
            'CD'   | 400
            'D'    | 500
            'DC'   | 600
            'DCC'  | 700
            'DCCC' | 800
    }

    def "MとCで表現できる"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman | arabic
            'CM'  | 900
            'M'   | 1000
            'MM'  | 2000
            'MMM' | 3000
    }

    def "ローマ数字でない文字を含むとき、RuntimeExceptionが発生する"() {
        when:
            sut.toArabic(roman)
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
        where:
            roman << ['UI',
                      '1',
                      '十']
    }

    def "ローマ数字の表記が間違っているとき、RuntimeExceptionが発生する"() {
        when:
            sut.toArabic(roman)
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
        where:
            roman << ['VV',
                      'MMMM',
                      'IXIV',
                      'CMM',
                      'IVI',
                      'MMMVDI'
                      ]
    }

    def "引数がnullの場合、RuntimeExceptionが発生する"() {
        when:
            sut.toArabic(null)
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
    }

    def "空文字列のとき、RuntimeExceptionが発生する"() {
        when:
            sut.toArabic('')
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
    }
}
