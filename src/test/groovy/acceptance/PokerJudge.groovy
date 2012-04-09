package test.groovy.acceptance

import main.groovy.Poker
import spock.lang.Specification

public class PokerJudge extends Specification {

    Poker sut

    def setup() {
        sut = new Poker()
    }

    def "引数が5個以外の場合、RuntimeExceptionを投げる"() {
        when:
        sut.poker(cards.toArray() as String[])
        then:
        def e = thrown RuntimeException
        e.class == RuntimeException
        where:
        cards << [[],
                  ["SA", "S2", "S3", "S4"],
                  ["SA", "S2", "S3", "S4", "S5", "S6"]]
    }

    def "カードの表記法が誤っている場合、RuntimeExceptionを投げる"() {
        when:
        sut.poker(card1, card2, card3, card4, card5)
        then:
        def e = thrown RuntimeException
        e.class == RuntimeException
        where:
            card1 | card2 | card3 | card4 | card5
            "S1"  | "H2"  | "D3"  | "C4"  | "S5"
            "S11" | "H2"  | "D3"  | "C4"  | "S5"
            "SB"  | "H2"  | "D3"  | "C4"  | "S5"
            "NA"  | "H2"  | "D3"  | "C4"  | "S5"
            "sA"  | "H2"  | "D3"  | "C4"  | "S5"
            "Sa"  | "H2"  | "D3"  | "C4"  | "S5"
            "SＡ" | "H2"  | "D3"  | "C4"  | "S5"
            "SAA" | "H2"  | "D3"  | "C4"  | "S5"
            "SA"  | "H20" | "D3"  | "C4"  | "S5"
            "SA"  | "H2"  | "D31" | "C4"  | "S5"
            "SA"  | "H2"  | "D3"  | "C42" | "S5"
            "SA"  | "H2"  | "D3"  | "C4"  | "S53"
    }

    def "カードが重複している場合、RuntimeExceptionを投げる"() {
        when:
        sut.poker(card1, card2, card3, card4, card5)
        then:
        def e = thrown RuntimeException
        e.class == RuntimeException
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "SA"  | "D3"  | "C4"  | "S5"
            "SA"  | "H2"  | "SA"  | "C4"  | "S5"
            "SA"  | "H2"  | "D3"  | "SA"  | "S5"
            "SA"  | "H2"  | "D3"  | "C4"  | "SA"
            "SA"  | "H2"  | "H2"  | "C4"  | "S5"
            "SA"  | "H2"  | "D3"  | "H2"  | "S5"
            "SA"  | "H2"  | "D3"  | "S5"  | "S5"
    }

    def "フラッシュの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "フラッシュ"
        where:
            card1 | card2 | card3 | card4 | card5
            "S2"  | "SK"  | "S5"  | "SA"  | "SQ"
            "H3"  | "H2"  | "H8"  | "H5"  | "H4"
            "D4"  | "D5"  | "DJ"  | "D9"  | "D10"
            "C5"  | "C6"  | "CA"  | "CK"  | "C2"
    }

    def "ストレートの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "ストレート"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "H2"  | "D3"  | "C4"  | "S5"
            "H7"  | "D6"  | "C5"  | "S4"  | "H3"
            "D9"  | "SK"  | "C10" | "HQ"  | "HJ"
            "CK"  | "CJ"  | "HA"  | "C10" | "CQ"
    }

    def "ストレートフラッシュの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "ストレートフラッシュ"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "S2"  | "S3"  | "S4"  | "S5"
            "H7"  | "H6"  | "H5"  | "H4"  | "H3"
            "D9"  | "DK"  | "D10" | "DQ"  | "DJ"
            "CK"  | "CJ"  | "C9"  | "C10" | "CQ"
    }

    def "ロイヤルストレートフラッシュの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "ロイヤルストレートフラッシュ"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "S10" | "SJ"  | "SQ"  | "SK"
            "HK"  | "HQ"  | "HJ"  | "H10" | "HA"
            "DA"  | "DK"  | "D10" | "DQ"  | "DJ"
            "CK"  | "CJ"  | "CA"  | "C10" | "CQ"
    }

    def "フォー・オブ・ア・カインドの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "フォー・オブ・ア・カインド"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "HA"  | "DA"  | "CA"  | "SK"
            "H2"  | "S2"  | "C2"  | "DJ"  | "D2"
            "D10" | "H10" | "D9"  | "S10" | "C10"
            "CK"  | "CQ"  | "DK"  | "HK"  | "SK"
            "D7"  | "CJ"  | "DJ"  | "HJ"  | "SJ"
    }

    def "フルハウスの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "フルハウス"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "HA"  | "DA"  | "CK"  | "SK"
            "H2"  | "S2"  | "C3"  | "D3"  | "H3"
            "D9"  | "H9"  | "D10" | "S9"  | "C10"
            "CK"  | "CQ"  | "DQ"  | "HK"  | "SQ"
            "D7"  | "CA"  | "C7"  | "HA"  | "S7"
    }

    def "スリー・オブ・ア・カインドの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "スリー・オブ・ア・カインド"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "HA"  | "DA"  | "CK"  | "SQ"
            "H2"  | "S4"  | "C3"  | "D3"  | "H3"
            "D9"  | "H9"  | "D10" | "S9"  | "CJ"
            "C6"  | "CQ"  | "DQ"  | "H7"  | "SQ"
            "D7"  | "C5"  | "C7"  | "H8"  | "S7"
    }

    def "ツーペアの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "ツーペア"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "H3"  | "D3"  | "CK"  | "SK"
            "H2"  | "S2"  | "C5"  | "D5"  | "H7"
            "D9"  | "H6"  | "D10" | "S9"  | "C10"
            "CK"  | "CA"  | "DJ"  | "HK"  | "SA"
            "D7"  | "CA"  | "DA"  | "H4"  | "S7"
    }

    def "ワンペアの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "ワンペア"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "H2"  | "D3"  | "CK"  | "SK"
            "H2"  | "S2"  | "C3"  | "D5"  | "H7"
            "D9"  | "H6"  | "D10" | "S3"  | "C10"
            "CK"  | "CQ"  | "DJ"  | "HK"  | "SA"
            "D7"  | "CA"  | "C6"  | "HA"  | "SJ"
    }

    def "ノーペアの場合"() {
        expect:
        sut.poker(card1, card2, card3, card4, card5) == "ノーペア"
        where:
            card1 | card2 | card3 | card4 | card5
            "SA"  | "H2"  | "D3"  | "CK"  | "SJ"
            "H2"  | "S4"  | "C3"  | "D5"  | "H7"
            "D9"  | "H6"  | "D10" | "S3"  | "C5"
            "CK"  | "CQ"  | "DJ"  | "H7"  | "SA"
            "D7"  | "C9"  | "C6"  | "HA"  | "SJ"
    }
}
