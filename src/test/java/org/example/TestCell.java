package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCell
{
    Cell testCell = new Cell();
    @Test
    public void testCellIsCreated()
    {

        Assertions.assertInstanceOf(Cell.class, testCell, "object of type Cell not created");
    }

    @Test
    public void testCellHasMine()
    {
        Assertions.assertTrue(testCell.hasMine == 1, "first cell not instantiated with a mine");
    }


}
