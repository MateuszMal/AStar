package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StateTest {
    private State state;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> matrix = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));
        state = new State(matrix,4,4);
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

    @Test
    void shouldSortListOfStates() {
        ArrayList<Integer> integers1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15));
        ArrayList<Integer> integers2 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 14, 15));
        ArrayList<Integer> integers3 = new ArrayList<>(List.of(2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 14, 15));
        ArrayList<Integer> integers4 = new ArrayList<>(List.of(2, 1, 3, 4, 0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        State state1 = new State(integers1,4,4);
        State state2 = new State(integers2,4,4);
        State state3 = new State(integers3,4,4);
        State state4 = new State(integers4,4,4);
        List<State> states = new ArrayList<>();
        states.add(state4);
        states.add(state3);
        states.add(state2);
        states.add(state1);

        Collections.sort(states);

    }
}