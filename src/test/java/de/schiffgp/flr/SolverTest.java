package de.schiffgp.flr;

import org.junit.Test;

import java.io.IOException;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class SolverTest {

    @Test
    public void shouldGetExpectedLargestRegion_flr001() throws IOException {

        // given
        String filename = "flr001.in";
        ColorBoard colorBoard = ColorBoardFileUtils.loadColorBoard(filename);
        Solver solver = new Solver(colorBoard);

        // when
        int actualLargestRegion = execWithTimer(solver::findLargestRegion);

        // then
        assertEquals(colorBoard.getExpectedLargestRegion(), actualLargestRegion);
    }

    @Test
    public void shouldGetExpectedLargestRegion_flr002() throws IOException {

        // given
        String filename = "flr002.in";
        ColorBoard colorBoard = ColorBoardFileUtils.loadColorBoard(filename);
        Solver solver = new Solver(colorBoard);

        // when
        int actualLargestRegion = execWithTimer(solver::findLargestRegion);

        // then
        assertEquals(colorBoard.getExpectedLargestRegion(), actualLargestRegion);
    }

    @Test
    public void shouldGetExpectedLargestRegion_flr003() throws IOException {

        String filename = "flr003.in";
        ColorBoard colorBoard = ColorBoardFileUtils.loadColorBoard(filename);

        int actualLargestRegion = execWithTimer(() -> {
            Solver solver = new Solver(colorBoard);
            return solver.findLargestRegion();
        });

        System.out.println("Result: " + actualLargestRegion);

        // then
        assertEquals(colorBoard.getExpectedLargestRegion(), actualLargestRegion);
    }

    @Test
    public void shouldGetExpectedLargestRegion_flr004() throws IOException {

        String filename = "flr004.in";
        ColorBoard colorBoard = ColorBoardFileUtils.loadColorBoard(filename);

        int actualLargestRegion = execWithTimer(() -> {
            Solver solver = new Solver(colorBoard);
            return solver.findLargestRegion();
        });

        // then
        assertEquals(colorBoard.getExpectedLargestRegion(), actualLargestRegion);
    }

    @Test
    public void shouldGetExpectedLargestRegion_flr005() throws IOException {

        String filename = "flr005.in";
        ColorBoard colorBoard = ColorBoardFileUtils.loadColorBoard(filename);

        int actualLargestRegion = execWithTimer(() -> {
            Solver solver = new Solver(colorBoard);
            return solver.findLargestRegion();
        });

        System.out.println("Result: " + actualLargestRegion);

        // then
        assertEquals(colorBoard.getExpectedLargestRegion(), actualLargestRegion);
    }

    @Test
    public void shouldGetExpectedLargestRegion_flr006() throws IOException {

        String filename = "flr006.in";
        ColorBoard colorBoard = ColorBoardFileUtils.loadColorBoard(filename);

        int actualLargestRegion = execWithTimer(() -> {
            Solver solver = new Solver(colorBoard);
            return solver.findLargestRegion();
        });

        System.out.println("Result: " + actualLargestRegion);

        // then
        assertEquals(colorBoard.getExpectedLargestRegion(), actualLargestRegion);
    }

    private int execWithTimer(Supplier<Integer> task) {

        long start = System.currentTimeMillis();
        Integer result = task.get();
        System.out.println("Zeit: " + (System.currentTimeMillis() - start));
        return result;
    }
}