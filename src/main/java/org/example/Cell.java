package org.example;

public class Cell
{
    private static int minedCells = 10;

    public int hasMine = -1;
    public int revealed = -1;
    public int mineCount = 0;

    public Cell()
    {
        if(minedCells > 0)
        {
                hasMine = 1;
                minedCells--;

        }
    }

    public void setRevealed()
    {
        this.revealed = 1;
    }

    public char getCellStatus()
    {
        char returnChar = '-';
        if(this.revealed == 1)
        {
            if(this.hasMine == 1)
            {
                returnChar = 'X';
            }
            else
            {
                if(this.mineCount == 0)
                {
                    returnChar = ' ';
                }
                else
                {
                    returnChar = (char)(48+this.mineCount);
                }

            }
        }
        //return returnChar = (char)(48+this.mineCount);
        return returnChar;
    }

}
