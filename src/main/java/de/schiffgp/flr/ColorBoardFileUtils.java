package de.schiffgp.flr;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ColorBoardFileUtils {

    public static ColorBoard loadColorBoard(String filename) {

        InputStream is = ColorBoardFileUtils.class.getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        List<String> lines = reader.lines().collect(Collectors.toList());
        String[] firstLineArray = lines.get(0).split(" ");

        int rows = Integer.parseInt(firstLineArray[0]);
        int colums = Integer.parseInt(firstLineArray[1]);
        int expectedSize = Integer.parseInt(firstLineArray[2]);

        ColorBoard colorBoard = new ColorBoard(rows, colums, expectedSize);
        for (int i = 0; i < rows; i++) {
            String[] rowArray = lines.get(i + 1).split(" ");
            for (int j = 0; j < colums; j++) {
                colorBoard.setColor(j, i, Integer.parseInt(rowArray[j]));
            }
        }

        return colorBoard;
    }

    public static void saveColorBoard(String filename, ColorBoard colorBoard) throws IOException {
        FileUtils.writeStringToFile(new File(filename), colorBoard.toString(), "UTF-8");
    }
}
