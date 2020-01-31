package com.seventimes.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.lineSeparator;

public class BoardImpl implements Board {

    private static final int EMPTY_TILE = 0;
    private int[][] tiles;
    private int size;
    private BoardHelper helper;

    public BoardImpl(int[][] tiles) {
        this.size = tiles.length;

        initTiles(tiles);
        helper = new BoardHelper(this);
    }

    @Override
    public int dimension() {
        return size;
    }

    @Override
    public int hamming() {
        return helper.reduce(0,
                (result, tile) -> result += !isGoal(tile) ? 1 : 0,
                true);
    }

    @Override
    public int manhattan() {
        return helper.reduce(0,
                (result, tile) -> result += helper.distance(tile.i, getGoalX(tile.number)) + helper.distance(tile.j, getGoalY(tile.number)),
                true);
    }

    @Override
    public boolean isGoal() {
        return helper.match(tile -> isGoal(tile), true);
    }

    @Override
    public Iterable<Board> neighbors() {
        Tile empty = helper.find(tile -> isEmpty(tile.number));
        List<Board> neighbours = new ArrayList<>();
        if (empty.j > 0) {
            neighbours.add(new BoardImpl(tiles).swap(empty, getTile(empty.i, empty.j - 1)));
        }
        if (empty.j < size - 1) {
            neighbours.add(new BoardImpl(tiles).swap(empty, getTile(empty.i, empty.j + 1)));
        }
        if (empty.i > 0) {
            neighbours.add(new BoardImpl(tiles).swap(empty, getTile(empty.i - 1, empty.j)));
        }
        if (empty.i < size - 1) {
            neighbours.add(new BoardImpl(tiles).swap(empty, getTile(empty.i + 1, empty.j)));
        }
        return neighbours;
    }

    private Tile getTile(int i, int j) {
        return new Tile(tiles[i][j], i, j);
    }

    @Override
    public Board twin() {
        Tile from, to;

        do {
            from = getTile((int) (Math.random() * size), (int) (Math.random() * size));
            to = getTile((int) (Math.random() * size), (int) (Math.random() * size));
        } while (isEmpty(from) || isEmpty(to) || from.number == to.number);

        return new BoardImpl(tiles).swap(from, to);
    }

    @Override
    public boolean isSolvable() {
        List<Integer> numbers = helper.reduce(new ArrayList<Integer>(), (list, tile) -> {
            list.add(tile.number);
            return list;
        }, false);

        int inversions = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (!isEmpty(numbers.get(i)) && !isEmpty(numbers.get(j)) && numbers.get(i) > numbers.get(j)) {
                    inversions++;
                }
            }
        }

        return inversions % 2 == 0;
    }

    public Board swap(Tile tile1, Tile tile2) {
        tiles[tile1.i][tile1.j] = tile2.number;
        tiles[tile2.i][tile2.j] = tile1.number;

        return this;
    }


    @Override
    public String toString() {
        return helper.reduce(new StringBuilder(), (sb, tile) ->
                sb.append(isEmpty(tile.number) ? " " : tile.number)
                        .append(helper.isLastInRow(tile.j) ? lineSeparator() : "\t"), false
        ).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardImpl board = (BoardImpl) o;
        return dimension() == board.dimension() && helper.match(tile -> tile.number == board.number(tile.i, tile.j), false);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }

    int number(int i, int j) {
        return tiles[i][j];
    }

    boolean isEmpty(int number) {
        return number == EMPTY_TILE;
    }

    boolean isEmpty(Tile tile) {
        return isEmpty(tile.number);
    }

    private void initTiles(int[][] tiles) {
        this.tiles = new int[size][size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, size);
        }
    }

    private boolean isGoal(Tile tile) {
        return tile.i == getGoalX(tile.number) && tile.j == getGoalY(tile.number);
    }

    private int getGoalX(int number) {
        if (number == EMPTY_TILE) {
            return size - 1;
        }
        return (int) Math.floor((number - 1) / size);
    }

    private int getGoalY(int number) {
        if (number == EMPTY_TILE) {
            return size - 1;
        }
        return number - getGoalX(number) * size - 1;
    }

    static class Tile {
        int number;
        int i;
        int j;

        Tile(int number, int i, int j) {
            this.number = number;
            this.i = i;
            this.j = j;
        }
    }
}
