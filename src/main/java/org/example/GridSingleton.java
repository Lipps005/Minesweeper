package org.example;

import java.util.Random;

public class GridSingleton
{
    //set seed for random so we can repeat the testing and still get the same results.
    private static final Random random = new Random(1965864024);
    //Singleton pattern because we only want a single instance of the grid
    //and a global point of access.
    //Our GridSingleton class will determine how to build the grid.

    private static GridSingleton gridSingleton;

    //create the grid template. It is quicker to calculate the cell we need to change and replace its character in
    //the string than it would be to use for loops to re-create the grid every time.
    private static final String GridTemplate = """
            8 -  -  -  -  -  -  -  -\s
            7 -  -  -  -  -  -  -  -\s
            6 -  -  -  -  -  -  -  -\s
            5 -  -  -  -  -  -  -  -\s
            4 -  -  -  -  -  -  -  -\s
            3 -  -  -  -  -  -  -  -\s
            2 -  -  -  -  -  -  -  -\s
            1 -  -  -  -  -  -  -  -\s
              A  B  C  D  E  F  G  H\s
            """;

    private static final char[] Grid = GridTemplate.toCharArray();

    private Cell[] gridCells = new Cell[64];
    private GridSingleton()
    {
        //generate new Cells
        for(int i = 0; i < 64; i++) {
            gridCells[i] = new Cell();
        }

        //pseudorandomly shuffle the cells
        for(int i = 0; i < 64; i++)
        {
            int j = random.nextInt(i, 64);
            Cell tempCell = gridCells[j];
            gridCells[j] = gridCells[i];
            gridCells[i] = tempCell;

        }

        //update each cell with mine count

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                int mineCount = 0;
                //i*8 + j = current index
                if(j+1 < 8)
                {
                    //middle right
                    mineCount += (gridCells[(i * 8) + j + 1].hasMine == 1) ? 1 : 0;
                }
                if(j-1 > -1 && ((i * 8) + j - 1) >= i*8 )
                {
                    //middle left
                    mineCount += (gridCells[(i * 8) + j - 1].hasMine == 1) ? 1 : 0;
                }
                if((((i * 8) + j+7) > (i+1) * 8 ) && ((i * 8) + j+7) < 64 )
                {
                    //top right
                    mineCount += (gridCells[((i * 8) + j+7)].hasMine == 1) ? 1 : 0;
                }
                if(j+1 < 8 && ((i * 8) + j+9) < 64)
                {
                    //top left
                    mineCount += (gridCells[((i * 8) + j+9)].hasMine == 1) ? 1 : 0;
                }
                if((i*8 - j-9) > ((i-1) * 8) && j-1 > -1)
                {
                    //bottom left
                    mineCount += (gridCells[((i * 8) + j-9)].hasMine == 1) ? 1 : 0;
                }
                if((i*8 + j - 7) < i*8 && (i*8 + j - 7) > -1)
                {
                    //bottom right
                    mineCount += (gridCells[(i * 8 + j-7)].hasMine == 1) ? 1 : 0;
                }
                if((i*8 + j+8) < 64)
                {
                    //top middle
                    mineCount += (gridCells[((i * 8) + j+8)].hasMine == 1) ? 1 : 0;
                }
                if((i*8 + j-8) > -1)
                {
                    //bottom middle
                    mineCount += (gridCells[((i * 8) + j-8)].hasMine == 1) ? 1 : 0;
                }


                /*
                    +8 top middle
                    -8 bottom middle
                    -A (if j - 1 < 0, doesn't exist)
                    +A (if j + 1 > 7, doesn't exist)

                 */
                gridCells[i*8+j].mineCount = mineCount;
            }

        }
    }

    public Cell getGridCell(int row, char chr)
    {
        //calculate int column value from char (A-H = 8)
        int col = (chr - 65); //ASCII value - ASCII value for A

        return gridCells[8*(row-1) + col]; //calculate index in gridCell Array and return cell object.
    }

    public static GridSingleton getInstance()
    {
        if(gridSingleton == null)
        {
            gridSingleton = new GridSingleton();
        }
        return gridSingleton;
    }

    public void renderGrid()
    {
        System.out.println(Grid);

    }

    public void updateGrid(int row, char chr)
    {
        //each row has 26 chars.
        //cells are every 3 chars.
        int cellPositionInGrid = 26*(9-row-1) + (chr - 65)*3 + 2;
        Grid[cellPositionInGrid] = getGridCell(row, chr).getCellStatus();
    }

    public void revealAllCells()
    {
        for(int i = 1; i < 9; i++)
        {
            getGridCell(i, (char) (65 + 0)).setRevealed();
            updateGrid(i, (char) (65 + 0));
            getGridCell(i, (char) (65 + 1)).setRevealed();
            updateGrid(i, (char) (65 + 1));
            getGridCell(i, (char) (65 + 2)).setRevealed();
            updateGrid(i, (char) (65 + 2));
            getGridCell(i, (char) (65 + 3)).setRevealed();
            updateGrid(i, (char) (65 + 3));
            getGridCell(i, (char) (65 + 4)).setRevealed();
            updateGrid(i, (char) (65 + 4));
            getGridCell(i, (char) (65 + 5)).setRevealed();
            updateGrid(i, (char) (65 + 5));
            getGridCell(i, (char) (65 + 6)).setRevealed();
            updateGrid(i, (char) (65 + 6));
            getGridCell(i, (char) (65 + 7)).setRevealed();
            updateGrid(i, (char) (65 + 7));

        }
    }

    public void revealCell(int row, char chr)
    {
        getGridCell(row, chr).setRevealed();
        updateGrid(row, chr);

        if(getGridCell(row, chr).hasMine == 1)
        {

        }




    }
}
