package org.example;

import java.util.Scanner;


public class Main {

    public static void main(String[] args){

        GridSingleton.getInstance().revealCell(8, 'A');
        GridSingleton.getInstance().updateGrid(1, 'H');
        GridSingleton.getInstance().renderGrid();
        GridSingleton.getInstance().revealCell(1, 'H');
        GridSingleton.getInstance().renderGrid();
        GridSingleton.getInstance().revealAllCells();
        GridSingleton.getInstance().renderGrid();

    }

}