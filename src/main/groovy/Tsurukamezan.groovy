package main.groovy

import main.java.*

class Tsurukamezan implements features.Tsurukamezan {
    @Override
    String tsurukame(int x, int y) {
        if (x < 0 || y < 0) throw new RuntimeException()
        def tsuruNum =
            (0..x).find{tsuru ->
                return tsuru*2 + (x-tsuru)*4 == y
            }
        if (tsuruNum == null) throw new RuntimeException()
        def kameNum = x - tsuruNum
        return "鶴${tsuruNum}羽、亀${kameNum}匹"
    }
}
