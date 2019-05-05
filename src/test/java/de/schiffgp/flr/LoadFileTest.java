package de.schiffgp.flr;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoadFileTest {

    @Test
    public void shouldLoadColorBoard() throws IOException {

        String filename = "flr001.in";

        ColorBoard colorBoard = ColorBoardFileUtils.loadColorBoard(filename);

        assertEquals(colorBoard.getColor(0, 0), 1);
        assertEquals(colorBoard.getColor(3, 2), 3);
    }

}