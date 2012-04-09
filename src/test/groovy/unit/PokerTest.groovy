package test.groovy.unit

import spock.lang.Specification
import main.groovy.Poker

class PokerTest extends Specification {

    Poker sut

    def setup() {
        sut = new Poker()
    }

    def "ワンペア"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'ワンペア'
        where:
            card1 | card2 | card3 | card4 | card5
            'SA'  | 'DA'  | 'D2'  | 'D3'  | 'D4'
            'D2'  | 'S5'  | 'C4'  | 'HK'  | 'H2'
    }

    def "ツーペア"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'ツーペア'
        where:
            card1 | card2 | card3 | card4 | card5
            'SA'  | 'DA'  | 'D2'  | 'S2'  | 'D4'
            'D2'  | 'S2'  | 'H3'  | 'CJ'  | 'C3'
    }

    def "スリー・オブ・ア・カインド"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'スリー・オブ・ア・カインド'
        where:
            card1 | card2 | card3 | card4 | card5
            'SA'  | 'DA'  | 'D2'  | 'HA'  | 'D4'
            'D10' | 'H10' | 'C10' | 'CK'  | 'CJ'
    }

    def "フォー・オブ・ア・カインド"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'フォー・オブ・ア・カインド'
        where:
            card1 | card2 | card3 | card4 | card5
            'SA'  | 'DA'  | 'D2'  | 'HA'  | 'CA'
            'D10' | 'H10' | 'C10' | 'CK'  | 'S10'
    }

    def "ストレート"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'ストレート'
        where:
            card1 | card2 | card3 | card4 | card5
            'S3'  | 'D4'  | 'S2'  | 'S5'  | 'H6'
            'DK'  | 'H10' | 'SJ'  | 'HA'  | 'CQ'
    }

    def "フラッシュ"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'フラッシュ'
        where:
            card1 | card2 | card3 | card4 | card5
            'S2'  | 'SK'  | 'S5'  | 'S3'  | 'S4'
            'DK'  | 'D2'  | 'DJ'  | 'DA'  | 'DQ'
    }

    def "フルハウス"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'フルハウス'
        where:
            card1 | card2 | card3 | card4 | card5
            'DK'  | 'SK'  | 'D5'  | 'CK'  | 'S5'
            'CA'  | 'C10' | 'D10' | 'DA'  | 'SA'
    }

    def "ストレートフラッシュ"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'ストレートフラッシュ'
        where:
            card1 | card2 | card3 | card4 | card5
            'D9'  | 'DQ'  | 'D8'  | 'D10' | 'DJ'
            'S2'  | 'S4'  | 'S3'  | 'S5'  | 'S6'
    }

    def "ロイヤルストレートフラッシュ"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'ロイヤルストレートフラッシュ'
        where:
            card1 | card2 | card3 | card4 | card5
            'DK'  | 'DQ'  | 'DA'  | 'D10' | 'DJ'
            'S10' | 'SJ'  | 'SQ'  | 'SK'  | 'SA'
    }

    def "ノーペア"() {
        expect:
            sut.poker(card1, card2, card3, card4, card5) == 'ノーペア'
        where:
            card1 | card2 | card3 | card4 | card5
            'D2'  | 'D3'  | 'D4'  |'D5'   | 'SK'
            'D10' | 'H2'  | 'H4'  | 'HK'  | 'SA'
    }

    def "カードの枚数が5枚以外のとき、RuntimeExceptionが発生する"() {
        when:
            sut.poker(cards.toArray() as String[])
        then:
            thrown RuntimeException
        where:
            cards << [[],
                      ['D2', 'D3', 'D4','D5'],
                      ['D10', 'H2', 'H4', 'HK', 'SA', 'H3']]
    }

    def "カードが重複しているとき、RuntimeExceptionが発生する"() {
        when:
            sut.poker(card1, card2, card3, card4, card5)
        then:
            thrown RuntimeException
        where:
            card1 | card2 | card3 | card4 | card5
            'D2'  | 'D3'  | 'D4'  | 'D5'  | 'D5'
            'SK'  | 'S3'  | 'H4'  |'SK'   | 'SA'
    }

    def "カードの表記が間違っているとき、RuntimeExceptionを発生する"() {
        when:
            sut.poker(card1, card2, card3, card4, card5)
        then:
            thrown RuntimeException
        where:
            card1 | card2 | card3 | card4 | card5
            'D2'  | 'D3'  | 'DA'  |'4D'   | 'D5'
            'SK'  | 'Y3'  | 'H4'  | 'SK'  | 'SA'
            'DD'  | 'D4'  | 'CA'  | 'C2'  | 'C3'
    }
}
