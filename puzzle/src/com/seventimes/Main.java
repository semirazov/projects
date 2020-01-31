package com.seventimes;

import com.seventimes.game.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Board boardUnsolvable = new BoardImpl(new int[][]{{1, 4, 3}, {2, 5, 6}, {8, 7, 0}});
        final Board boardUnsolvable1 = new BoardImpl(new int[][]{{1, 2, 3}, {4, 5, 6}, {8, 7, 0}});
        final Board board = new BoardImpl(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        final Solver solver = new SolverImpl(boardUnsolvable);

        List<Board> boards = solver.solution();
//        new Drawer(boards).animate();
        new Drawer(boards).animate();
        System.out.println("Is solvable: " + solver.isSolvable());
        System.out.println("Moves: " + solver.moves());

    }
}
