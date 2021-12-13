import algorithms.AStar;
import states.State;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AStar astar = new AStar();
        ArrayList<Integer> endStatus = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 13, 14, 15));
        State state = new State(endStatus);

        System.out.println(astar.checkValuesAreOnWrongPosition(endStatus));
        astar.compute(state);

    }
}
