package test.groovy.unit

import main.groovy.TicTacToe
import spock.lang.Specification

class TicTacToeTest extends Specification {

    TicTacToe sut

    def setup() {
        sut = new TicTacToe()
    }

    def "0が勝ちのとき"() {
        expect:
            sut.ticTacToe(board as int[][]) == 0
        where:
            board << [
                      [[0, 0, 0], [0, 1, 1], [0, 1, 1]],
                      [[1, 1, 0], [0, 1, 0], [1, 0, 0]],
                      [[0, 0, 0], [0, 0, 0], [0, 0, 0]]]
    }

    def "1が勝ちのとき"() {
        expect:
            sut.ticTacToe(board as int[][]) == 1
        where:
            board << [
                      [[0, 0, -1], [0, 1, 1], [1, 1, 1]],
                      [[1, 0, 1], [0, 0, 1], [0, 1, 1]],
                      [[1, 1, 1], [1, 1, 1], [0, 1, 0]]]
    }


    def "引き分けのとき"() {
        expect:
            sut.ticTacToe(board as int[][]) == -1
        where:
            board << [
                      [[1, 0, 1], [0, 1, 0], [0, 1, 0]],
                      [[0, 1, 0], [0, 1, 1], [1, 0, 1]],
                      [[-1, -1, -1], [-1, -1, -1], [-1, -1, -1]]]
    }

    def "ゲーム盤が3×3でないとき、RuntimeExceptionが発生する"() {
        when:
            sut.ticTacToe(board as int[][])
        then:
            def e = thrown RuntimeException
            e.class == RuntimeException
        where:
            board << [
                      [[], [], []],
                      [[1, 1, 1, 1], [0, 1, 1], [-1, -1, -1]],
                      [[0, 0, 0], [0, 1, 1], [1, 1]]]
    }

    def "ゲーム盤に1,0,-1以外の数字があるとき、RuntimeExceptionが発生する"() {
        when:
        sut.ticTacToe(board as int[][])
        then:
        def e = thrown RuntimeException
        e.class == RuntimeException
        where:
        board << [
                [[10, 0, 0], [0, 0, 0], [-1, -1, -1]],
                [[-1, 1, 1], [0, 1, 1], [-2, -1, -1]]]
    }

    def "どちらも勝ちのとき、RuntimeExceptionが発生する"() {
        when:
        sut.ticTacToe(board as int[][])
        then:
        def e = thrown RuntimeException
        e.class == RuntimeException
        where:
        board << [
                  [[1, 1, 1], [0, 0, 0], [-1, -1, -1]],
                  [[0, 1, 0], [0, 1, 0], [1, 1, 0]]]
    }
}
