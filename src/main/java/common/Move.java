package common;

import states.Operator;
import states.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Move {
    private static Integer zeroPos;

    public static Integer findZero(List<Integer> entryPoint) {
        for (int i = 0; i < entryPoint.size(); i++) {
            if (entryPoint.get(i) == 0) return i;
        }
        return null;
    }

    public static Optional<State> moveZero(State state, Operator operator) {
        Integer step;
        ArrayList<Integer> fifteenPuzzle = new ArrayList<>(state.getFifteenPuzzle());
        zeroPos = findZero(fifteenPuzzle);
        switch (operator) {
            case L -> {
                step = 1;
                if (zeroPos != null && zeroPos % 4 != 0) {
                    Integer temp = fifteenPuzzle.get(zeroPos - step);
                    fifteenPuzzle.set(zeroPos - step, 0);
                    fifteenPuzzle.set(zeroPos, temp);
                    return Optional.of(getState(state, operator, fifteenPuzzle));
                }
            }
            case R -> {
                step = 1;
                if (zeroPos != null && zeroPos % 4 != 3) {
                    Integer temp = fifteenPuzzle.get(zeroPos + step);
                    fifteenPuzzle.set(zeroPos + step, 0);
                    fifteenPuzzle.set(zeroPos, temp);
                    return Optional.of(getState(state, operator, fifteenPuzzle));
                }
            }
            case U -> {
                step = 4;
                if (zeroPos != null && zeroPos > 3) {
                    Integer temp = fifteenPuzzle.get(zeroPos - step);
                    fifteenPuzzle.set(zeroPos - step, 0);
                    fifteenPuzzle.set(zeroPos, temp);
                    return Optional.of(getState(state, operator, fifteenPuzzle));
                }
            }
            case D -> {
                step = 4;
                if (zeroPos != null && zeroPos < 12) {
                    Integer temp = fifteenPuzzle.get(zeroPos + step);
                    fifteenPuzzle.set(zeroPos + step, 0);
                    fifteenPuzzle.set(zeroPos, temp);
                    return Optional.of(getState(state, operator, fifteenPuzzle));
                }
            }
        }
        return Optional.empty();
    }

    private static State getState(State state, Operator operator, ArrayList<Integer> fifteenPuzzle) {
        State newState = new State(fifteenPuzzle);
        newState.setOperator(operator);
        newState.setParent(state);
        newState.setDeep(state.getDeep() + 1);
        return newState;
    }
}
