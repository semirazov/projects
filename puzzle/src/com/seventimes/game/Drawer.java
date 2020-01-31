package com.seventimes.game;

import java.util.List;

public class Drawer {

    private List<Board> boards;

    public Drawer(List<Board> boards) {
        this.boards = boards;
    }

    public void animate() {

        boards.stream().forEach((board) -> {

            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(board);
            System.out.println("Manhattan: " + board.manhattan());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
