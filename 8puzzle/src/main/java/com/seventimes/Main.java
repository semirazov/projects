package com.seventimes;

import com.seventimes.game.Board;
import com.seventimes.game.BoardImpl;
import com.seventimes.game.Solver;
import com.seventimes.game.SolverImpl;

public class Main {
    public static void main(String[] args) {
        final Board board = new BoardImpl(new int[][]{{4,15,14,0}, {6,8,7,13}, {3,10,12,11}, {2,1,5,9}});
        final Solver solver = new SolverImpl(board);

        System.out.println(board);

        System.out.println("Hamming: " + board.hamming());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());


        for(Board b : solver.solution()){
            System.out.println(b);
            System.out.println("---------");
        }

    }
}
