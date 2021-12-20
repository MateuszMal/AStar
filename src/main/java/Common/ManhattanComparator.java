package Common;

import states.State;

import java.util.Comparator;

public class ManhattanComparator implements Comparator<State> {
    @Override
    public int compare(State state, State t1) {
        return Integer.compare(state.manhattan(), t1.manhattan());
    }
}
