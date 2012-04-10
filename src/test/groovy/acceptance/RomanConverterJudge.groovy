package test.groovy.acceptance

import main.groovy.RomanConverter
import spock.lang.Specification


class RomanConverterJudge extends Specification {

    RomanConverter sut

    def setup() {
        sut = new RomanConverter()
    }

    def "引数がnullの場合、RuntimeExceptionを投げる"() {
        when:
            sut.toArabic(null)
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
    }

    def "引数が空文字列の場合、RuntimeExceptionを投げる"() {
        when:
            sut.toArabic("")
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
    }

    def "1000の位の数字のみの場合"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman | arabic
            "MMM" | 3000
            "MM"  | 2000
            "M"   | 1000
    }

    def "100の位の数字のみの場合"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman  | arabic
            "CM"   | 900
            "DCCC" | 800
            "DCC"  | 700
            "DC"   | 600
            "D"    | 500
            "CD"   | 400
            "CCC"  | 300
            "CC"   | 200
            "C"    | 100
    }

    def "10の位の数字のみの場合"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman  | arabic
            "XC"   | 90
            "LXXX" | 80
            "LXX"  | 70
            "LX"   | 60
            "L"    | 50
            "XL"   | 40
            "XXX"  | 30
            "XX"   | 20
            "X"    | 10
    }

    def "1の位の数字のみの場合"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman  | arabic
            "IX"   | 9
            "VIII" | 8
            "VII"  | 7
            "VI"   | 6
            "V"    | 5
            "IV"   | 4
            "III"  | 3
            "II"   | 2
            "I"    | 1
    }

    def "1の位から1000の位までの数字を複数組み合わせた数の場合"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman        | arabic
            "MCCCLXXXIV" | 1384	  // 1000, 100, 10, 1
            "MMCDXC"     | 2490	  // 1000, 100, 10, -
            "MMMDV"      | 3505	  // 1000, 100, --, 1
            "MXVI"       | 1016	  // 1000, ---, 10, 1
            "DCXXVII"    | 627	  // ----, 100, 10, 1
            "MMDCC"      | 2700	  // 1000, 100, --, -
            "MMMVIII"    | 3008	  // 1000, ---, --, 1
            "XXXIX"      | 39	  // ----, ---, 10, 1
            "MXL"        | 1040	  // 1000, ---, 10, -
            "DCCCI"      | 801	  // ----, 100, --, 1
            "CML"        | 950	  // ----, 100, 10, -
    }

    def "適当にいくつかの例。(Wikipediaに書かれていた例。)"() {
        expect:
            sut.toArabic(roman) == arabic
        where:
            roman           | arabic
            "XI"            | 11
            "XII"           | 12
            "XIV"           | 14
            "XVIII"         | 18
            "XXIV"          | 24
            "XLIII"         | 43
            "XCIX"          | 99
            "CDXCV"         | 495
            "MDCCCLXXXVIII" | 1888
            "MCMXLV"        | 1945
            "MMMCMXCIX"     | 3999
    }

    def "ローマ数字に使用されない文字が含まれていた場合、RuntimeExceptionを投げる"() {
        when:
            sut.toArabic(roman)
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
        where:
            roman << ["0",
                      "iii",
                      "200",
                      "三千九百九十九"]
    }

    def "ローマ数字に使用される文字だが、正しくない並びの場合、RuntimeExceptionを投げる"() {
        when:
            sut.toArabic(roman)
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
        where:
            roman << ["IIII",
                      "VIIII",
                      "IIIV",
                      "LXL",
                      "MMMCMXCIXI",
                      ]
    }
}
