package algorithms;

import org.junit.jupiter.api.Test;
import states.State;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest {
    private final AStar astar = new AStar();

    @Test
    void shouldBeCorrectEndStatus() {
        ArrayList<Integer> matrix = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));

        assertEquals(matrix.hashCode(), astar.getResult());
    }

    @Test
    void shouldBeEnd() {
        ArrayList<Integer> matrix = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));

        assertTrue(astar.isEnd(matrix));
    }

    @Test
    void shouldReturnAmountOfWrongNumbersPositions() {
        ArrayList<Integer> matrix = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15));
        ArrayList<Integer> matrix1 = new ArrayList<>(List.of(2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15));
        ArrayList<Integer> matrix2 = new ArrayList<>(List.of(2, 1, 4, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15));

        assertEquals(1, astar.checkValuesAreOnWrongPosition(matrix));
        assertEquals(3, astar.checkValuesAreOnWrongPosition(matrix1));
        assertEquals(5, astar.checkValuesAreOnWrongPosition(matrix2));
    }

    @Test
    void shouldFindNeighbors() {
        ArrayList<Integer> matrix = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15));
        State state = new State(matrix);
        List<State> neighbors = astar.getNeighbors(state);
        List<Integer> checkList = new ArrayList<>();
        neighbors.forEach(e -> checkList.addAll(e.getFifteenPuzzle()));

        assertAll(() -> checkList.containsAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15)),
                () -> checkList.containsAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0)),
                () -> checkList.containsAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 11, 12, 13, 14, 15)),
                () -> checkList.containsAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15)));
    }

    @Test
    void shouldStopComputingPuzzles() {
        State state = new State(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0)));
        State wrongState = new State(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15)));

        assertTrue(astar.compute(state));
        assertFalse(astar.compute(wrongState));
    }

    @Test
    void shouldFindResult() {
        State state = new State(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 14, 15)));

        astar.compute(state);
    }
}