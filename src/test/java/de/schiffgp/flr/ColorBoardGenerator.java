package de.schiffgp.flr;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class ColorBoardGenerator {

    @Test
    public void generateFile() throws IOException {
        ColorBoard colorBoard = generate(10000, 1000, 6);
        System.out.println("board generated.");
        ColorBoardFileUtils.saveColorBoard("flr006.in", colorBoard);
    }

    public static ColorBoard generate(int colums, int rows, int numberOfDifferentColors) {

        ColorBoard colorBoard = new ColorBoard(rows, colums, 0);
        Random random = new Random();

        for (int x = 0; x < colums; x++) {
            for (int y = 0; y < rows; y++) {

                int color = random.nextInt(numberOfDifferentColors);
                colorBoard.setColor(x, y, color);

            }
        }

        return colorBoard;
    }
}
