package com.seventimes.game;

public interface Solver {

    // is the initial board solvable? (see below)
    boolean isSolvable();

    // min number of moves to solve initial board
    int moves();

    // sequence of boards in a shortest solution
    Iterable<Board> solution();
}