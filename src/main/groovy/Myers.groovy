package main.groovy

import main.java.*

class Myers implements features.Myers {
    @Override
    String getName(int x, int y, int z) {
        if (!triangleJudge(x, y, z)) throw new RuntimeException()
        if (x == y && y == z) return "正三角形"
        else if (x == y || x == z || y == z ) return "二等辺三角形"
        else return "不等辺三角形"
    }

    boolean triangleJudge(int x, int y, int z) {
        if (x <= y) {
            if (y <= z) return x + y > z
            else return x + z > y
        }
        else {
            if (x <= z) return x + y > z
            else return y + z > x
        }
    }
}