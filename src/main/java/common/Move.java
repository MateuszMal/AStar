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
        int columns = state.getColumns();
        int rows = state.getRows() - 1;
        Integer step;
        ArrayList<Integer> fifteenPuzzle = new ArrayList<>(state.getFifteenPuzzle());
        zeroPos = findZero(fifteenPuzzle);
        switch (operator) {
            case L -> {
                step = 1;
                if (zeroPos != null && zeroPos % columns != 0) {
                    Integer temp = fifteenPuzzle.get(zeroPos - step);
                    fifteenPuzzle.set(zeroPos - step, 0);
                    fifteenPuzzle.set(zeroPos, temp);
                    return Optional.of(getState(state, operator, fifteenPuzzle));
                }
            }
            case R -> {
                step = 1;
                if (zeroPos != null && zeroPos % columns != columns - 1) {
                    Integer temp = fifteenPuzzle.get(zeroPos + step);
                    fifteenPuzzle.set(zeroPos + step, 0);
                    fifteenPuzzle.set(zeroPos, temp);
                    return Optional.of(getState(state, operator, fifteenPuzzle));
                }
            }
            case U -> {
                step = 4;
                if (zeroPos != null && zeroPos > rows) {
                    Integer temp = fifteenPuzzle.get(zeroPos - step);
                    fifteenPuzzle.set(zeroPos - step, 0);
                    fifteenPuzzle.set(zeroPos, temp);
                    return Optional.of(getState(state, operator, fifteenPuzzle));
                }
            }
            case D -> {
                step = 4;
                if (zeroPos != null && zeroPos < (rows * columns)) {
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
        int columns = state.getColumns();
        int rows = state.getRows();
        State newState = new State(fifteenPuzzle, rows, columns);
        newState.setOperator(operator);
        newState.setParent(state);
        newState.setDeep(state.getDeep() + 1);
        return newState;
    }
}
