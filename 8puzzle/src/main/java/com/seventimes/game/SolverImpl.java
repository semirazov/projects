package com.seventimes.game;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolverImpl implements Solver {

    private Board board;
    private MinPQ<SearchNode> pq;

    public SolverImpl(Board board) {
        this.board = board;
        pq = new MinPQ<>(Comparator.comparingInt(SearchNode::getPriority));
    }

    @Override
    public boolean isSolvable() {
        return false;
    }

    @Override
    public int moves() {
        return 0;
    }

    @Override
    public Iterable<Board> solution() {
        SearchNode initialNode = new SearchNode(board);
        initialNode.setPriority(board.manhattan());

        pq.insert(initialNode);

        SearchNode currentNode = initialNode;
        Board currentBoard;

        while (!currentNode.getBoard().isGoal()) {
            currentNode = pq.delMin();
            currentBoard = currentNode.getBoard();

            for (Board board : currentBoard.neighbors()) {
                SearchNode neighbourNode = new SearchNode(board);
                neighbourNode.setPrevNode(currentNode);
                neighbourNode.setMoves(currentNode.getMoves() + 1);

                neighbourNode.setPriority(neighbourNode.getMoves() + board.manhattan());

                pq.insert(neighbourNode);
            }
        }


        return restoreSolution(currentNode);
    }


    private List<Board> restoreSolution(SearchNode node) {
        List<Board> boards = new ArrayList<>();
        SearchNode currentNode = node;

        do {
            boards.add(currentNode.getBoard());
        } while ((currentNode = currentNode.getPrevNode()) != null);

        return boards;
    }
}
