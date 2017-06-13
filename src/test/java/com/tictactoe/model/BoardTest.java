package com.tictactoe.model;

import com.tictactoe.enums.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by rybeusz on 12.06.17.
 */
class BoardTest {

    Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void testBoardCreatesBoardWith3Rows() {
        assertEquals(9, board.getCells().length);
    }


    @Test
    public void testInitFillsCellsWithCorrectCoordinates() {
        Cell cell = new Cell(8, 6);
        board.init();
        assertTrue(cell.equals(board.getCells()[8]));
    }

    @Test
    public void testIsDrawIfNoEmptyCells() {
        board.init();
        for (Cell cell : board.getCells()) {
            cell.setContent(Seed.CROSS);
        }
        assertTrue(board.isDraw());
    }

    @Test
    public void testIsDrawIfSomeEmptyCells() {
        board.init();
        for (Cell cell : board.getCells()) {
            cell.setContent(Seed.CROSS);
        }
        board.getCells()[8].clear();
        assertFalse(board.isDraw());
    }

    @Test
    public void testIf3CrossesInRowWin() {
        // |X|X|X|
        // | | | |
        // | | | |
        board.init();
        board.getCells()[0].setContent(Seed.CROSS);
        board.getCells()[1].setContent(Seed.CROSS);
        assertTrue(board.hasWon(Seed.CROSS, 2));
    }

    @Test
    public void testIf3NoughtInRowWin() {
        // |O|O|O|
        // | | | |
        // | | | |
        board.init();
        board.getCells()[0].setContent(Seed.NOUGHT);
        board.getCells()[1].setContent(Seed.NOUGHT);
        assertTrue(board.hasWon(Seed.NOUGHT, 2));
    }

    @Test
    public void testIf3CrossesInCrossWin() {
        // |X| | |
        // | |X| |
        // | | |X|
        board.init();
        board.getCells()[0].setContent(Seed.CROSS);
        board.getCells()[4].setContent(Seed.CROSS);
        assertTrue(board.hasWon(Seed.CROSS, 8));
    }

    @Test
    public void testIf3NoughtInCrossWin() {
        // |O| | |
        // | |O| |
        // | | |O|
        board.init();
        board.getCells()[0].setContent(Seed.NOUGHT);
        board.getCells()[4].setContent(Seed.NOUGHT);
        assertTrue(board.hasWon(Seed.NOUGHT, 8));
    }

    @Test
    public void testPlayerChooseUsedCellThrowsException() {
        board.init();
        board.getCells()[0].setContent(Seed.NOUGHT);
        board.getCells()[1].setContent(Seed.NOUGHT);
        board.getCells()[2].setContent(Seed.NOUGHT);
        assertThrows(IllegalArgumentException.class, () -> {
           board.hasWon(Seed.CROSS, 0);
        });
    }

    @Test
    public void testFullBoardWithCrossWinner() {
        // |X|X|O|
        // |O|X|O|
        // |O|X|X|
        board.init();
        board.getCells()[0].setContent(Seed.CROSS);
        board.getCells()[1].setContent(Seed.CROSS);
        board.getCells()[2].setContent(Seed.NOUGHT);
        board.getCells()[3].setContent(Seed.NOUGHT);
        board.getCells()[4].setContent(Seed.CROSS);
        board.getCells()[5].setContent(Seed.NOUGHT);
        board.getCells()[6].setContent(Seed.NOUGHT);
        board.getCells()[8].setContent(Seed.CROSS);
        assertTrue(board.hasWon(Seed.CROSS, 7));
    }

    @Test
    public void testFullBoardWithoutWinner() {
        // |X|O|X|
        // |X|O|O|
        // |O|X|O|
        board.init();
        board.getCells()[0].setContent(Seed.CROSS);
        board.getCells()[1].setContent(Seed.NOUGHT);
        board.getCells()[2].setContent(Seed.CROSS);
        board.getCells()[3].setContent(Seed.CROSS);
        board.getCells()[4].setContent(Seed.NOUGHT);
        board.getCells()[5].setContent(Seed.NOUGHT);
        board.getCells()[6].setContent(Seed.NOUGHT);
        board.getCells()[8].setContent(Seed.NOUGHT);
        assertFalse(board.hasWon(Seed.CROSS, 7));
    }

}