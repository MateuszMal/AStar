package Common;

import states.State;

import java.util.Comparator;

public class HammingComparator implements Comparator<State> {

    @Override
    public int compare(State state, State t1) {
        return Integer.compare((state.getDeep() + state.getWrongPositions()),
                (t1.getDeep() + t1.getWrongPositions()));
    }
}
