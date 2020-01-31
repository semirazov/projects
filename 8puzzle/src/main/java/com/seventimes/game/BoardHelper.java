package com.seventimes.game;

import java.util.function.BiFunction;
import java.util.function.Predicate;

class BoardHelper {

    private BoardImpl board;

    BoardHelper(BoardImpl board) {
        this.board = board;
    }

    boolean isLastInRow(int j) {
        return (j + 1) % board.dimension() == 0;
    }

    int distance(int i1, int i2) {
        return Math.abs(i1 - i2);
    }

    <R> R reduce(R identity, BiFunction<R, BoardImpl.Tile, R> biFunction, boolean skipEmpty) {
        int size = board.dimension();
        R result = identity;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int number = board.number(i, j);
                if (skipEmpty && board.isEmpty(number)) continue;
                result = biFunction.apply(result, new BoardImpl.Tile(number, i, j));
            }
        }
        return result;
    }

    BoardImpl.Tile find(Predicate<BoardImpl.Tile> predicate) {
        int size = board.dimension();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                BoardImpl.Tile tile = new BoardImpl.Tile(board.number(i, j), i, j);
                if (predicate.test(tile)) {
                    return tile;
                }

            }
        }
        return null;
    }

    boolean match(Predicate<BoardImpl.Tile> consumer, boolean skipEmpty) {
        int size = board.dimension();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int number = board.number(i, j);
                if (skipEmpty && board.isEmpty(number)) continue;
                if (!consumer.test(new BoardImpl.Tile(number, i, j))) {
                    return false;
                }

            }
        }
        return true;
    }

}
