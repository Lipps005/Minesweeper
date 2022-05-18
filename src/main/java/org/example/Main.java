package org.example;

import java.io.Console;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        GridSingleton.getInstance().revealCell(8, 'A');
        GridSingleton.getInstance().updateGrid(1, 'H');
        GridSingleton.getInstance().renderGrid();
        GridSingleton.getInstance().revealCell(1, 'H');
        GridSingleton.getInstance().renderGrid();
        GridSingleton.getInstance().revealAllCells();
        GridSingleton.getInstance().renderGrid();

    }

}