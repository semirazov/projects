package com.seventimes.game;

import java.util.List;

public interface Solver {

    // is the initial board solvable? (see below)
    boolean isSolvable();

    // min number of moves to solve initial board
    int moves();

    // sequence of boards in a shortest solution
    List<Board> solution();
}