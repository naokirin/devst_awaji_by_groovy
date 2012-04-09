package main.groovy

import main.java.*

class Poker implements features.Poker {
    @Override
    String poker(String... cards) {
        def cardsList = this.cardsList(cards)
        def numberList = this.numberList(cardsList)
        def numberCounts = this.numberCounts(numberList)

        if (numberCounts[2] == 1 && numberCounts[3] == 1)
            return 'フルハウス'
        else if (numberCounts[2] == 1)
            return 'ワンペア'
        else if (numberCounts[2] == 2)
            return 'ツーペア'
        else if (numberCounts[3] == 1)
            return 'スリー・オブ・ア・カインド'
        else if (numberCounts[4] == 1)
            return 'フォー・オブ・ア・カインド'


        def straight = straightJudge(numberList)
        def royalStraight = straightJudge(numberList.collect {it == 1 ? 14 : it})
        def suitList = cardsList.collect { it[0] }
        def flush = suitList.every {it == suitList[0]}

        if (straight)
            if (flush) return 'ストレートフラッシュ'
            else return 'ストレート'
        else if (royalStraight)
            if (flush) return 'ロイヤルストレートフラッシュ'
            else return 'ストレート'
        else if (flush) return 'フラッシュ'
        return 'ノーペア'
    }

    private List cardsList(String... cards) {
        if (cards.size() != 5) throw new RuntimeException()
        def cardsList = cards.collect {
                def match = it =~ /(S|C|D|H)([2-9]|10|J|Q|K|A)/
                if (match.matches()) [match[0][1], match[0][2]]
                else throw new RuntimeException() }
        if (cardsList.unique().size() != 5) throw new RuntimeException()
        else return cardsList
    }

    private List numberList(List cardsList) {
        def alphaToNum = [J:11, Q:12, K:13, A:1]
        return cardsList.collect {
            if (alphaToNum[it[1]] != null) alphaToNum[it[1]]
            else it[1].toInteger() }
    }

    private Map numberCounts(List numberList) {
        return numberList
                .countBy { it }
                .countBy {_, value -> value }
    }

    private boolean straightJudge(List<Integer> cardNumbers) {
        return (new IntRange(cardNumbers.min(), cardNumbers.max()).size() == cardNumbers.size()) && cardNumbers.unique().size() == 5
    }
}
