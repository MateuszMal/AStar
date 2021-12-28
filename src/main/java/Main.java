import algorithms.AStar;
import states.State;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AStar astar = new AStar();
        ArrayList<Integer> endStatus = new ArrayList<>(List.of(
                1, 2, 3, 4,
                5, 6, 7, 0,
                8, 9, 10, 11,
                12, 13, 14, 15));
        State state = new State(endStatus,4,4);

        ArrayList<Integer> endStatus1 = new ArrayList<>(List.of(
                2, 1, 3, 4,
                5, 6, 7, 8,
                9, 10, 15, 11,
                0, 13, 14, 12));
        State state1 = new State(endStatus1,4,4);

        ArrayList<Integer> endStatus2 = new ArrayList<>(List.of(
                1, 2, 3, 4,
                5, 6, 7, 8,
                10, 13, 11, 12,
                9, 14, 0, 15));
        State state2 = new State(endStatus2,4,4);

        astar.compute(state2, "hamm");
    }
}
