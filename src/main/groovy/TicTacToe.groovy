package main.groovy

import main.java.*

class TicTacToe implements features.TicTacToe {

    @Override
    int ticTacToe(int[][] board) {
        if (board.length != 3) throw new RuntimeException()
        board.each {if (it.length != 3) throw new RuntimeException()}
        board.each {column ->
            column.each {value ->
                if([-1, 0, 1].find{it == value} == null)
                    throw new RuntimeException()
            }
        }

        def (win0, win1) = [isPlayerWin(board, 0), isPlayerWin(board, 1)]
        if (win0 && win1) throw new RuntimeException()
        else if (win0) return 0
        else if (win1) return 1
        else return -1
    }

    private boolean isPlayerWin(int[][] board, int player) {
        def columnWin =
            board.collect{column ->
                column.every {it == player}
            }.any {it}

        def rowWin = false
        board[0].eachWithIndex {it, index ->
            if (it == player) rowWin |= (it == board[1][index] && it == board[2][index])
        }

        def inclineWin = false
        board[0].eachWithIndex {it, index ->
            if (it == player) {
                if (index == 0) inclineWin |= (it == board[1][1] && it == board[2][2])
                if (index == 2) inclineWin |= (it == board[1][1] && it == board[2][0])
            }
        }

        return columnWin || rowWin || inclineWin
    }
}
