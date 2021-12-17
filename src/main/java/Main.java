import algorithms.AStar;
import states.State;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AStar astar = new AStar();
        ArrayList<Integer> endStatus = new ArrayList<>(List.of(
                1, 2, 3, 4,
                5, 6, 7, 8,
                0, 9, 10, 11,
                12, 13, 14, 15));
        State state = new State(endStatus);

        ArrayList<Integer> endStatus1 = new ArrayList<>(List.of(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 0));
        State state1 = new State(endStatus1);

        ArrayList<Integer> endStatus2 = new ArrayList<>(List.of(
                1, 2, 0, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                3, 13, 14, 15));
        State state2 = new State(endStatus2);

        astar.compute(state2);
    }
}
