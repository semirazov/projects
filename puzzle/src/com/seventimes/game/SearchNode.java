package com.seventimes.game;

public class SearchNode {

    private Board board;
    private int moves;
    private int priority;
    private SearchNode prevNode;


    public SearchNode(Board board) {
        this.board = board;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public SearchNode getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(SearchNode prevNode) {
        this.prevNode = prevNode;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
