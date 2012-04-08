package main.groovy

import main.java.*

class Coin implements features.Coin {
    @Override
    String coinUsage(int amount) {
        if (amount < 1) throw new RuntimeException()

        def rest = amount
        def coinCount = [500, 100, 50, 10, 5, 1].collectEntries {coin ->
            def use = (rest / coin).toInteger()
            rest -= use * coin
            [(coin):use]
        }
        def result = ""
        coinCount.each {coin ->
            if (coin.value > 0) {
                if (!result.empty) result += 'と'
                if (coin.value == 1) result += "${coin.key}円玉"
                else result += "${coin.key}円玉${coin.value}枚"
            }
        }
        return result
    }
}
