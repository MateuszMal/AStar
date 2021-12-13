package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StateTest {
    private State state;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> matrix = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));
        state = new State(matrix);
    }

    @Test
    void shouldCreateStateInstance() {
        ArrayList<Integer> someMatrix = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));

        assertEquals(someMatrix, state.getFifteenPuzzle());
    }

    @Test
    void shouldHashCodeEquals() {
        ArrayList<Integer> someMatrix = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));
        ArrayList<Integer> differentMatrix = new ArrayList<>(List.of(0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1));

        assertEquals(someMatrix.hashCode(), state.hashCode());
        assertNotEquals(differentMatrix.hashCode(), state.hashCode());
    }

}